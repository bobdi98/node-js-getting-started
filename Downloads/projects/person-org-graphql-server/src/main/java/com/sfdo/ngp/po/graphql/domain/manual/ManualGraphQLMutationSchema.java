package com.sfdo.ngp.po.graphql.domain.manual;

import static graphql.Scalars.GraphQLFloat;
import static graphql.Scalars.GraphQLID;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLInputObjectField.newInputObjectField;
import static graphql.schema.GraphQLInputObjectType.newInputObject;
import static graphql.schema.GraphQLList.list;
import static graphql.schema.GraphQLObjectType.newObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sfdo.ngp.po.graphql.fetchers.OrganizationUpsertFetcher;
import com.sfdo.ngp.po.graphql.fetchers.PersonDeleteFetcher;
import com.sfdo.ngp.po.graphql.fetchers.PersonUpsertFetcher;
import com.sfdo.ngp.po.graphql.fetchers.ProductDeleteFetcher;
import com.sfdo.ngp.po.graphql.fetchers.ProductUpsertFetcher;
import com.sfdo.ngp.po.service.PersonAdaptor;
import com.sfdo.ngp.po.service.ProductAdaptor;

import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLInputType;
import graphql.schema.GraphQLObjectType;


@Configuration
public class ManualGraphQLMutationSchema {

    @Autowired
    ProductAdaptor productAdaptor;

    @Autowired
    @Qualifier("ProductType")
    GraphQLObjectType productType;

    @Autowired
    PersonAdaptor personAdaptor;

    @Autowired
    @Qualifier("PersonType")
    GraphQLObjectType personType;

    @Autowired
    @Qualifier("OrgType")
    GraphQLObjectType orgType;
    
    

