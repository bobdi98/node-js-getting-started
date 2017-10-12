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
 * The persistent class for the workplace_pref_cause database table.
 * 
 */
@Entity
@Table(name="workplace_pref_cause")
@NamedQuery(name="WorkplacePrefCause.findAll", query="SELECT w FROM WorkplacePrefCause w")
public class WorkplacePrefCause implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WorkplacePrefCausePK id;

	@Column(name="is_restricted")
	private Boolean isRestricted;

	//bi-directional many-to-one association to Cause
	@ManyToOne
	@JoinColumn(name="id", insertable=false, updatable=false)
	private Cause cause;

	//bi-directional many-to-one association to WorkplacePartner
	@ManyToOne
	@JoinColumn(name="workplace_partner_id", insertable=false, updatable=false)
	private WorkplacePartner workplacePartner;

	public WorkplacePrefCause() {
	}

	public WorkplacePrefCausePK getId() {
		return this.id;
	}

	public void setId(WorkplacePrefCausePK id) {
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

	public WorkplacePartner getWorkplacePartner() {
		return this.workplacePartner;
	}

	public void setWorkplacePartner(WorkplacePartner workplacePartner) {
		this.workplacePartner = workplacePartner;
	}

}