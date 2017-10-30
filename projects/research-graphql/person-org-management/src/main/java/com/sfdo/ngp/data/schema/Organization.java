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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the organization database table.
 * 
 */
@Entity
@NamedQuery(name="Organization.findAll", query="SELECT o FROM Organization o")
public class Organization implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String city;

	private String country;

	@Column(name="created_by")
	private Long createdBy;

	@Temporal(TemporalType.DATE)
	@Column(name="created_date")
	private Date createdDate;

	private String dba;

	@Column(name="default_currency")
	private String defaultCurrency;

	@Column(name="default_language")
	private String defaultLanguage;

	@Column(name="external_id")
	private String externalId;

	@Column(name="is_nonprofit")
	private Boolean isNonprofit;

	@Column(name="is_processor")
	private Boolean isProcessor;

	@Column(name="is_workplace_account_manager")
	private Boolean isWorkplaceAccountManager;

	@Column(name="is_workplace_partner")
	private Boolean isWorkplacePartner;

	@Temporal(TemporalType.DATE)
	@Column(name="last_modified_date")
	private Date lastModifiedDate;

	private BigDecimal latitude;

	private BigDecimal longitude;

	private String name;

	private String phone;

	@Column(name="postal_code")
	private String postalCode;

	@Column(name="postal_code_ext")
	private String postalCodeExt;

	private String state;

	private String street;

	@Column(name="street_2")
	private String street2;

	@Column(name="time_zone")
	private String timeZone;

	private String website;

	//bi-directional many-to-one association to ImpactFund
	@OneToMany(mappedBy="organization")
	private List<ImpactFund> impactFunds;

	//bi-directional many-to-one association to Nonprofit
	@OneToMany(mappedBy="organization")
	private List<Nonprofit> nonprofits;

	//bi-directional many-to-one association to OrgIdentity
	@OneToMany(mappedBy="organization")
	private List<OrgIdentity> orgIdentities;

	//bi-directional many-to-one association to Person
	@OneToMany(mappedBy="organization")
	private List<Person> persons;

	//bi-directional many-to-one association to PersonRole
	@OneToMany(mappedBy="organization")
	private List<PersonRole> personRoles;

	//bi-directional many-to-one association to Processor
	@OneToMany(mappedBy="organization")
	private List<Processor> processors;

	//bi-directional many-to-one association to WorkplaceAccountManager
	@OneToMany(mappedBy="organization")
	private List<WorkplaceAccountManager> workplaceAccountManagers;

	//bi-directional many-to-one association to WorkplacePartner
	@OneToMany(mappedBy="organization")
	private List<WorkplacePartner> workplacePartners;

	public Organization() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public String getDba() {
		return this.dba;
	}

	public void setDba(String dba) {
		this.dba = dba;
	}

	public String getDefaultCurrency() {
		return this.defaultCurrency;
	}

	public void setDefaultCurrency(String defaultCurrency) {
		this.defaultCurrency = defaultCurrency;
	}

	public String getDefaultLanguage() {
		return this.defaultLanguage;
	}

	public void setDefaultLanguage(String defaultLanguage) {
		this.defaultLanguage = defaultLanguage;
	}

	public String getExternalId() {
		return this.externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public Boolean getIsNonprofit() {
		return this.isNonprofit;
	}

	public void setIsNonprofit(Boolean isNonprofit) {
		this.isNonprofit = isNonprofit;
	}

	public Boolean getIsProcessor() {
		return this.isProcessor;
	}

	public void setIsProcessor(Boolean isProcessor) {
		this.isProcessor = isProcessor;
	}

	public Boolean getIsWorkplaceAccountManager() {
		return this.isWorkplaceAccountManager;
	}

	public void setIsWorkplaceAccountManager(Boolean isWorkplaceAccountManager) {
		this.isWorkplaceAccountManager = isWorkplaceAccountManager;
	}

	public Boolean getIsWorkplacePartner() {
		return this.isWorkplacePartner;
	}

	public void setIsWorkplacePartner(Boolean isWorkplacePartner) {
		this.isWorkplacePartner = isWorkplacePartner;
	}

	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public BigDecimal getLatitude() {
		return this.latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return this.longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPostalCodeExt() {
		return this.postalCodeExt;
	}

	public void setPostalCodeExt(String postalCodeExt) {
		this.postalCodeExt = postalCodeExt;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreet2() {
		return this.street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getTimeZone() {
		return this.timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public List<ImpactFund> getImpactFunds() {
		return this.impactFunds;
	}

	public void setImpactFunds(List<ImpactFund> impactFunds) {
		this.impactFunds = impactFunds;
	}

	public ImpactFund addImpactFund(ImpactFund impactFund) {
		getImpactFunds().add(impactFund);
		impactFund.setOrganization(this);

		return impactFund;
	}

	public ImpactFund removeImpactFund(ImpactFund impactFund) {
		getImpactFunds().remove(impactFund);
		impactFund.setOrganization(null);

		return impactFund;
	}

	public List<Nonprofit> getNonprofits() {
		return this.nonprofits;
	}

	public void setNonprofits(List<Nonprofit> nonprofits) {
		this.nonprofits = nonprofits;
	}

	public Nonprofit addNonprofit(Nonprofit nonprofit) {
		getNonprofits().add(nonprofit);
		nonprofit.setOrganization(this);

		return nonprofit;
	}

	public Nonprofit removeNonprofit(Nonprofit nonprofit) {
		getNonprofits().remove(nonprofit);
		nonprofit.setOrganization(null);

		return nonprofit;
	}

	public List<OrgIdentity> getOrgIdentities() {
		return this.orgIdentities;
	}

	public void setOrgIdentities(List<OrgIdentity> orgIdentities) {
		this.orgIdentities = orgIdentities;
	}

	public OrgIdentity addOrgIdentity(OrgIdentity orgIdentity) {
		getOrgIdentities().add(orgIdentity);
		orgIdentity.setOrganization(this);

		return orgIdentity;
	}

	public OrgIdentity removeOrgIdentity(OrgIdentity orgIdentity) {
		getOrgIdentities().remove(orgIdentity);
		orgIdentity.setOrganization(null);

		return orgIdentity;
	}

	public List<Person> getPersons() {
		return this.persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public Person addPerson(Person person) {
		getPersons().add(person);
		person.setOrganization(this);

		return person;
	}

	public Person removePerson(Person person) {
		getPersons().remove(person);
		person.setOrganization(null);

		return person;
	}

	public List<PersonRole> getPersonRoles() {
		return this.personRoles;
	}

	public void setPersonRoles(List<PersonRole> personRoles) {
		this.personRoles = personRoles;
	}

	public PersonRole addPersonRole(PersonRole personRole) {
		getPersonRoles().add(personRole);
		personRole.setOrganization(this);

		return personRole;
	}

	public PersonRole removePersonRole(PersonRole personRole) {
		getPersonRoles().remove(personRole);
		personRole.setOrganization(null);

		return personRole;
	}

	public List<Processor> getProcessors() {
		return this.processors;
	}

	public void setProcessors(List<Processor> processors) {
		this.processors = processors;
	}

	public Processor addProcessor(Processor processor) {
		getProcessors().add(processor);
		processor.setOrganization(this);

		return processor;
	}

	public Processor removeProcessor(Processor processor) {
		getProcessors().remove(processor);
		processor.setOrganization(null);

		return processor;
	}

	public List<WorkplaceAccountManager> getWorkplaceAccountManagers() {
		return this.workplaceAccountManagers;
	}

	public void setWorkplaceAccountManagers(List<WorkplaceAccountManager> workplaceAccountManagers) {
		this.workplaceAccountManagers = workplaceAccountManagers;
	}

	public WorkplaceAccountManager addWorkplaceAccountManager(WorkplaceAccountManager workplaceAccountManager) {
		getWorkplaceAccountManagers().add(workplaceAccountManager);
		workplaceAccountManager.setOrganization(this);

		return workplaceAccountManager;
	}

	public WorkplaceAccountManager removeWorkplaceAccountManager(WorkplaceAccountManager workplaceAccountManager) {
		getWorkplaceAccountManagers().remove(workplaceAccountManager);
		workplaceAccountManager.setOrganization(null);

		return workplaceAccountManager;
	}

	public List<WorkplacePartner> getWorkplacePartners() {
		return this.workplacePartners;
	}

	public void setWorkplacePartners(List<WorkplacePartner> workplacePartners) {
		this.workplacePartners = workplacePartners;
	}

	public WorkplacePartner addWorkplacePartner(WorkplacePartner workplacePartner) {
		getWorkplacePartners().add(workplacePartner);
		workplacePartner.setOrganization(this);

		return workplacePartner;
	}

	public WorkplacePartner removeWorkplacePartner(WorkplacePartner workplacePartner) {
		getWorkplacePartners().remove(workplacePartner);
		workplacePartner.setOrganization(null);

		return workplacePartner;
	}

}