package com.sfdo.ngp.graphql;

import java.util.HashMap;
import java.util.Map;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class GraphQLRequest {

    private static Map<String, Object> EMPTY_VARIABLES = new HashMap<>();

    public static GraphQLRequest build(String query) {
        return builder()
                .query(query)
                .build();
    }

    public String query;
    public String variables;

}


