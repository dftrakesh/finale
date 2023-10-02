package com.dft.finale;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.dft.finale.constants.FinaleConstants;
import com.dft.finale.login.LoginRequest;
import lombok.SneakyThrows;

import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class Finale implements FinaleConstants {
    private final HttpClient client;
    int MAX_ATTEMPTS = 50;
    int TIME_OUT_DURATION = 60000;
    private final String userName;
    private final String password;
    private final String BaseURL;

    public Finale(String userName, String password, String storeName) {
        this.BaseURL = FINALE_URL + storeName;
        this.userName = userName;
        this.password = password;

        client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .version(HttpClient.Version.HTTP_1_1)
                .cookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ALL))
                .connectTimeout(Duration.ofSeconds(360))
                .build();
        //login();
    }

    @SneakyThrows
    public List<HttpCookie> login() {
        String jsonBody = new ObjectMapper().writeValueAsString(new LoginRequest(userName, password));
        HttpRequest request = post(baseUrl(LOGIN_URL), jsonBody);
        client.send(request, HttpResponse.BodyHandlers.ofString());
        return getCookies();
    }

    public List<HttpCookie> getCookies() {
        Optional<CookieHandler> cookieHandler = client.cookieHandler();
        if (cookieHandler.isEmpty()) return null;
        CookieStore store = ((CookieManager) cookieHandler.get()).getCookieStore();
        if (store == null) return null;
        return store.getCookies();
    }

    @SneakyThrows
    protected URI baseUrl(String path) {
        return new URI(BaseURL + FORWARD_SLASH + path);
    }

    @SneakyThrows
    protected HttpRequest get(URI uri) {
        HttpRequest.Builder builder = HttpRequest.newBuilder(uri)
                .header(HTTP_REQUEST_PROPERTY_ACCEPT, HTTP_REQUEST_ACCEPT_TYPE_JSON)
                .GET();
        return builder.build();
    }

    @SneakyThrows
    protected HttpRequest post(URI uri, final String jsonBody) {
        HttpRequest.Builder builder = HttpRequest.newBuilder(uri)
                .header(HTTP_REQUEST_PROPERTY_CONTENT_TYPE, HTTP_REQUEST_CONTENT_TYPE_JSON)
                .header(HTTP_REQUEST_PROPERTY_ACCEPT, HTTP_REQUEST_ACCEPT_TYPE_JSON)
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody));
        return builder.build();
    }

    @SneakyThrows
    public <T> T getRequestWrapped(HttpRequest request, HttpResponse.BodyHandler<T> handler) {
        try {
            return client
                    .sendAsync(request, handler)
                    .thenComposeAsync(response -> tryResend(client, request, handler, response, 1))
                    .get()
                    .body();
        } catch (Exception exception) {
            login();
        }
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public <T> CompletableFuture<HttpResponse<T>> tryResend(HttpClient client,
                                                            HttpRequest request,
                                                            HttpResponse.BodyHandler<T> handler,
                                                            HttpResponse<T> resp, int count) {
        if (resp.statusCode() == 429 && count < MAX_ATTEMPTS) {
            Thread.sleep(TIME_OUT_DURATION);
            return client.sendAsync(request, handler)
                    .thenComposeAsync(response -> tryResend(client, request, handler, response, count + 1));
        }
        return CompletableFuture.completedFuture(resp);
    }
}
