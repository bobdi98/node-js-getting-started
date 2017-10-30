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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the campaign database table.
 * 
 */
@Entity
@NamedQuery(name="Campaign.findAll", query="SELECT c FROM Campaign c")
public class Campaign implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="allow_localized_funds")
	private Boolean allowLocalizedFunds;

	@Column(name="allow_npo_designation")
	private Boolean allowNpoDesignation;

	@Column(name="allow_specified_funds")
	private Boolean allowSpecifiedFunds;

	@Column(name="banner_image")
	private String bannerImage;

	@Column(name="card_image")
	private String cardImage;

	@Column(name="created_by")
	private Long createdBy;

	@Temporal(TemporalType.DATE)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="cta_link")
	private String ctaLink;

	@Column(name="cta_text")
	private String ctaText;

	private String currency;

	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	@Column(name="fundraising_goal")
	private BigDecimal fundraisingGoal;

	@Temporal(TemporalType.DATE)
	@Column(name="last_modified_date")
	private Date lastModifiedDate;

	private String name;

	@Column(name="one_time_ask_ladder")
	private String oneTimeAskLadder;

	@Column(name="recurring_time_ask_ladder")
	private String recurringTimeAskLadder;

	private String scope;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;

	private String subheading;

	//bi-directional many-to-many association to PaymentType
	@ManyToMany
	@JoinTable(
		name="campaign_payment_type"
		, joinColumns={
			@JoinColumn(name="campaign_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="payment_type_id")
			}
		)
	private List<PaymentType> paymentTypes;

	//bi-directional many-to-one association to Processor
	@ManyToOne
	@JoinColumn(name="processor_id")
	private Processor processor;

	//bi-directional many-to-many association to ImpactFund
	@ManyToMany(mappedBy="campaigns")
	private List<ImpactFund> impactFunds;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="campaign")
	private List<Transaction> transactions;

	public Campaign() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getAllowLocalizedFunds() {
		return this.allowLocalizedFunds;
	}

	public void setAllowLocalizedFunds(Boolean allowLocalizedFunds) {
		this.allowLocalizedFunds = allowLocalizedFunds;
	}

	public Boolean getAllowNpoDesignation() {
		return this.allowNpoDesignation;
	}

	public void setAllowNpoDesignation(Boolean allowNpoDesignation) {
		this.allowNpoDesignation = allowNpoDesignation;
	}

	public Boolean getAllowSpecifiedFunds() {
		return this.allowSpecifiedFunds;
	}

	public void setAllowSpecifiedFunds(Boolean allowSpecifiedFunds) {
		this.allowSpecifiedFunds = allowSpecifiedFunds;
	}

	public String getBannerImage() {
		return this.bannerImage;
	}

	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}

	public String getCardImage() {
		return this.cardImage;
	}

	public void setCardImage(String cardImage) {
		this.cardImage = cardImage;
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

	public String getCtaLink() {
		return this.ctaLink;
	}

	public void setCtaLink(String ctaLink) {
		this.ctaLink = ctaLink;
	}

	public String getCtaText() {
		return this.ctaText;
	}

	public void setCtaText(String ctaText) {
		this.ctaText = ctaText;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getFundraisingGoal() {
		return this.fundraisingGoal;
	}

	public void setFundraisingGoal(BigDecimal fundraisingGoal) {
		this.fundraisingGoal = fundraisingGoal;
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

	public String getOneTimeAskLadder() {
		return this.oneTimeAskLadder;
	}

	public void setOneTimeAskLadder(String oneTimeAskLadder) {
		this.oneTimeAskLadder = oneTimeAskLadder;
	}

	public String getRecurringTimeAskLadder() {
		return this.recurringTimeAskLadder;
	}

	public void setRecurringTimeAskLadder(String recurringTimeAskLadder) {
		this.recurringTimeAskLadder = recurringTimeAskLadder;
	}

	public String getScope() {
		return this.scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getSubheading() {
		return this.subheading;
	}

	public void setSubheading(String subheading) {
		this.subheading = subheading;
	}

	public List<PaymentType> getPaymentTypes() {
		return this.paymentTypes;
	}

	public void setPaymentTypes(List<PaymentType> paymentTypes) {
		this.paymentTypes = paymentTypes;
	}

	public Processor getProcessor() {
		return this.processor;
	}

	public void setProcessor(Processor processor) {
		this.processor = processor;
	}

	public List<ImpactFund> getImpactFunds() {
		return this.impactFunds;
	}

	public void setImpactFunds(List<ImpactFund> impactFunds) {
		this.impactFunds = impactFunds;
	}

	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Transaction addTransaction(Transaction transaction) {
		getTransactions().add(transaction);
		transaction.setCampaign(this);

		return transaction;
	}

	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setCampaign(null);

		return transaction;
	}

}