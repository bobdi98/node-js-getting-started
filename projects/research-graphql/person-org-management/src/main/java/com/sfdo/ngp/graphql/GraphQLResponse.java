package com.sfdo.ngp.graphql;

import java.util.List;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class GraphQLResponse<T> {

    public T data;
    public List errors;
}


