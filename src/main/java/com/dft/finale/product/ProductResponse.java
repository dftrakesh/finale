
package com.dft.finale.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductResponse {

    private String weight;
    private String statusId;
    private String productId;
    private String productUrl;
    private String createdDate;
    private String weightUomId;
    private String containerId;
    private String internalName;
    private String productTypeId;
    private String distanceUomId;
    private String lastUpdatedDate;
    private String manufacturerName;
    private String caseDimensionUomId;
    private String actionUrlDeactivate;
    private String universalProductCode;
    private String manufacturerProductId;
    private List<PriceList> priceList = null;
    private List<SupplierList> supplierList = null;
    private List<ProductStoreList> productStoreList = null;
    private List<UserFieldDataList> userFieldDataList = null;
    private List<StatusIdHistoryList> statusIdHistoryList = null;
}