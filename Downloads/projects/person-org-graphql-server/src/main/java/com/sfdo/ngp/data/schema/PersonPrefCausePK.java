package com.sfdo.ngp.data.schema;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the person_pref_cause database table.
 * 
 */
@Embeddable
public class PersonPrefCausePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="cause_id", insertable=false, updatable=false)
	private Long causeId;

	@Column(name="person_id", insertable=false, updatable=false)
	private Long personId;

	public PersonPrefCausePK() {
	}
	public Long getCauseId() {
		return this.causeId;
	}
	public void setCauseId(Long causeId) {
		this.causeId = causeId;
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
		if (!(other instanceof PersonPrefCausePK)) {
			return false;
		}
		PersonPrefCausePK castOther = (PersonPrefCausePK)other;
		return 
			this.causeId.equals(castOther.causeId)
			&& this.personId.equals(castOther.personId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.causeId.hashCode();
		hash = hash * prime + this.personId.hashCode();
		
		return hash;
	}
}