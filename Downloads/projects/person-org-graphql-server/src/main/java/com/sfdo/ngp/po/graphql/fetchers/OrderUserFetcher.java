package com.sfdo.ngp.po.graphql.fetchers;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.sfdo.ngp.po.service.UserAdaptor;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import me.roybailey.data.schema.OrderDto;
import me.roybailey.data.schema.UserDto;


/**
 * Instantiated by graphql-java library so we need to hook into Spring to get other beans.
 */
@Slf4j
public class OrderUserFetcher implements DataFetcher<UserDto>, ApplicationListener<ContextRefreshedEvent> {

    UserAdaptor userAdaptor;

    public OrderUserFetcher(UserAdaptor userAdaptor) {
        this.userAdaptor = userAdaptor;
    }

    @Override
    public UserDto get(DataFetchingEnvironment environment) {
        //System.out.println("getSource {}" + (Object) environment.getSource());
        OrderDto order = environment.getSource();
        UserDto user = userAdaptor.getUser(order.getUserId());
        return user;
    }

    // only needed for annotations schema as it instantiates fetchers outside spring
    public OrderUserFetcher() {
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        userAdaptor = contextRefreshedEvent.getApplicationContext().getBean(UserAdaptor.class);
    }
}
