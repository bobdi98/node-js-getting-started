package com.sfdo.ngp.data.schema;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the person_roles database table.
 * 
 */
@Entity
@Table(name="person_roles")
@NamedQuery(name="PersonRole.findAll", query="SELECT p FROM PersonRole p")
public class PersonRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PersonRolePK id;

	//bi-directional many-to-one association to Organization
	@ManyToOne
	@JoinColumn(name="org_id", insertable=false)
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