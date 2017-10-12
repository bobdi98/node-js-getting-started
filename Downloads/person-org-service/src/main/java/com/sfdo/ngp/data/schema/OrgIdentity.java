package com.sfdo.ngp.data.schema;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the org_identity database table.
 * 
 */
@Entity
@Table(name="org_identity")
@NamedQuery(name="OrgIdentity.findAll", query="SELECT o FROM OrgIdentity o")
public class OrgIdentity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="annual_pay_period")
	private String annualPayPeriod;

	private String department;

	@Column(name="emp_id")
	private String empId;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	@Column(name="is_active")
	private Boolean isActive;

	@Column(name="manager_emp_id")
	private String managerEmpId;

	private String office;

	@Column(name="primary_luw")
	private String primaryLuw;

	private String region;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;

	private String title;

	@Column(name="where_raised_luw")
	private String whereRaisedLuw;

	//bi-directional many-to-one association to Identity
	@ManyToOne
	@JoinColumn(name="id", insertable=false, updatable=false)
	private Identity identity;

	//bi-directional many-to-one association to Organization
	@ManyToOne
	@JoinColumn(name="org_id", insertable=false, updatable=false)
	private Organization organization;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="id",  insertable=false, updatable=false)
	private Person person;

	//bi-directional many-to-one association to WorkplacePartner
	@ManyToOne
	@JoinColumn(name="employer_id", insertable=false, updatable=false)
	private WorkplacePartner workplacePartner;

	public OrgIdentity() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAnnualPayPeriod() {
		return this.annualPayPeriod;
	}

	public void setAnnualPayPeriod(String annualPayPeriod) {
		this.annualPayPeriod = annualPayPeriod;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmpId() {
		return this.empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getManagerEmpId() {
		return this.managerEmpId;
	}

	public void setManagerEmpId(String managerEmpId) {
		this.managerEmpId = managerEmpId;
	}

	public String getOffice() {
		return this.office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getPrimaryLuw() {
		return this.primaryLuw;
	}

	public void setPrimaryLuw(String primaryLuw) {
		this.primaryLuw = primaryLuw;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWhereRaisedLuw() {
		return this.whereRaisedLuw;
	}

	public void setWhereRaisedLuw(String whereRaisedLuw) {
		this.whereRaisedLuw = whereRaisedLuw;
	}

	public Identity getIdentity() {
		return this.identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public WorkplacePartner getWorkplacePartner() {
		return this.workplacePartner;
	}

	public void setWorkplacePartner(WorkplacePartner workplacePartner) {
		this.workplacePartner = workplacePartner;
	}

}