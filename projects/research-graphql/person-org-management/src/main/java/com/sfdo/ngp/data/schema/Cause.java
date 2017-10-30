package com.sfdo.ngp.data.schema;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the cause database table.
 * 
 */
@Entity
@NamedQuery(name="Cause.findAll", query="SELECT c FROM Cause c")
public class Cause implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

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

	//bi-directional many-to-one association to PersonPrefCause
	@OneToMany(mappedBy="cause")
	private List<PersonPrefCause> personPrefCauses;

	//bi-directional many-to-one association to WorkplacePrefCause
	@OneToMany(mappedBy="cause")
	private List<WorkplacePrefCause> workplacePrefCauses;

	public Cause() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<PersonPrefCause> getPersonPrefCauses() {
		return this.personPrefCauses;
	}

	public void setPersonPrefCauses(List<PersonPrefCause> personPrefCauses) {
		this.personPrefCauses = personPrefCauses;
	}

	public PersonPrefCause addPersonPrefCaus(PersonPrefCause personPrefCaus) {
		getPersonPrefCauses().add(personPrefCaus);
		personPrefCaus.setCause(this);

		return personPrefCaus;
	}

	public PersonPrefCause removePersonPrefCaus(PersonPrefCause personPrefCaus) {
		getPersonPrefCauses().remove(personPrefCaus);
		personPrefCaus.setCause(null);

		return personPrefCaus;
	}

	public List<WorkplacePrefCause> getWorkplacePrefCauses() {
		return this.workplacePrefCauses;
	}

	public void setWorkplacePrefCauses(List<WorkplacePrefCause> workplacePrefCauses) {
		this.workplacePrefCauses = workplacePrefCauses;
	}

	public WorkplacePrefCause addWorkplacePrefCaus(WorkplacePrefCause workplacePrefCaus) {
		getWorkplacePrefCauses().add(workplacePrefCaus);
		workplacePrefCaus.setCause(this);

		return workplacePrefCaus;
	}

	public WorkplacePrefCause removeWorkplacePrefCaus(WorkplacePrefCause workplacePrefCaus) {
		getWorkplacePrefCauses().remove(workplacePrefCaus);
		workplacePrefCaus.setCause(null);

		return workplacePrefCaus;
	}

}