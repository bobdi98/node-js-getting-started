package com.sfdo.ngp.data.schema;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the workplace_pref_cause database table.
 * 
 */
@Embeddable
public class WorkplacePrefCausePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="cause_id", insertable=false, updatable=false)
	private Long causeId;

	@Column(name="workplace_partner_id", insertable=false, updatable=false)
	private Long workplacePartnerId;

	public WorkplacePrefCausePK() {
	}
	public Long getCauseId() {
		return this.causeId;
	}
	public void setCauseId(Long causeId) {
		this.causeId = causeId;
	}
	public Long getWorkplacePartnerId() {
		return this.workplacePartnerId;
	}
	public void setWorkplacePartnerId(Long workplacePartnerId) {
		this.workplacePartnerId = workplacePartnerId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof WorkplacePrefCausePK)) {
			return false;
		}
		WorkplacePrefCausePK castOther = (WorkplacePrefCausePK)other;
		return 
			this.causeId.equals(castOther.causeId)
			&& this.workplacePartnerId.equals(castOther.workplacePartnerId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.causeId.hashCode();
		hash = hash * prime + this.workplacePartnerId.hashCode();
		
		return hash;
	}
}