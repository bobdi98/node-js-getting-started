package com.sfdo.ngp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.sfdo.ngp.configuration.DataSourceProperties;
import com.sfdo.ngp.data.schema.Organization;
import com.sfdo.ngp.data.schema.Person;
import com.sfdo.ngp.persistence.OrganizationRepository;
import com.sfdo.ngp.persistence.PersonRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service()
public class PersonAdaptor {

    @Autowired
    DataSourceProperties properties;
    
    @Autowired
    PersonRepository personRepository;


    @Autowired
    OrganizationRepository orgRepository;

    public List<Person> getAllPersons() {
	        
    		return Lists.newArrayList(this.personRepository.findAll());
    }

    public Person getPerson(Long personId) {
        return personRepository.findOne(personId);
    }

    public Person upsertPerson(Person person) {
    		personRepository.save(person);
        return person;
    }

    public Person deletePerson(Long personId) {
        personRepository.delete(personId);
    		return null;
    }
    
    public Organization getOrganization(Long orgId) {
    		return orgRepository.findOne(orgId);
    }

}

