package com.sfdo.ngp.po.graphql.fetchers;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.sfdo.ngp.data.schema.Organization;
import com.sfdo.ngp.po.service.PersonAdaptor;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;


/**
 * Instantiated by graphql-java library so we need to hook into Spring to get other beans.
 */
@Slf4j
public class OrganizationFetcher implements DataFetcher<Organization>, ApplicationListener<ContextRefreshedEvent> {

    PersonAdaptor personAdaptor;

    public OrganizationFetcher(PersonAdaptor userAdaptor) {
        this.personAdaptor = userAdaptor;
    }

    @Override
    public Organization get(DataFetchingEnvironment environment) {
       // log.info("getArguments {}", environment.getArguments());
        String orgId = environment.getArgument("id");
        Organization user = personAdaptor.getOrganization(new Long(orgId));
        return user;
    }

    // only needed for annotations schema as it instantiates fetchers outside spring
    public OrganizationFetcher(){}

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        personAdaptor = contextRefreshedEvent.getApplicationContext().getBean(PersonAdaptor.class);
    }
}
