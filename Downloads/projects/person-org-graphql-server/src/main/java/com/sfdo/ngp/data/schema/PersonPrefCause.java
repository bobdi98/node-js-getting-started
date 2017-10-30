package com.sfdo.ngp.data.schema;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the person_pref_cause database table.
 * 
 */
@Entity
@Table(name="person_pref_cause")
@NamedQuery(name="PersonPrefCause.findAll", query="SELECT p FROM PersonPrefCause p")
public class PersonPrefCause implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PersonPrefCausePK id;

	@Column(name="is_restricted")
	private Boolean isRestricted;

	//bi-directional many-to-one association to Cause
	@ManyToOne
	@JoinColumn(name="id", insertable=false, updatable=false)
	private Cause cause;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="id", insertable=false, updatable=false)
	private Person person;

	public PersonPrefCause() {
	}

	public PersonPrefCausePK getId() {
		return this.id;
	}

	public void setId(PersonPrefCausePK id) {
		this.id = id;
	}

	public Boolean getIsRestricted() {
		return this.isRestricted;
	}

	public void setIsRestricted(Boolean isRestricted) {
		this.isRestricted = isRestricted;
	}

	public Cause getCause() {
		return this.cause;
	}

	public void setCause(Cause cause) {
		this.cause = cause;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}