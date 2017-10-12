package com.sfdo.ngp.configuration;

import static graphql.schema.GraphQLSchema.newSchema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.coxautodev.graphql.tools.SchemaParser;
import com.sfdo.ngp.data.schema.Person;
import com.sfdo.ngp.graphql.domain.schema.Mutation;
import com.sfdo.ngp.graphql.domain.schema.PersonDtoInput;
import com.sfdo.ngp.graphql.domain.schema.PersonResolver;
import com.sfdo.ngp.graphql.domain.schema.Query;

import graphql.GraphQL;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Configuration
public class GraphQLConfiguration {

    @Value("${graphql.schema:manual}")
    String schemaType;

    @Autowired
    @Qualifier("QueryType")
    GraphQLObjectType queryType;

    @Autowired
    @Qualifier("MutationType")
    GraphQLObjectType mutationType;

    @Autowired
    Query queryRootResolver;

    @Autowired
    Mutation mutationRootResolver;

    @Autowired
    PersonResolver personResolver;


    @Bean
    public GraphQLSchema getGraphQLSchema() throws IllegalAccessException, NoSuchMethodException, InstantiationException {
        GraphQLSchema schema = null;
        GraphQLObjectType queryObject = null;
        GraphQLObjectType mutationObject = null;
        schemaType = System.getProperty("graphql.schema", schemaType);
        log.info("Loading GraphQL schema type " + schemaType.toUpperCase());
        switch (schemaType) {

        case "schema":
            // this uses combination of schema file and resolvers...
            SchemaParser file = SchemaParser.newParser()
                    .file("schema.graphql")
                    .resolvers(
                            queryRootResolver,
                            mutationRootResolver,
                            personResolver
                    )
                    .dictionary(
                            PersonDtoInput.class,
                            Person.class
                    )
                    .build();

            schema = file.makeExecutableSchema();
            break;

            case "manual":
            default:
                // this uses manual schema definition (boilerplate heavy)
                queryObject = queryType;
                mutationObject = mutationType;
                schema = newSchema()
                        .query(queryObject)
                        .mutation(mutationObject)
                        .build();
                break;
        }
        log.info("schema={}", schema);
        return schema;
    }

    @Bean
    public GraphQL createGraphQLSchema() throws IllegalAccessException, NoSuchMethodException, InstantiationException {
        return GraphQL.newGraphQL(getGraphQLSchema()).build();
    }
}