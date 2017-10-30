package com.sfdo.ngp.po.graphql.fetchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

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
public class PersonUpsertFetcher implements DataFetcher<Person>, ApplicationListener<ContextRefreshedEvent> {

    PersonAdaptor personAdaptor;

    public PersonUpsertFetcher(PersonAdaptor personAdaptor) {
        this.personAdaptor = personAdaptor;
    }

    public static final List<String> EMPTY_CATEGORIES = new ArrayList<>();

    @Override
    public Person get(DataFetchingEnvironment environment) {
        //log.info("getArguments {}", environment.getArguments());
        Object inputObject = environment.getArgument("person");
        Person newPerson = new Person();
        Function<Object, String> safeString = (o) -> o == null ? null : o.toString();
        Function<Object, Long> safeLong = (o) -> (o instanceof Number) ? Long.valueOf((long)o) : null;
        Function<Object, List<String>> safeCategories = (o) -> (EMPTY_CATEGORIES.getClass().isInstance(o)) ? (List<String>) o : null;
        if (inputObject == null) {
            newPerson.setId(safeLong.apply(environment.getArgument("Id")));
            newPerson.setAddressline1(environment.getArgument("addressLine1"));
            newPerson.setAddressline2(environment.getArgument("addressLine2"));
            newPerson.setCity(environment.getArgument("city"));
            newPerson.setFirstname(environment.getArgument("firstName"));
            newPerson.setGender(environment.getArgument("gender"));
            newPerson.setLastname(environment.getArgument("lastName"));
            newPerson.setMiddlename(environment.getArgument("middleName"));
            newPerson.setNickname(environment.getArgument("nickName"));
            newPerson.setPersonalemail(environment.getArgument("personalEmail"));
            newPerson.setPersonalphone(environment.getArgument("personalPhone"));
            newPerson.setSalutation(environment.getArgument("salutation"));
            newPerson.setState(environment.getArgument("state"));
            newPerson.setSuffix(environment.getArgument("suffix"));
        } else {
            Map<String, Object> inputMap = (Map<String, Object>) inputObject;
            newPerson.setId(safeLong.apply(environment.getArgument("Id")));
            newPerson.setAddressline1(safeString.apply(inputMap.get("addressLine1")));
            newPerson.setAddressline2(safeString.apply(inputMap.get("addressLine2")));
            newPerson.setCity(safeString.apply(inputMap.get("city")));
            newPerson.setFirstname(safeString.apply(inputMap.get("firstName")));
            newPerson.setGender(safeString.apply(inputMap.get("gender")));
            newPerson.setLastname(safeString.apply(inputMap.get("lastName")));
            newPerson.setMiddlename(safeString.apply(inputMap.get("middleName")));
            newPerson.setNickname(safeString.apply(inputMap.get("nickName")));
            newPerson.setPersonalemail(safeString.apply(inputMap.get("personalEmail")));
            newPerson.setPersonalphone(safeString.apply(inputMap.get("personalPhone")));
            newPerson.setSalutation(safeString.apply(inputMap.get("salutation")));
            newPerson.setState(safeString.apply(inputMap.get("state")));
            newPerson.setSuffix(safeString.apply(inputMap.get("suffix")));

        }
        //log.info("saving new product={}", newPerson);
        Person savedPerson = personAdaptor.upsertPerson(newPerson);
        //log.info("saved new product={}", savedProduct);
        return savedPerson;
    }

    // only needed for annotations schema as it instantiates fetchers outside spring
    public PersonUpsertFetcher() {
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        personAdaptor = contextRefreshedEvent.getApplicationContext().getBean(PersonAdaptor.class);
    }
}
