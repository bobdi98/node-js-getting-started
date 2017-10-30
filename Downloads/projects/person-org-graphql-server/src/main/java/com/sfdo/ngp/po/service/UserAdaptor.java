package com.sfdo.ngp.po.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sfdo.ngp.po.configuration.DataSourceProperties;

import feign.Feign;
import feign.Headers;
import feign.Logger;
import feign.Param;
import feign.RequestLine;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.extern.slf4j.Slf4j;
import me.roybailey.data.schema.UserDto;


@Slf4j
@Service
public class UserAdaptor {

    @Autowired
    DataSourceProperties properties;

    @Autowired
    ObjectMapper jacksonMapper;

    @Headers("Accept: application/json")
    private interface UserApi {

        @RequestLine("GET /user-api/v1/user")
        List<UserDto> getAllUsers();

        @RequestLine("GET /user-api/v1/user/{id}")
        UserDto getUserById(@Param("id") String id);

        @RequestLine("POST /user-api/v1/user")
        UserDto createUser(UserDto user);
    }

    private UserApi api;

    @PostConstruct
    public void apiSetup() {
        //System.out.println("Connecting {} to {}", UserApi.class.getSimpleName(), properties.getUrlUserService());
        this.api = Feign.builder()
                .encoder(new JacksonEncoder(jacksonMapper))
                .decoder(new JacksonDecoder(jacksonMapper))
                .logLevel(Logger.Level.BASIC)
                .target(UserApi.class, properties.getUrlUserService());
    }


    public List<UserDto> getAllUsers() {
        List<UserDto> allUsers = api.getAllUsers();
        //System.out.println("getAllUsers() : {}", allUsers);
        return allUsers;
    }

    public UserDto getUser(String userId) {
        UserDto user = api.getUserById(userId);
        //System.out.println("getUser({}) : {}", userId, user);
        return user;
    }


    public UserDto createUser(UserDto user) {
        UserDto newUser = api.createUser(user);
        //System.out.println("createUser({}) : {}", user, newUser);
        return newUser;
    }
}


