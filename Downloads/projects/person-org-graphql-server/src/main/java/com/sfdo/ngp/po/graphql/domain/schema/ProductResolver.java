package com.sfdo.ngp.po.graphql.domain.schema;


import org.springframework.stereotype.Service;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.sfdo.ngp.po.schema.ProductDto;


/**
 * This class contains resolver methods for the "Data Class" type
 */
@Service
public class ProductResolver implements GraphQLResolver<ProductDto> {

    public String getProductId(ProductDto product) {
        return product.getBrand();
    }

}