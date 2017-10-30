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
 * The persistent class for the workplace_pref_nonprofit database table.
 * 
 */
@Entity
@Table(name="workplace_pref_nonprofit")
@NamedQuery(name="WorkplacePrefNonprofit.findAll", query="SELECT w FROM WorkplacePrefNonprofit w")
public class WorkplacePrefNonprofit implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WorkplacePrefNonprofitPK id;

	@Column(name="is_restricted")
	private Boolean isRestricted;

	//bi-directional many-to-one association to Nonprofit
	@ManyToOne
	@JoinColumn(name="ngo_id", insertable=false, updatable=false)
	private Nonprofit nonprofit;

	public WorkplacePrefNonprofit() {
	}

	public WorkplacePrefNonprofitPK getId() {
		return this.id;
	}

	public void setId(WorkplacePrefNonprofitPK id) {
		this.id = id;
	}

	public Boolean getIsRestricted() {
		return this.isRestricted;
	}

	public void setIsRestricted(Boolean isRestricted) {
		this.isRestricted = isRestricted;
	}

	public Nonprofit getNonprofit() {
		return this.nonprofit;
	}

	public void setNonprofit(Nonprofit nonprofit) {
		this.nonprofit = nonprofit;
	}

}