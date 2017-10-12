package com.sfdo.ngp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sfdo.ngp.graphql.GraphQLRequest;
import com.sfdo.ngp.graphql.GraphQLResponse;
import com.sfdo.ngp.graphql.GraphQLService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class GraphQLController {

    @Autowired
    private GraphQLService graphQLService;

    @RequestMapping(value = "/graphql", method = RequestMethod.POST)
    public GraphQLResponse<?> graphql(@RequestHeader HttpHeaders headers, @RequestBody GraphQLRequest graphql) throws Exception {
        log.info("GraphQLRequest=" + graphql);
        GraphQLResponse<?> execute = graphQLService.execute(graphql, headers);
        return execute;
    }
}
