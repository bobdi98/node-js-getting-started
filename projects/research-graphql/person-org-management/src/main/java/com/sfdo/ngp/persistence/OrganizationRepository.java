package com.sfdo.ngp.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sfdo.ngp.data.schema.Organization;

@Repository
public interface OrganizationRepository extends CrudRepository<Organization, Long> {

}
