package com.sfdo.ngp.data.schema;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the nonprofit database table.
 * 
 */
@Entity
@NamedQuery(name="Nonprofit.findAll", query="SELECT n FROM Nonprofit n")
public class Nonprofit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="npo_id")
	private Long npoId;

	@Column(name="account_code")
	private String accountCode;

	@Column(name="created_by")
	private Long createdBy;

	@Temporal(TemporalType.DATE)
	@Column(name="created_date")
	private Date createdDate;

	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="last_modified_date")
	private Date lastModifiedDate;

	private String name;

	@Column(name="stripe_connect_id")
	private String stripeConnectId;

	//bi-directional many-to-one association to Designation
	@OneToMany(mappedBy="nonprofit")
	private List<Designation> designations;

	//bi-directional many-to-one association to Organization
	@ManyToOne
	@JoinColumn(name="org_id")
	private Organization organization;

	//bi-directional many-to-many association to PaymentType
	@ManyToMany(mappedBy="nonprofits")
	private List<PaymentType> paymentTypes;

	//bi-directional many-to-one association to PersonPrefNonprofit
	@OneToMany(mappedBy="nonprofit")
	private List<PersonPrefNonprofit> personPrefNonprofits;

	//bi-directional many-to-one association to WorkplacePrefNonprofit
	@OneToMany(mappedBy="nonprofit")
	private List<WorkplacePrefNonprofit> workplacePrefNonprofits;

	public Nonprofit() {
	}

	public Long getNpoId() {
		return this.npoId;
	}

	public void setNpoId(Long npoId) {
		this.npoId = npoId;
	}

	public String getAccountCode() {
		return this.accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStripeConnectId() {
		return this.stripeConnectId;
	}

	public void setStripeConnectId(String stripeConnectId) {
		this.stripeConnectId = stripeConnectId;
	}

	public List<Designation> getDesignations() {
		return this.designations;
	}

	public void setDesignations(List<Designation> designations) {
		this.designations = designations;
	}

	public Designation addDesignation(Designation designation) {
		getDesignations().add(designation);
		designation.setNonprofit(this);

		return designation;
	}

	public Designation removeDesignation(Designation designation) {
		getDesignations().remove(designation);
		designation.setNonprofit(null);

		return designation;
	}

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public List<PaymentType> getPaymentTypes() {
		return this.paymentTypes;
	}

	public void setPaymentTypes(List<PaymentType> paymentTypes) {
		this.paymentTypes = paymentTypes;
	}

	public List<PersonPrefNonprofit> getPersonPrefNonprofits() {
		return this.personPrefNonprofits;
	}

	public void setPersonPrefNonprofits(List<PersonPrefNonprofit> personPrefNonprofits) {
		this.personPrefNonprofits = personPrefNonprofits;
	}

	public PersonPrefNonprofit addPersonPrefNonprofit(PersonPrefNonprofit personPrefNonprofit) {
		getPersonPrefNonprofits().add(personPrefNonprofit);
		personPrefNonprofit.setNonprofit(this);

		return personPrefNonprofit;
	}

	public PersonPrefNonprofit removePersonPrefNonprofit(PersonPrefNonprofit personPrefNonprofit) {
		getPersonPrefNonprofits().remove(personPrefNonprofit);
		personPrefNonprofit.setNonprofit(null);

		return personPrefNonprofit;
	}

	public List<WorkplacePrefNonprofit> getWorkplacePrefNonprofits() {
		return this.workplacePrefNonprofits;
	}

	public void setWorkplacePrefNonprofits(List<WorkplacePrefNonprofit> workplacePrefNonprofits) {
		this.workplacePrefNonprofits = workplacePrefNonprofits;
	}

	public WorkplacePrefNonprofit addWorkplacePrefNonprofit(WorkplacePrefNonprofit workplacePrefNonprofit) {
		getWorkplacePrefNonprofits().add(workplacePrefNonprofit);
		workplacePrefNonprofit.setNonprofit(this);

		return workplacePrefNonprofit;
	}

	public WorkplacePrefNonprofit removeWorkplacePrefNonprofit(WorkplacePrefNonprofit workplacePrefNonprofit) {
		getWorkplacePrefNonprofits().remove(workplacePrefNonprofit);
		workplacePrefNonprofit.setNonprofit(null);

		return workplacePrefNonprofit;
	}

}