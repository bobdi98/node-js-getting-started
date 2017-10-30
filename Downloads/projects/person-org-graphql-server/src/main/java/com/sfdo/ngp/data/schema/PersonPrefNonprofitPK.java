package com.sfdo.ngp.data.schema;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the person_pref_nonprofit database table.
 * 
 */
@Embeddable
public class PersonPrefNonprofitPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ngo_id", insertable=false, updatable=false)
	private Long ngoId;

	@Column(name="person_id", insertable=false, updatable=false)
	private Long personId;

	public PersonPrefNonprofitPK() {
	}
	public Long getNgoId() {
		return this.ngoId;
	}
	public void setNgoId(Long ngoId) {
		this.ngoId = ngoId;
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
		if (!(other instanceof PersonPrefNonprofitPK)) {
			return false;
		}
		PersonPrefNonprofitPK castOther = (PersonPrefNonprofitPK)other;
		return 
			this.ngoId.equals(castOther.ngoId)
			&& this.personId.equals(castOther.personId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.ngoId.hashCode();
		hash = hash * prime + this.personId.hashCode();
		
		return hash;
	}
}