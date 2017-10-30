package com.sfdo.ngp.po.graphql.domain.schema;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.sfdo.ngp.po.service.UserAdaptor;

import me.roybailey.data.schema.OrderDto;
import me.roybailey.data.schema.UserDto;

/**
 * This class contains resolver methods for the "Data Class" type
 */
@Service
public class OrderResolver implements GraphQLResolver<OrderDto> {

    @Autowired
    UserAdaptor userAdaptor;

    public UserDto getUser(OrderDto order) {
        return userAdaptor.getUser(order.getUserId());
    }
}