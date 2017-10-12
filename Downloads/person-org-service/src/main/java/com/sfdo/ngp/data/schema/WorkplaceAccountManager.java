package com.sfdo.ngp.data.schema;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the workplace_account_manager database table.
 * 
 */
@Entity
@Table(name="workplace_account_manager")
@NamedQuery(name="WorkplaceAccountManager.findAll", query="SELECT w FROM WorkplaceAccountManager w")
public class WorkplaceAccountManager implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="wp_acct_mgr_id")
	private Long wpAcctMgrId;

	//bi-directional many-to-one association to Organization
	@ManyToOne
	@JoinColumn(name="org_id")
	private Organization organization;

	//bi-directional many-to-one association to WorkplacePartner
	@OneToMany(mappedBy="workplaceAccountManager")
	private List<WorkplacePartner> workplacePartners;

	public WorkplaceAccountManager() {
	}

	public Long getWpAcctMgrId() {
		return this.wpAcctMgrId;
	}

	public void setWpAcctMgrId(Long wpAcctMgrId) {
		this.wpAcctMgrId = wpAcctMgrId;
	}

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public List<WorkplacePartner> getWorkplacePartners() {
		return this.workplacePartners;
	}

	public void setWorkplacePartners(List<WorkplacePartner> workplacePartners) {
		this.workplacePartners = workplacePartners;
	}

	public WorkplacePartner addWorkplacePartner(WorkplacePartner workplacePartner) {
		getWorkplacePartners().add(workplacePartner);
		workplacePartner.setWorkplaceAccountManager(this);

		return workplacePartner;
	}

	public WorkplacePartner removeWorkplacePartner(WorkplacePartner workplacePartner) {
		getWorkplacePartners().remove(workplacePartner);
		workplacePartner.setWorkplaceAccountManager(null);

		return workplacePartner;
	}

}