package com.sfdo.ngp.data.schema;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the payment_type database table.
 * 
 */
@Entity
@Table(name="payment_type")
@NamedQuery(name="PaymentType.findAll", query="SELECT p FROM PaymentType p")
public class PaymentType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String type;

	//bi-directional many-to-many association to Campaign
	@ManyToMany(mappedBy="paymentTypes")
	private List<Campaign> campaigns;

	//bi-directional many-to-many association to Nonprofit
	@ManyToMany
	@JoinTable(
		name="npo_payment_type"
		, joinColumns={
			@JoinColumn(name="payment_type_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="npo_id")
			}
		)
	private List<Nonprofit> nonprofits;

	public PaymentType() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Campaign> getCampaigns() {
		return this.campaigns;
	}

	public void setCampaigns(List<Campaign> campaigns) {
		this.campaigns = campaigns;
	}

	public List<Nonprofit> getNonprofits() {
		return this.nonprofits;
	}

	public void setNonprofits(List<Nonprofit> nonprofits) {
		this.nonprofits = nonprofits;
	}

}