package com.sfdo.ngp.persistence;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.sfdo.ngp.data.schema.Role;

public interface RoleRepository extends CrudRepository<Role, Serializable>{

}
