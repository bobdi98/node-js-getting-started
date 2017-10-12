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
 * The persistent class for the person_pref_nonprofit database table.
 * 
 */
@Entity
@Table(name="person_pref_nonprofit")
@NamedQuery(name="PersonPrefNonprofit.findAll", query="SELECT p FROM PersonPrefNonprofit p")
public class PersonPrefNonprofit implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PersonPrefNonprofitPK id;

	@Column(name="is_restricted")
	private Boolean isRestricted;

	@Column(name="leader_status")
	private String leaderStatus;

	@Column(name="publish_flag")
	private Boolean publishFlag;

	@Column(name="release_flag")
	private Boolean releaseFlag;

	//bi-directional many-to-one association to Nonprofit
	@ManyToOne
	@JoinColumn(name="ngo_id", insertable=false, updatable=false)
	private Nonprofit nonprofit;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="id", insertable=false, updatable=false)
	private Person person;

	public PersonPrefNonprofit() {
	}

	public PersonPrefNonprofitPK getId() {
		return this.id;
	}

	public void setId(PersonPrefNonprofitPK id) {
		this.id = id;
	}

	public Boolean getIsRestricted() {
		return this.isRestricted;
	}

	public void setIsRestricted(Boolean isRestricted) {
		this.isRestricted = isRestricted;
	}

	public String getLeaderStatus() {
		return this.leaderStatus;
	}

	public void setLeaderStatus(String leaderStatus) {
		this.leaderStatus = leaderStatus;
	}

	public Boolean getPublishFlag() {
		return this.publishFlag;
	}

	public void setPublishFlag(Boolean publishFlag) {
		this.publishFlag = publishFlag;
	}

	public Boolean getReleaseFlag() {
		return this.releaseFlag;
	}

	public void setReleaseFlag(Boolean releaseFlag) {
		this.releaseFlag = releaseFlag;
	}

	public Nonprofit getNonprofit() {
		return this.nonprofit;
	}

	public void setNonprofit(Nonprofit nonprofit) {
		this.nonprofit = nonprofit;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}