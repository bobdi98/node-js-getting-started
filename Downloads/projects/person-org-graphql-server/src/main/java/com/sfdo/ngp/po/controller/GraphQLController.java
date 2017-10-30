package com.sfdo.ngp.po.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import com.sfdo.ngp.po.graphql.GraphQLRequest;
import com.sfdo.ngp.po.graphql.GraphQLResponse;
import com.sfdo.ngp.po.graphql.GraphQLService;


@Slf4j
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class GraphQLController {

    @Autowired
    private GraphQLService graphQLService;

    @RequestMapping(value = "/graphql", method = RequestMethod.POST)
    public GraphQLResponse<?> graphql(@RequestHeader HttpHeaders headers, @RequestBody GraphQLRequest graphql) throws Exception {
        System.out.println("GraphQLRequest=" + graphql);
        GraphQLResponse<?> execute = graphQLService.execute(graphql, headers);
        return execute;
    }
}
