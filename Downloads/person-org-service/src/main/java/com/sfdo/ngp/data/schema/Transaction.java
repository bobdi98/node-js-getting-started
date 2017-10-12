package com.sfdo.ngp.data.schema;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the transaction database table.
 * 
 */
@Entity
@NamedQuery(name="Transaction.findAll", query="SELECT t FROM Transaction t")
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name="check_date")
	private Date checkDate;

	@Column(name="check_number")
	private BigDecimal checkNumber;

	@Column(name="number_of_installments")
	private BigDecimal numberOfInstallments;

	@Column(name="payment_status")
	private String paymentStatus;

	@Column(name="publish_flag")
	private Boolean publishFlag;

	@Column(name="recurring_amount")
	private BigDecimal recurringAmount;

	@Temporal(TemporalType.DATE)
	@Column(name="recurring_end_date")
	private Date recurringEndDate;

	@Column(name="recurring_frequency")
	private BigDecimal recurringFrequency;

	@Temporal(TemporalType.DATE)
	@Column(name="recurring_start_date")
	private Date recurringStartDate;

	@Column(name="release_flag")
	private Boolean releaseFlag;

	@Column(name="total_amount")
	private Long totalAmount;

	@Temporal(TemporalType.DATE)
	@Column(name="tx_date")
	private Date txDate;

	//bi-directional many-to-one association to Designation
	@OneToMany(mappedBy="transaction")
	private List<Designation> designations;

	//bi-directional many-to-one association to Campaign
	@ManyToOne
	private Campaign campaign;

	//bi-directional many-to-one association to Person
	@ManyToOne
	private Person person;

	//bi-directional many-to-one association to Processor
	@ManyToOne
	@JoinColumn(name="processor_id")
	private Processor processor;

	//bi-directional many-to-one association to WorkplacePartner
	@ManyToOne
	@JoinColumn(name="workplace_partner_id")
	private WorkplacePartner workplacePartner;

	public Transaction() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public BigDecimal getCheckNumber() {
		return this.checkNumber;
	}

	public void setCheckNumber(BigDecimal checkNumber) {
		this.checkNumber = checkNumber;
	}

	public BigDecimal getNumberOfInstallments() {
		return this.numberOfInstallments;
	}

	public void setNumberOfInstallments(BigDecimal numberOfInstallments) {
		this.numberOfInstallments = numberOfInstallments;
	}

	public String getPaymentStatus() {
		return this.paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Boolean getPublishFlag() {
		return this.publishFlag;
	}

	public void setPublishFlag(Boolean publishFlag) {
		this.publishFlag = publishFlag;
	}

	public BigDecimal getRecurringAmount() {
		return this.recurringAmount;
	}

	public void setRecurringAmount(BigDecimal recurringAmount) {
		this.recurringAmount = recurringAmount;
	}

	public Date getRecurringEndDate() {
		return this.recurringEndDate;
	}

	public void setRecurringEndDate(Date recurringEndDate) {
		this.recurringEndDate = recurringEndDate;
	}

	public BigDecimal getRecurringFrequency() {
		return this.recurringFrequency;
	}

	public void setRecurringFrequency(BigDecimal recurringFrequency) {
		this.recurringFrequency = recurringFrequency;
	}

	public Date getRecurringStartDate() {
		return this.recurringStartDate;
	}

	public void setRecurringStartDate(Date recurringStartDate) {
		this.recurringStartDate = recurringStartDate;
	}

	public Boolean getReleaseFlag() {
		return this.releaseFlag;
	}

	public void setReleaseFlag(Boolean releaseFlag) {
		this.releaseFlag = releaseFlag;
	}

	public Long getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Date getTxDate() {
		return this.txDate;
	}

	public void setTxDate(Date txDate) {
		this.txDate = txDate;
	}

	public List<Designation> getDesignations() {
		return this.designations;
	}

	public void setDesignations(List<Designation> designations) {
		this.designations = designations;
	}

	public Designation addDesignation(Designation designation) {
		getDesignations().add(designation);
		designation.setTransaction(this);

		return designation;
	}

	public Designation removeDesignation(Designation designation) {
		getDesignations().remove(designation);
		designation.setTransaction(null);

		return designation;
	}

	public Campaign getCampaign() {
		return this.campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Processor getProcessor() {
		return this.processor;
	}

	public void setProcessor(Processor processor) {
		this.processor = processor;
	}

	public WorkplacePartner getWorkplacePartner() {
		return this.workplacePartner;
	}

	public void setWorkplacePartner(WorkplacePartner workplacePartner) {
		this.workplacePartner = workplacePartner;
	}

}