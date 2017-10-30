package com.sfdo.ngp.data.schema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
 * The persistent class for the person database table.
 * 
 */
@Entity
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String addressline1;

	private String addressline2;

	private String city;

	@Temporal(TemporalType.DATE)
	private Date dateofbirth;

	private String firstname;

	private String gender;

	private String lastname;

	private String middlename;

	private String nickname;

	private String personalemail;

	private String personalphone;

	private String postalcode;

	private String postalcodeext;

	private String salutation;

	private String state;

	private String suffix;

	//bi-directional many-to-one association to Identity
	@OneToMany(mappedBy="person")
	private List<Identity> identities;

	//bi-directional many-to-one association to OrgIdentity
	@OneToMany(mappedBy="person", cascade=CascadeType.PERSIST)
    
	private List<OrgIdentity> orgIdentities;

	//bi-directional many-to-one association to Organization
	@ManyToOne
	@JoinColumn(name="org_id")
	private Organization organization;

	//bi-directional many-to-one association to PersonPrefCause
	@OneToMany(mappedBy="person")
	private List<PersonPrefCause> personPrefCauses;

	//bi-directional many-to-one association to PersonPrefNonprofit
	@OneToMany(mappedBy="person")
	private List<PersonPrefNonprofit> personPrefNonprofits;

	//bi-directional many-to-one association to PersonRole
	@OneToMany(mappedBy="person", cascade=CascadeType.PERSIST)
	private List<PersonRole> personRoles;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="person")
	private List<Transaction> transactions;

	public Person() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddressline1() {
		return this.addressline1;
	}

	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}

	public String getAddressline2() {
		return this.addressline2;
	}

	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getDateofbirth() {
		return this.dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMiddlename() {
		return this.middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPersonalemail() {
		return this.personalemail;
	}

	public void setPersonalemail(String personalemail) {
		this.personalemail = personalemail;
	}

	public String getPersonalphone() {
		return this.personalphone;
	}

	public void setPersonalphone(String personalphone) {
		this.personalphone = personalphone;
	}

	public String getPostalcode() {
		return this.postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getPostalcodeext() {
		return this.postalcodeext;
	}

	public void setPostalcodeext(String postalcodeext) {
		this.postalcodeext = postalcodeext;
	}

	public String getSalutation() {
		return this.salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSuffix() {
		return this.suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public List<Identity> getIdentities() {
		return this.identities;
	}

	public void setIdentities(List<Identity> identities) {
		this.identities = identities;
	}

	public Identity addIdentity(Identity identity) {
		getIdentities().add(identity);
		identity.setPerson(this);

		return identity;
	}

	public Identity removeIdentity(Identity identity) {
		getIdentities().remove(identity);
		identity.setPerson(null);

		return identity;
	}

	
	public List<OrgIdentity> getOrgIdentities() {
		return this.orgIdentities;
	}

	public void setOrgIdentities(List<OrgIdentity> orgIdentities) {
		this.orgIdentities = orgIdentities;
	}

	public OrgIdentity addOrgIdentity(OrgIdentity orgIdentity) {
		if(getOrgIdentities() == null)
			this.orgIdentities = new ArrayList<OrgIdentity>();
		getOrgIdentities().add(orgIdentity);
		orgIdentity.setPerson(this);

		return orgIdentity;
	}

	public OrgIdentity removeOrgIdentity(OrgIdentity orgIdentity) {
		getOrgIdentities().remove(orgIdentity);
		orgIdentity.setPerson(null);

		return orgIdentity;
	}

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public List<PersonPrefCause> getPersonPrefCauses() {
		return this.personPrefCauses;
	}

	public void setPersonPrefCauses(List<PersonPrefCause> personPrefCauses) {
		this.personPrefCauses = personPrefCauses;
	}

	public PersonPrefCause addPersonPrefCaus(PersonPrefCause personPrefCaus) {
		getPersonPrefCauses().add(personPrefCaus);
		personPrefCaus.setPerson(this);

		return personPrefCaus;
	}

	public PersonPrefCause removePersonPrefCaus(PersonPrefCause personPrefCaus) {
		getPersonPrefCauses().remove(personPrefCaus);
		personPrefCaus.setPerson(null);

		return personPrefCaus;
	}

	public List<PersonPrefNonprofit> getPersonPrefNonprofits() {
		return this.personPrefNonprofits;
	}

	public void setPersonPrefNonprofits(List<PersonPrefNonprofit> personPrefNonprofits) {
		this.personPrefNonprofits = personPrefNonprofits;
	}

	public PersonPrefNonprofit addPersonPrefNonprofit(PersonPrefNonprofit personPrefNonprofit) {
		getPersonPrefNonprofits().add(personPrefNonprofit);
		personPrefNonprofit.setPerson(this);

		return personPrefNonprofit;
	}

	public PersonPrefNonprofit removePersonPrefNonprofit(PersonPrefNonprofit personPrefNonprofit) {
		getPersonPrefNonprofits().remove(personPrefNonprofit);
		personPrefNonprofit.setPerson(null);

		return personPrefNonprofit;
	}

	public List<PersonRole> getPersonRoles() {
		return this.personRoles;
	}

	public void setPersonRoles(List<PersonRole> personRoles) {
		this.personRoles = personRoles;
	}

	public PersonRole addPersonRole(PersonRole personRole) {
		if(getPersonRoles() == null)
			setPersonRoles(new ArrayList<PersonRole>());
		getPersonRoles().add(personRole);
		personRole.setPerson(this);

		return personRole;
	}

	public PersonRole removePersonRole(PersonRole personRole) {
		getPersonRoles().remove(personRole);
		personRole.setPerson(null);

		return personRole;
	}

	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Transaction addTransaction(Transaction transaction) {
		getTransactions().add(transaction);
		transaction.setPerson(this);

		return transaction;
	}

	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setPerson(null);

		return transaction;
	}

}