package com.sfdo.ngp.po.graphql.fetchers;

import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.http.HttpHeaders;

import com.sfdo.ngp.data.schema.Person;
import com.sfdo.ngp.po.service.PersonAdaptor;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;


/**
 * Instantiated by graphql-java library so we need to hook into Spring to get other beans.
 */
@Slf4j
public class PersonListFetcher implements DataFetcher<List<Person>>, ApplicationListener<ContextRefreshedEvent> {

    PersonAdaptor personAdaptor;

    public PersonListFetcher(PersonAdaptor personAdaptor) {
        this.personAdaptor = personAdaptor;
    }

    @Override
    public List<Person> get(DataFetchingEnvironment environment) {
        HttpHeaders headers = environment.getContext();
       // log.info("getContext() {}", headers);
       // log.info("getArguments {}", environment.getArguments());
        List<Person> allPersons = personAdaptor.getAllPersons();
        return allPersons;
    }

    // only needed for annotations schema as it instantiates fetchers outside spring
    public PersonListFetcher() {}

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    	personAdaptor = contextRefreshedEvent.getApplicationContext().getBean(PersonAdaptor.class);
    }
}
