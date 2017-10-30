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
 * The persistent class for the workplace_partner database table.
 * 
 */
@Entity
@Table(name="workplace_partner")
@NamedQuery(name="WorkplacePartner.findAll", query="SELECT w FROM WorkplacePartner w")
public class WorkplacePartner implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="workplace_partner_id")
	private Long workplacePartnerId;

	private String divison;

	private Long reseller;

	//bi-directional many-to-one association to OrgIdentity
	@OneToMany(mappedBy="workplacePartner")
	private List<OrgIdentity> orgIdentities;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="workplacePartner")
	private List<Transaction> transactions;

	//bi-directional many-to-one association to Organization
	@ManyToOne
	@JoinColumn(name="org_id")
	private Organization organization;

	//bi-directional many-to-one association to Processor
	@ManyToOne
	@JoinColumn(name="default_processor")
	private Processor processor;

	//bi-directional many-to-one association to WorkplaceAccountManager
	@ManyToOne
	@JoinColumn(name="acct_manager_id")
	private WorkplaceAccountManager workplaceAccountManager;

	//bi-directional many-to-one association to WorkplacePrefCause
	@OneToMany(mappedBy="workplacePartner")
	private List<WorkplacePrefCause> workplacePrefCauses;

	public WorkplacePartner() {
	}

	public Long getWorkplacePartnerId() {
		return this.workplacePartnerId;
	}

	public void setWorkplacePartnerId(Long workplacePartnerId) {
		this.workplacePartnerId = workplacePartnerId;
	}

	public String getDivison() {
		return this.divison;
	}

	public void setDivison(String divison) {
		this.divison = divison;
	}

	public Long getReseller() {
		return this.reseller;
	}

	public void setReseller(Long reseller) {
		this.reseller = reseller;
	}

	public List<OrgIdentity> getOrgIdentities() {
		return this.orgIdentities;
	}

	public void setOrgIdentities(List<OrgIdentity> orgIdentities) {
		this.orgIdentities = orgIdentities;
	}

	public OrgIdentity addOrgIdentity(OrgIdentity orgIdentity) {
		getOrgIdentities().add(orgIdentity);
		orgIdentity.setWorkplacePartner(this);

		return orgIdentity;
	}

	public OrgIdentity removeOrgIdentity(OrgIdentity orgIdentity) {
		getOrgIdentities().remove(orgIdentity);
		orgIdentity.setWorkplacePartner(null);

		return orgIdentity;
	}

	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Transaction addTransaction(Transaction transaction) {
		getTransactions().add(transaction);
		transaction.setWorkplacePartner(this);

		return transaction;
	}

	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setWorkplacePartner(null);

		return transaction;
	}

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Processor getProcessor() {
		return this.processor;
	}

	public void setProcessor(Processor processor) {
		this.processor = processor;
	}

	public WorkplaceAccountManager getWorkplaceAccountManager() {
		return this.workplaceAccountManager;
	}

	public void setWorkplaceAccountManager(WorkplaceAccountManager workplaceAccountManager) {
		this.workplaceAccountManager = workplaceAccountManager;
	}

	public List<WorkplacePrefCause> getWorkplacePrefCauses() {
		return this.workplacePrefCauses;
	}

	public void setWorkplacePrefCauses(List<WorkplacePrefCause> workplacePrefCauses) {
		this.workplacePrefCauses = workplacePrefCauses;
	}

	public WorkplacePrefCause addWorkplacePrefCaus(WorkplacePrefCause workplacePrefCaus) {
		getWorkplacePrefCauses().add(workplacePrefCaus);
		workplacePrefCaus.setWorkplacePartner(this);

		return workplacePrefCaus;
	}

	public WorkplacePrefCause removeWorkplacePrefCaus(WorkplacePrefCause workplacePrefCaus) {
		getWorkplacePrefCauses().remove(workplacePrefCaus);
		workplacePrefCaus.setWorkplacePartner(null);

		return workplacePrefCaus;
	}
	
	public Long getOrgId() {
		return getOrganization().getId();
	}
	
	public Long getProcessorId() {
		return getProcessor().getProcessorId();
	}

}