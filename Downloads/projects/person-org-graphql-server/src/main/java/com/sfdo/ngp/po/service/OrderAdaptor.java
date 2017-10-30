package com.sfdo.ngp.po.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfdo.ngp.po.configuration.DataSourceProperties;

import feign.Feign;
import feign.Headers;
import feign.Logger;
import feign.Param;
import feign.RequestLine;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.extern.slf4j.Slf4j;
import me.roybailey.data.schema.OrderDto;


@Slf4j
@Service()
public class OrderAdaptor {

    @Autowired
    DataSourceProperties properties;

    @Headers("Accept: application/json")
    private interface OrderApi {

        @RequestLine("GET /order-api/v1/order")
        List<OrderDto> getAllOrders();

        @RequestLine("GET /order-api/v1/order/{id}")
        OrderDto getOrderById(@Param("id") String id);

        @Headers("Content-Type: application/json")
        @RequestLine("POST /order-api/v1/order")
        OrderDto createOrder(OrderDto order);
    }

    private OrderApi api;

    @PostConstruct
    public void apiSetup() {
        //System.out.println("Connecting {} to {}", OrderApi.class.getSimpleName(), properties.getUrlOrderService());
        this.api = Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logLevel(Logger.Level.BASIC)
                .target(OrderApi.class, properties.getUrlOrderService());
    }


    public List<OrderDto> getAllOrders() {
        List<OrderDto> allOrders = api.getAllOrders();
        //System.out.println("getAllOrders() : {}", allOrders);
        return allOrders;
    }

    public OrderDto getOrder(String orderId) {
        OrderDto order = api.getOrderById(orderId);
        //System.out.println("getOrder({}) : {}", orderId, order);
        return order;
    }

    public OrderDto createOrder(OrderDto order) {
        OrderDto newOrder = api.createOrder(order);
        //System.out.println("createOrder({}) : {}", order, newOrder);
        return newOrder;
    }

}

