package com.sfdo.ngp.po.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.SchemaParser;
import com.sfdo.ngp.data.schema.OrgIdentity;
import com.sfdo.ngp.data.schema.Organization;
import com.sfdo.ngp.data.schema.Person;
import com.sfdo.ngp.data.schema.PersonRole;
import com.sfdo.ngp.data.schema.PersonRolePK;
import com.sfdo.ngp.data.schema.Processor;
import com.sfdo.ngp.data.schema.Role;
import com.sfdo.ngp.data.schema.WorkplacePartner;
import com.sfdo.ngp.po.graphql.domain.schema.Mutation;
import com.sfdo.ngp.po.graphql.domain.schema.OrderItemResolver;
import com.sfdo.ngp.po.graphql.domain.schema.OrderResolver;
import com.sfdo.ngp.po.graphql.domain.schema.OrgIdentityDtoInput;
import com.sfdo.ngp.po.graphql.domain.schema.OrganizationDtoInput;
import com.sfdo.ngp.po.graphql.domain.schema.OrganizationResolver;
import com.sfdo.ngp.po.graphql.domain.schema.PersonDtoInput;
import com.sfdo.ngp.po.graphql.domain.schema.PersonResolver;
import com.sfdo.ngp.po.graphql.domain.schema.ProductResolver;
import com.sfdo.ngp.po.graphql.domain.schema.Query;
import com.sfdo.ngp.po.graphql.domain.schema.RoleDtoInput;
import com.sfdo.ngp.po.graphql.domain.schema.UserResolver;

import graphql.GraphQL;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Configuration
@Component
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
    ProductResolver productResolver;

    @Autowired
    UserResolver userResolver;

    @Autowired
    OrderResolver orderResolver;

    @Autowired
    OrderItemResolver orderItemResolver;
    
    @Autowired
    PersonResolver personResolver;

    @Autowired
    OrganizationResolver orgResolver;
    
    @Bean
    public GraphQLSchema getGraphQLSchema() throws IllegalAccessException, NoSuchMethodException, InstantiationException {
        GraphQLSchema schema = null;
        GraphQLObjectType queryObject = null;
        GraphQLObjectType mutationObject = null;
        schemaType = System.getProperty("graphql.schema", schemaType);
        System.out.println("Loading GraphQL schema type " + schemaType.toUpperCase());
        switch (schemaType) {

            case "schema":
                // this uses combination of schema file and resolvers...
                SchemaParser file = SchemaParser.newParser()
                        .file("schema.graphql")
                        .resolvers(
                                queryRootResolver,
                                mutationRootResolver,
                                personResolver,
                                orgResolver
                                
                        )
                        .dictionary(
                                Person.class,
                                PersonDtoInput.class,
                                OrganizationDtoInput.class,
                                RoleDtoInput.class,
                                Organization.class,
                                OrgIdentity.class,
                                PersonRole.class,
                                PersonRolePK.class,
                                WorkplacePartner.class,
                                Processor.class,
                                Role.class,
                                RoleDtoInput.class,
                                OrgIdentityDtoInput.class
                        )
                        .build();

                schema = file.makeExecutableSchema();
                break;

            case "manual":
            default:
                // this uses manual schema definition (boilerplate heavy)
                //queryObject = queryType;
                //mutationObject = mutationType;
                //schema = newSchema()
                  //      .query(queryObject)
                   //     .mutation(mutationObject)
                    //    .build();
                break;
        }
        System.out.println("schema={}" + schema);
        return schema;
    }

    @Bean
    public GraphQL createGraphQLSchema() throws IllegalAccessException, NoSuchMethodException, InstantiationException {
        return GraphQL.newGraphQL(getGraphQLSchema()).build();
    }
}
