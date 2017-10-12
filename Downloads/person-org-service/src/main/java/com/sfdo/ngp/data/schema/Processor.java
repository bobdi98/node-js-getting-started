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


/**
 * The persistent class for the processor database table.
 * 
 */
@Entity
@NamedQuery(name="Processor.findAll", query="SELECT p FROM Processor p")
public class Processor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="processor_id")
	private Long processorId;

	@Column(name="is_active")
	private Boolean isActive;

	@Column(name="stripe_connect_id")
	private String stripeConnectId;

	//bi-directional many-to-one association to Campaign
	@OneToMany(mappedBy="processor")
	private List<Campaign> campaigns;

	//bi-directional many-to-one association to Organization
	@ManyToOne
	@JoinColumn(name="org_id")
	private Organization organization;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="processor")
	private List<Transaction> transactions;

	//bi-directional many-to-one association to WorkplacePartner
	@OneToMany(mappedBy="processor")
	private List<WorkplacePartner> workplacePartners;

	public Processor() {
	}

	public Long getProcessorId() {
		return this.processorId;
	}

	public void setProcessorId(Long processorId) {
		this.processorId = processorId;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getStripeConnectId() {
		return this.stripeConnectId;
	}

	public void setStripeConnectId(String stripeConnectId) {
		this.stripeConnectId = stripeConnectId;
	}

	public List<Campaign> getCampaigns() {
		return this.campaigns;
	}

	public void setCampaigns(List<Campaign> campaigns) {
		this.campaigns = campaigns;
	}

	public Campaign addCampaign(Campaign campaign) {
		getCampaigns().add(campaign);
		campaign.setProcessor(this);

		return campaign;
	}

	public Campaign removeCampaign(Campaign campaign) {
		getCampaigns().remove(campaign);
		campaign.setProcessor(null);

		return campaign;
	}

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Transaction addTransaction(Transaction transaction) {
		getTransactions().add(transaction);
		transaction.setProcessor(this);

		return transaction;
	}

	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setProcessor(null);

		return transaction;
	}

	public List<WorkplacePartner> getWorkplacePartners() {
		return this.workplacePartners;
	}

	public void setWorkplacePartners(List<WorkplacePartner> workplacePartners) {
		this.workplacePartners = workplacePartners;
	}

	public WorkplacePartner addWorkplacePartner(WorkplacePartner workplacePartner) {
		getWorkplacePartners().add(workplacePartner);
		workplacePartner.setProcessor(this);

		return workplacePartner;
	}

	public WorkplacePartner removeWorkplacePartner(WorkplacePartner workplacePartner) {
		getWorkplacePartners().remove(workplacePartner);
		workplacePartner.setProcessor(null);

		return workplacePartner;
	}

}