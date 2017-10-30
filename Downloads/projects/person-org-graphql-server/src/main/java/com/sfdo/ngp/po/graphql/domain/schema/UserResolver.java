package com.sfdo.ngp.po.graphql.domain.schema;


import org.springframework.stereotype.Service;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.sfdo.ngp.po.schema.UserDto;

/**
 * This class contains resolver methods for the "Data Class" type
 */
@Service
public class UserResolver implements GraphQLResolver<UserDto> {

    public String getUserId(UserDto user) {
        return user.getId();
    }

    public String getFirstname(UserDto user) {
        return user.getGivenName();
    }

    public String getLastname(UserDto user) {
        return user.getFamilyName();
    }
}