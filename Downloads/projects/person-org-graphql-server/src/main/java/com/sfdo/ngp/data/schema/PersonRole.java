package com.sfdo.ngp.data.schema;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Type;


/**
 * The persistent class for the person_roles database table.
 * 
 */
@Entity
@Table(name="person_roles")
@NamedQuery(name="PersonRole.findAll", query="SELECT p FROM PersonRole p")
public class PersonRole implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	public PersonRole(PersonRolePK id, Long orgId) {
		super();
		this.org_id = orgId;
		//this.organization = organization;
	}
	
	@Type(type="java.lang.Long")
	@Column(name="org_id")
	private Long org_id;
	
	

	public Long getOrg_id() {
		return org_id;
	}


	public void setOrg_id(Long org_id) {
		this.org_id = org_id;
	}

	@EmbeddedId
	private PersonRolePK id;

	//bi-directional many-to-one association to Organization
	@ManyToOne
	@JoinColumn(name="org_id",  insertable=false, updatable=false)
	private Organization organization;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="Id", insertable=false, updatable=false)
	private Person person;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="Id", insertable=false, updatable=false)
	private Role role;

	public PersonRole() {
	}

	
	public PersonRolePK getId() {
		return this.id;
	}

	public void setId(PersonRolePK id) {
		this.id = id;
	}

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}