
package com.dft.finale.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductResponse {

    private String productId;
    private String productTypeId;
    private String statusId;
    private String productUrl;
    private String lastUpdatedDate;
    private String createdDate;
    private String weightUomId;
    private String caseDimensionUomId;
    private String distanceUomId;
    private String actionUrlDeactivate;

    private List<PriceList> priceList = null;
    private List<SupplierList> supplierList = null;
    private List<ProductStoreList> productStoreList = null;
    private List<UserFieldDataList> userFieldDataList = null;
    private List<StatusIdHistoryList> statusIdHistoryList = null;
    private String universalProductCode;
    private String weight;
    private String internalName;
    private String containerId;
    private String manufacturerName;
    private String manufacturerProductId;
}
