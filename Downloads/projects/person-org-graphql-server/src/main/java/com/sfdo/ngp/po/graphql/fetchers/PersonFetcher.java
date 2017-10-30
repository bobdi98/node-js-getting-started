package com.sfdo.ngp.po.graphql.fetchers;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.sfdo.ngp.data.schema.Person;
import com.sfdo.ngp.po.service.PersonAdaptor;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;


/**
 * Instantiated by graphql-java library so we need to hook into Spring to get other beans.
 */
@Slf4j
public class PersonFetcher implements DataFetcher<Person>, ApplicationListener<ContextRefreshedEvent> {

    PersonAdaptor personAdaptor;

    public PersonFetcher(PersonAdaptor userAdaptor) {
        this.personAdaptor = userAdaptor;
    }

    @Override
    public Person get(DataFetchingEnvironment environment) {
       // log.info("getArguments {}", environment.getArguments());
        String userId = environment.getArgument("id");
        Person user = personAdaptor.getPerson(new Long(userId));
        return user;
    }

    // only needed for annotations schema as it instantiates fetchers outside spring
    public PersonFetcher(){}

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        personAdaptor = contextRefreshedEvent.getApplicationContext().getBean(PersonAdaptor.class);
    }
}
