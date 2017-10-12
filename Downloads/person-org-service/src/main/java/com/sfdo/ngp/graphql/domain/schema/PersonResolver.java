package com.sfdo.ngp.graphql.domain.schema;


import java.util.List;

import org.springframework.stereotype.Service;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.sfdo.ngp.data.schema.OrgIdentity;
import com.sfdo.ngp.data.schema.Person;
import com.sfdo.ngp.data.schema.PersonRole;

/**
 * This class contains resolver methods for the "Data Class" type
 */
@Service
public class PersonResolver implements GraphQLResolver<Person> {

    public Long getPersonId(Person person) {
        return person.getId();
    }
    
    public List<PersonRole> getRoles(Person person) {
    		return person.getPersonRoles();
    }
    
    public List<OrgIdentity> getOrgIdentities(Person person) {
    		return person.getOrgIdentities();
    }

}