package com.sfdo.ngp.po.graphql.fetchers;

import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.http.HttpHeaders;

import com.sfdo.ngp.po.service.UserAdaptor;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import me.roybailey.data.schema.UserDto;


/**
 * Instantiated by graphql-java library so we need to hook into Spring to get other beans.
 */
@Slf4j
public class UserListFetcher implements DataFetcher<List<UserDto>>, ApplicationListener<ContextRefreshedEvent> {

    UserAdaptor userAdaptor;

    public UserListFetcher(UserAdaptor userAdaptor) {
        this.userAdaptor = userAdaptor;
    }

    @Override
    public List<UserDto> get(DataFetchingEnvironment environment) {
        HttpHeaders headers = environment.getContext();
        //System.out.println("getContext() {}" + headers);
        //System.out.println("getArguments {}"+ environment.getArguments());
        List<UserDto> allUsers = userAdaptor.getAllUsers();
        return allUsers;
    }

    // only needed for annotations schema as it instantiates fetchers outside spring
    public UserListFetcher() {}

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        userAdaptor = contextRefreshedEvent.getApplicationContext().getBean(UserAdaptor.class);
    }
}
