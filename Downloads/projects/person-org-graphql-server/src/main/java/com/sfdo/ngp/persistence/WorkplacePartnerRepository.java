package com.sfdo.ngp.persistence;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.sfdo.ngp.data.schema.WorkplacePartner;

public interface WorkplacePartnerRepository extends CrudRepository<WorkplacePartner, Serializable> {

}
