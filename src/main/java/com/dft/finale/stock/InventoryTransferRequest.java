package com.dft.finale.stock;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Objects;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InventoryTransferRequest {

    private String sessionSecret;
    private String productUrl;
    private String facilityUrlFrom;
    private String facilityUrlTo;
    private String lotId;
    private String quantity;
    private Objects normalizedPackingString;
    private String receiveDate;
    private String sendDate;
    private String generalComments;
}
