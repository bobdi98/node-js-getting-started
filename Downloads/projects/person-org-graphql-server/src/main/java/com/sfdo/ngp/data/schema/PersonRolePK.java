package com.sfdo.ngp.data.schema;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the person_roles database table.
 * 
 */
@Embeddable
public class PersonRolePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

/*	private Long id;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}*/

	@Column(name="role_id", insertable=false, updatable=false)
	private Long roleId;

	@Column(name="person_id", insertable=false, updatable=false)
	private Long personId;

	public PersonRolePK() {
	}
	
	public PersonRolePK(Long roleId, Long personId) {
		this.roleId = roleId;
		this.personId = personId;
	}
	
	
	public Long getRoleId() {
		return this.roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getPersonId() {
		return this.personId;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PersonRolePK)) {
			return false;
		}
		PersonRolePK castOther = (PersonRolePK)other;
		return 
			this.roleId.equals(castOther.roleId)
			&& this.personId.equals(castOther.personId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.roleId.hashCode();
		hash = hash * prime + this.personId.hashCode();
		
		return hash;
	}
}