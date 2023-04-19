package com.dft.finale.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductRequest {
    private String productId;
    private String internalName;
    private String productTypeId;
    private String statusId;
    private String productUrl;
    private String weightUomId;
    private String caseDimensionUomId;
    private String distanceUomId;
    private String actionUrlDeactivate;
    private String manufacturerProductId;
    private List<PriceList> priceList;
    private List<SupplierList> supplierList;
    private List<ProductStoreList> productStoreList;
    private List<UserFieldDataList> userFieldDataList;
    private List<StatusIdHistoryList> statusIdHistoryList;
    private String universalProductCode;
    private String weight;
    private String sessionSecret;
}
