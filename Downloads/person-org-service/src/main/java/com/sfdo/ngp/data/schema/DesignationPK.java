package com.sfdo.ngp.data.schema;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the designation database table.
 * 
 */
@Embeddable
public class DesignationPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Long id;

	@Column(insertable=false, updatable=false)
	private Long designee;

	public DesignationPK() {
	}
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getDesignee() {
		return this.designee;
	}
	public void setDesignee(Long designee) {
		this.designee = designee;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DesignationPK)) {
			return false;
		}
		DesignationPK castOther = (DesignationPK)other;
		return 
			this.id.equals(castOther.id)
			&& this.designee.equals(castOther.designee);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id.hashCode();
		hash = hash * prime + this.designee.hashCode();
		
		return hash;
	}
}