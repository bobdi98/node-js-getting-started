package com.sfdo.ngp.graphql.domain.manual;

import static graphql.Scalars.GraphQLID;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLInputObjectField.newInputObjectField;
import static graphql.schema.GraphQLInputObjectType.newInputObject;
import static graphql.schema.GraphQLObjectType.newObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sfdo.ngp.graphql.fetchers.PersonDeleteFetcher;
import com.sfdo.ngp.graphql.fetchers.PersonUpsertFetcher;
import com.sfdo.ngp.service.PersonAdaptor;

import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLInputType;
import graphql.schema.GraphQLObjectType;


@Configuration
public class ManualGraphQLMutationSchema {

    @Autowired
    PersonAdaptor personAdaptor;

    @Autowired
    @Qualifier("PersonType")
    GraphQLObjectType personType;


    @Bean
    @Qualifier("InputPersonType")
    GraphQLInputType getInputPersonType() {
        return newInputObject()
                .name("PersonDtoInput")
                .field(newInputObjectField()
                        .name("id")
                        .type(GraphQLID))
                .field(newInputObjectField()
                        .name("title")
                        .type(GraphQLString))
                .field(newInputObjectField()
                        .name("bday")
                        .type(GraphQLString))
                .field(newInputObjectField()
                        .name("email")
                        .type(GraphQLString))
                .field(newInputObjectField()
                        .name("lastname")
                        .type(GraphQLString))
                .field(newInputObjectField()
                        .name("givenname")
                        .type(GraphQLString))
                .build();
    }
    @Bean
    @Qualifier("MutationType")
    public GraphQLObjectType getMutationType() {
        return newObject()
                .name("MutationType")
                .field(newFieldDefinition()
                        .name("createPerson")
                        .type(personType)
                        .argument(GraphQLArgument.newArgument()
                                .name("title")
                                .type(GraphQLString)
                                .build())
                        .argument(GraphQLArgument.newArgument()
                                .name("givenName")
                                .type(GraphQLString)
                                .build())
                        .dataFetcher(new PersonUpsertFetcher(personAdaptor))
                        .build())
                .field(newFieldDefinition()
                        .name("createPersonObject")
                        .type(personType)
                        .argument(GraphQLArgument.newArgument()
                                .name("person")
                                .type(getInputPersonType())
                                .build())
                        .dataFetcher(new PersonUpsertFetcher(personAdaptor))
                        .build())
                .field(newFieldDefinition()
                        .name("deletePerson")
                        .type(personType)
                        .argument(GraphQLArgument.newArgument()
                                .name("id")
                                .type(GraphQLID)
                                .build())
                        .dataFetcher(new PersonDeleteFetcher(personAdaptor))
                        .build())
                .build();
    }

}
