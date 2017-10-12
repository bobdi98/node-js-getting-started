package com.sfdo.ngp.graphql.fetchers;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.sfdo.ngp.data.schema.Person;
import com.sfdo.ngp.service.PersonAdaptor;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;


/**
 * Instantiated by graphql-java library so we need to hook into Spring to get other beans.
 */
@Slf4j
public class PersonDeleteFetcher implements DataFetcher<Person>, ApplicationListener<ContextRefreshedEvent> {

    PersonAdaptor personAdaptor;

    public PersonDeleteFetcher(PersonAdaptor personAdaptor) {
        this.personAdaptor = personAdaptor;
    }

    @Override
    public Person get(DataFetchingEnvironment environment) {
        log.info("getArguments {}", environment.getArguments());
        String personId = environment.getArgument("id");
        Person deletedPerson = personAdaptor.deletePerson(new Long(personId));
        log.info("deleted product={}", deletedPerson);
        return deletedPerson;
    }

    // only needed for annotations schema as it instantiates fetchers outside spring
    public PersonDeleteFetcher() {
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        personAdaptor = contextRefreshedEvent.getApplicationContext().getBean(PersonAdaptor.class);
    }
}
