package com.sfdo.ngp.po.graphql.fetchers;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.sfdo.ngp.po.service.UserAdaptor;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import me.roybailey.data.schema.UserDto;


/**
 * Instantiated by graphql-java library so we need to hook into Spring to get other beans.
 */
@Slf4j
public class UserFetcher implements DataFetcher<UserDto>, ApplicationListener<ContextRefreshedEvent> {

    UserAdaptor userAdaptor;

    public UserFetcher(UserAdaptor userAdaptor) {
        this.userAdaptor = userAdaptor;
    }

    @Override
    public UserDto get(DataFetchingEnvironment environment) {
        //System.out.println("getArguments {}" + environment.getArguments());
        String userId = environment.getArgument("userId");
        UserDto user = userAdaptor.getUser(userId);
        return user;
    }

    // only needed for annotations schema as it instantiates fetchers outside spring
    public UserFetcher(){}

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        userAdaptor = contextRefreshedEvent.getApplicationContext().getBean(UserAdaptor.class);
    }
}
