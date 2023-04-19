
package com.dft.finale.product;

import lombok.Data;

@Data
public class PriceList {
     
    private String productPriceTypeId;
    private Double price;
    private String currencyUomId;
}
