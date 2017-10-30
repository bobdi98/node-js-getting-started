package com.sfdo.ngp.persistence;

import java.io.Serializable;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.sfdo.ngp.data.schema.PersonRole;

public interface PersonRoleRepository extends CrudRepository<PersonRole, Serializable> {

	@Modifying
    @Query(value = "insert into ngp.person_roles (role_id,person_id, org_id) VALUES (:role_id,:person_id, :org_id)", nativeQuery=true)
    @Transactional
    void createPersonRole(@Param("role_id")Long role_id,
    				@Param("person_id")Long person_id, 
    				@Param("org_id") Long org_id);
}
