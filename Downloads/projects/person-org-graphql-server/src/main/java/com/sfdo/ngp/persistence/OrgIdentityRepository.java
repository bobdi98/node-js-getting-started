package com.sfdo.ngp.persistence;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.sfdo.ngp.data.schema.OrgIdentity;

public interface OrgIdentityRepository extends CrudRepository<OrgIdentity, Serializable> {

}
