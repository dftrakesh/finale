package com.dft.finale;

import com.dft.finale.handler.JsonBodyHandler;
import com.dft.finale.product.ProductRequest;
import com.dft.finale.product.ProductResponse;
import com.dft.finale.report.FinaleReport;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class FinaleAPIResource extends Finale {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public FinaleAPIResource(String userName, String password, String storeName) {
        super(userName, password, storeName);
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
    public List<List<String>> getReport(String url) {
        URI uri = baseUrl(url);
        HttpResponse.BodyHandler<FinaleReport[]> handler = new JsonBodyHandler<>(FinaleReport[].class);
        HttpRequest request = get(uri);
        FinaleReport[] report = getRequestWrapped(request, handler);
        return report[0].getData();
    }
}
