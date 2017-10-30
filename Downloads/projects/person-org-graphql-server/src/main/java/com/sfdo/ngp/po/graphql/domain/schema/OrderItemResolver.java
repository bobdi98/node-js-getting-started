package com.sfdo.ngp.po.graphql.domain.schema;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.sfdo.ngp.po.service.ProductAdaptor;

import me.roybailey.data.schema.OrderItemDto;
import me.roybailey.data.schema.ProductDto;

/**
 * This class contains resolver methods for the "Data Class" type
 */
@Service
public class OrderItemResolver implements GraphQLResolver<OrderItemDto> {

    @Autowired
    ProductAdaptor productAdaptor;

    public ProductDto getProduct(OrderItemDto item) {
        return productAdaptor.getProduct(item.getProductId());
    }
}