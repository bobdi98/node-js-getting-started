package com.sfdo.ngp.po.graphql.fetchers;

import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.sfdo.ngp.po.service.OrderAdaptor;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import me.roybailey.data.schema.OrderDto;


/**
 * Instantiated by graphql-java library so we need to hook into Spring to get other beans.
 */
@Slf4j
public class OrderListFetcher implements DataFetcher<List<OrderDto>>, ApplicationListener<ContextRefreshedEvent> {

    OrderAdaptor orderAdaptor;

    public OrderListFetcher(OrderAdaptor orderAdaptor) {
        this.orderAdaptor = orderAdaptor;
    }

    @Override
    public List<OrderDto> get(DataFetchingEnvironment environment) {
        //System.out.println("getArguments {}" + environment.getArguments());
        List<OrderDto> allOrders = orderAdaptor.getAllOrders();
        return allOrders;
    }

    // only needed for annotations schema as it instantiates fetchers outside spring
    public OrderListFetcher(){}

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        orderAdaptor = contextRefreshedEvent.getApplicationContext().getBean(OrderAdaptor.class);
    }
}
