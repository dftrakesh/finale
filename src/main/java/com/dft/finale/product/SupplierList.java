
package com.dft.finale.product;


import lombok.Data;

@Data
public class SupplierList {

    private String supplierPartyUrl;
    private String supplierPrefOrderId;
    private Double price;
    private String currencyUomId;
}
