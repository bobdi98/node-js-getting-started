package com.sfdo.ngp.data.schema;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the workplace_pref_nonprofit database table.
 * 
 */
@Embeddable
public class WorkplacePrefNonprofitPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ngo_id", insertable=false, updatable=false)
	private Long ngoId;

	@Column(name="workplace_partner_id")
	private Long workplacePartnerId;

	public WorkplacePrefNonprofitPK() {
	}
	public Long getNgoId() {
		return this.ngoId;
	}
	public void setNgoId(Long ngoId) {
		this.ngoId = ngoId;
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
		if (!(other instanceof WorkplacePrefNonprofitPK)) {
			return false;
		}
		WorkplacePrefNonprofitPK castOther = (WorkplacePrefNonprofitPK)other;
		return 
			this.ngoId.equals(castOther.ngoId)
			&& this.workplacePartnerId.equals(castOther.workplacePartnerId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.ngoId.hashCode();
		hash = hash * prime + this.workplacePartnerId.hashCode();
		
		return hash;
	}
}