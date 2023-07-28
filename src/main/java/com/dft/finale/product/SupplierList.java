
package com.dft.finale.product;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SupplierList {
    private String supplierPartyUrl;
    private String supplierPrefOrderId;
    private Double price;
    private String currencyUomId;
}