    @Bean
    @Qualifier("InputPersonType")
    GraphQLInputType getInputPersonType() {
        return newInputObject()
                .name("PersonDtoInput")
                .field(newInputObjectField()
                        .name("id")
                        .type(GraphQLID))
                .field(newInputObjectField()
                        .name("lastname")
                        .type(GraphQLString))
                .field(newInputObjectField()
                        .name("middlename")
                        .type(GraphQLString))
                .field(newInputObjectField()
                        .name("nickname")
                        .type(GraphQLString))
                .field(newInputObjectField()
                        .name("city")
                        .type(GraphQLString))
                .field(newInputObjectField()
                        .name("addressline1")
                        .type(GraphQLString))
                .field(newInputObjectField()
                        .name("addressline2")
                        .type(GraphQLString))
                .field(newInputObjectField()
                        .name("state")
                        .type(GraphQLString))
                .field(newInputObjectField()
                        .name("suffix")
                        .type(GraphQLString))
                .field(newInputObjectField()
                        .name("salutation")
                        .type(GraphQLString))
                .field(newInputObjectField()
                        .name("postalcode")
                        .type(GraphQLString))
                .field(newInputObjectField()
                        .name("postalcodeext")
                        .type(GraphQLString))
                .field(newInputObjectField()
                        .name("gender")
                        .type(GraphQLString))
                .field(newInputObjectField()
                        .name("firstname")
                        .type(GraphQLString))
                .build();
    }
    @Bean
    @Qualifier("InputOrgType")
    GraphQLInputType getInputOrgType() {
        return newInputObject()
                .name("OrganizationDtoInput")
                .field(newInputObjectField()
                        .name("id")
                        .type(GraphQLID))
                .field(newInputObjectField()
                        .name("name")
                        .type(GraphQLString))
                .field(newInputObjectField()
                        .name("dba")
                        .type(GraphQLString))
                .field(newInputObjectField()
                        .name("externalId")
                        .type(GraphQLString))
                .field(newInputObjectField()
                        .name("city")
                        .type(GraphQLString))
                .field(newInputObjectField()
                        .name("state")
                        .type(GraphQLString))
                .field(newInputObjectField()
                        .name("postalCode")
                        .type(GraphQLString))
                .field(newInputObjectField()
                        .name("postalCodeExt")
                        .type(GraphQLString))
                .field(newInputObjectField()
                        .name("country")
                        .type(GraphQLString))
                .field(newInputObjectField()
                        .name("phone")
                        .type(GraphQLString))
                .field(newInputObjectField()
                        .name("website")
                        .type(GraphQLString))
                .field(newInputObjectField()
                        .name("defaultLanguage")
                        .type(GraphQLString))
                .field(newInputObjectField()
                        .name("defaultCurrency")
                        .type(GraphQLString))
                .field(newInputObjectField()
                        .name("timeZone")
                        .type(GraphQLString))
                .build();
    }
    /*
    @Bean
    @Qualifier("InputPersonRoleType")
    GraphQLInputType getInputPersonRoleType() {
        return newInputObject()
                .name("PersonRoleDtoInput")
                .field(newInputObjectField()
                        .name("id")
                        .type(getInputPersonRolePKType()))
                .field(newInputObjectField()
                        .name("organization")
                        .type(getInputOrgType()))
               .build();
    }

    @Bean
    @Qualifier("InputPersonRolePKType")
    GraphQLInputType getInputPersonRolePKType() {
        return newInputObject()
                .name("PersonRolePKDtoInput")
                .field(newInputObjectField()
                        .name("personId")
                        .type(GraphQLID))
                .field(newInputObjectField()
                        .name("roleId")
                        .type(GraphQLID))
                .build();
    }
    
    @Bean
    @Qualifier("InputRoleType")
    GraphQLInputType getInputRoleType() {
        return newInputObject()
                .name("RoleDtoInput")
                .field(newInputObjectField()
                        .name("id")
                        .type(GraphQLID))
                .field(newInputObjectField()
                        .name("name")
                        .type(GraphQLString))
                .field(newInputObjectField()
                        .name("description")
                        .type(GraphQLString))
                .build();
    }
    
    */
    @Bean
    @Qualifier("MutationType")
    public GraphQLObjectType getMutationType() {
        return newObject()
                .name("MutationType")
                .field(newFieldDefinition()
                        .name("createProduct")
                        .type(productType)
                        .argument(GraphQLArgument.newArgument()
                                .name("name")
                                .type(GraphQLString)
                                .build())
                        .argument(GraphQLArgument.newArgument()
                                .name("price")
                                .type(GraphQLFloat)
                                .build())
                        .dataFetcher(new ProductUpsertFetcher(productAdaptor))
                        .build())
                .field(newFieldDefinition()
                        .name("createProductObject")
                        .type(productType)
                        .argument(GraphQLArgument.newArgument()
                                .name("product")
                                .type(getInputProductType())
                                .build())
                        .dataFetcher(new ProductUpsertFetcher(productAdaptor))
                        .build())
                .field(newFieldDefinition()
                        .name("deleteProduct")
                        .type(productType)
                        .argument(GraphQLArgument.newArgument()
                                .name("productId")
                                .type(GraphQLID)
                                .build())
                        .dataFetcher(new ProductDeleteFetcher(productAdaptor))
                        .build())
                .field(newFieldDefinition()
                        .name("deletePerson")
                        .type(productType)
                        .argument(GraphQLArgument.newArgument()
                                .name("Id")
                                .type(GraphQLID)
                                .build())
                        .dataFetcher(new PersonDeleteFetcher(personAdaptor))
                        .build())
                .field(newFieldDefinition()
                        .name("createPerson")
                        .type(productType)
                        .argument(GraphQLArgument.newArgument()
                                .name("firstname")
                                .type(GraphQLString)
                                .build())
                        .argument(GraphQLArgument.newArgument()
                                .name("lastname")
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
                        .name("createOrganizationObject")
                        .type(orgType)
                        .argument(GraphQLArgument.newArgument()
                                .name("organization")
                                .type(getInputOrgType())
                                .build())
                        .dataFetcher(new OrganizationUpsertFetcher(personAdaptor))
                        .build())
                .build();
    }

    @Bean
    @Qualifier("InputProductType")
    GraphQLInputType getInputProductType() {
        return newInputObject()
                .name("ProductDtoInput")
                .field(newInputObjectField()
                        .name("productId")
                        .type(GraphQLID))
                .field(newInputObjectField()
                        .name("name")
                        .type(GraphQLString))
                .field(newInputObjectField()
                        .name("brand")
                        .type(GraphQLString))
                .field(newInputObjectField()
                        .name("description")
                        .type(GraphQLString))
                .field(newInputObjectField()
                        .name("category")
                        .type(list(GraphQLString)))
                .field(newInputObjectField()
                        .name("price")
                        .type(GraphQLFloat))
                .build();
    }

 
}
