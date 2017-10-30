package com.sfdo.ngp.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sfdo.ngp.data.schema.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
    List<Person> findByLastname(String lastName);

}


