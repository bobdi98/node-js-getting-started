package com.sfdo.ngp.graphql.domain.schema;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import com.sfdo.ngp.data.schema.Person;
import com.sfdo.ngp.service.PersonAdaptor;


@Service
public class Query implements GraphQLRootResolver {

    @Autowired
    PersonAdaptor personAdaptor;
    
    public List<Person> persons() {
        return personAdaptor.getAllPersons();
    }

    public Person person(Long userId) {
        return personAdaptor.getPerson(userId);
    }

}
