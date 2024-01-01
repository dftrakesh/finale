package com.dft.finale;

import com.dft.finale.handler.JsonBodyHandler;
import com.dft.finale.product.ProductRequest;
import com.dft.finale.product.ProductResponse;
import com.dft.finale.report.FinaleReport;
import com.dft.finale.stock.InventoryTransferRequest;
import com.dft.finale.stock.InventoryTransferResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.net.HttpCookie;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FinaleAPIResource extends Finale {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public FinaleAPIResource(String userName, String password, String storeName) {
        super(userName, password, storeName);
    }

    public HttpCookie getSeesionSecret() {
        return getCookies().stream().filter(httpCookie -> httpCookie.getName().equalsIgnoreCase("JSESSIONID"))
                .findFirst().orElse(null);
    }

    @SneakyThrows
    public ProductResponse updateProduct(String sku, ProductRequest productRequest) {
        URI uri = baseUrl(PRODUCT_URL + FORWARD_SLASH + sku);
        String jsonBody = objectMapper.writeValueAsString(productRequest);
        HttpRequest request = post(uri, jsonBody);
        HttpResponse.BodyHandler<ProductResponse> handler = new JsonBodyHandler<>(ProductResponse.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public ProductResponse getProduct(String sku) {
        String encodedUrl = URLEncoder.encode(sku, StandardCharsets.UTF_8);
        URI uri = baseUrl(PRODUCT_URL + FORWARD_SLASH + encodedUrl);
        HttpRequest request = get(uri);
        HttpResponse.BodyHandler<ProductResponse> handler = new JsonBodyHandler<>(ProductResponse.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public List<List<String>> getReport(String url) {
        URI uri = baseUrl(url);
        HttpResponse.BodyHandler<FinaleReport[]> handler = new JsonBodyHandler<>(FinaleReport[].class);
        HttpRequest request = get(uri);
        FinaleReport[] report = getRequestWrapped(request, handler);
        return report[0].getData();
    }

    @SneakyThrows
    public InventoryTransferResponse inventoryStockTransfer(InventoryTransferRequest inventoryTransferRequest) {
        URI uri = baseUrl(RESOURCE_INVENTORY_TRANSFER);
        String jsonBody = objectMapper.writeValueAsString(inventoryTransferRequest);
        HttpRequest request = post(uri, jsonBody);
        HttpResponse.BodyHandler<InventoryTransferResponse> handler = new JsonBodyHandler<>(InventoryTransferResponse.class);
        return getRequestWrapped(request, handler);
    }
}