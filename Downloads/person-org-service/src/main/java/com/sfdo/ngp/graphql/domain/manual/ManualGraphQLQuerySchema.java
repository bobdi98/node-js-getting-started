package com.sfdo.ngp.graphql.domain.manual;

import static graphql.Scalars.GraphQLID;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLList.list;
import static graphql.schema.GraphQLObjectType.newObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sfdo.ngp.data.schema.Person;
import com.sfdo.ngp.graphql.fetchers.PersonListFetcher;
import com.sfdo.ngp.service.PersonAdaptor;

import graphql.schema.GraphQLObjectType;


@Configuration
public class ManualGraphQLQuerySchema {

    @Autowired
    PersonAdaptor personAdaptor;


    @Bean
    @Qualifier("PersonType")
    public GraphQLObjectType getPersonType() {
        return newObject()
                .name("Person")
                .field(newFieldDefinition()
                        .name("id")
                        .type(GraphQLID)
                        .dataFetcher(dfe -> ((Person) dfe.getSource()).getId()))
                .field(newFieldDefinition()
                        .name("bday")
                        .type(GraphQLString))
                .field(newFieldDefinition()
                        .name("lastName")
                        .type(GraphQLString)
                        .dataFetcher(dfe -> ((Person) dfe.getSource()).getLastname()))
                .field(newFieldDefinition()
                        .name("title")
                        .type(GraphQLString))
                .build();
    }


    @Bean
    @Qualifier("QueryType")
    public GraphQLObjectType getQueryType() {
        return newObject()
                .name("QueryType")
                .field(newFieldDefinition()
                        .name("persons")
                        .type(list(getPersonType()))
                        .dataFetcher(new PersonListFetcher(personAdaptor))
                        .build())
                .build();
    }
}
