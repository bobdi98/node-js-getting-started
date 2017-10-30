package com.sfdo.ngp.data.schema;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the designation database table.
 * 
 */
@Entity
@NamedQuery(name="Designation.findAll", query="SELECT d FROM Designation d")
public class Designation implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DesignationPK id;

	@Column(name="allocation_amount")
	private BigDecimal allocationAmount;

	@Column(name="allocation_percent")
	private BigDecimal allocationPercent;

	//bi-directional many-to-one association to Nonprofit
	@ManyToOne
	@JoinColumn(name="designee", insertable=false, updatable=false)
	private Nonprofit nonprofit;

	//bi-directional many-to-one association to Transaction
	@ManyToOne
	private Transaction transaction;

	public Designation() {
	}

	public DesignationPK getId() {
		return this.id;
	}

	public void setId(DesignationPK id) {
		this.id = id;
	}

	public BigDecimal getAllocationAmount() {
		return this.allocationAmount;
	}

	public void setAllocationAmount(BigDecimal allocationAmount) {
		this.allocationAmount = allocationAmount;
	}

	public BigDecimal getAllocationPercent() {
		return this.allocationPercent;
	}

	public void setAllocationPercent(BigDecimal allocationPercent) {
		this.allocationPercent = allocationPercent;
	}

	public Nonprofit getNonprofit() {
		return this.nonprofit;
	}

	public void setNonprofit(Nonprofit nonprofit) {
		this.nonprofit = nonprofit;
	}

	public Transaction getTransaction() {
		return this.transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

}