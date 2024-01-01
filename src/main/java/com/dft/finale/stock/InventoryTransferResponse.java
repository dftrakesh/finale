package com.dft.finale.stock;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class InventoryTransferResponse {

    private String inventoryTransferId;
    private String inventoryTransferUrl;
    private String productId;
    private String productUrl;
    private String quantity;
    private String facilityUrlFrom;
    private String facilityUrlTo;
    private String sendDate;
    private String receiveDate;
    private String lastUpdatedDate;
    private String createdDate;
    private String generalComments;
}
