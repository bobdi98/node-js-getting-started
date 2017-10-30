package com.sfdo.ngp.persistence;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.sfdo.ngp.data.schema.Identity;

public interface IdentityRepository extends CrudRepository<Identity, Serializable> {

}
