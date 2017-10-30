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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the impact_fund database table.
 * 
 */
@Entity
@Table(name="impact_fund")
@NamedQuery(name="ImpactFund.findAll", query="SELECT i FROM ImpactFund i")
public class ImpactFund implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="accounting_code")
	private String accountingCode;

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

	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="last_modified_date")
	private Date lastModifiedDate;

	private String name;

	@Column(name="processor_id")
	private Long processorId;

	private String scope;

	private String subheading;

	//bi-directional many-to-many association to Campaign
	@ManyToMany
	@JoinTable(
		name="campaign_funds"
		, joinColumns={
			@JoinColumn(name="impact_fund_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="campaign_id")
			}
		)
	private List<Campaign> campaigns;

	//bi-directional many-to-one association to Organization
	@ManyToOne
	@JoinColumn(name="org_id")
	private Organization organization;

	public ImpactFund() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountingCode() {
		return this.accountingCode;
	}

	public void setAccountingCode(String accountingCode) {
		this.accountingCode = accountingCode;
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

	public Long getProcessorId() {
		return this.processorId;
	}

	public void setProcessorId(Long processorId) {
		this.processorId = processorId;
	}

	public String getScope() {
		return this.scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getSubheading() {
		return this.subheading;
	}

	public void setSubheading(String subheading) {
		this.subheading = subheading;
	}

	public List<Campaign> getCampaigns() {
		return this.campaigns;
	}

	public void setCampaigns(List<Campaign> campaigns) {
		this.campaigns = campaigns;
	}

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

}