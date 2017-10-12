package com.sfdo.ngp.data.schema;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 * The persistent class for the identity database table.
 * 
 */
@Entity
@NamedQuery(name="Identity.findAll", query="SELECT i FROM Identity i")
public class Identity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="identity_type")
	private String identityType;

	@Column(name="user_name")
	private String userName;

	//bi-directional many-to-one association to Person
	@ManyToOne
	private Person person;

	//bi-directional many-to-one association to OrgIdentity
	@OneToMany(mappedBy="identity")
	private List<OrgIdentity> orgIdentities;

	public Identity() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdentityType() {
		return this.identityType;
	}

	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<OrgIdentity> getOrgIdentities() {
		return this.orgIdentities;
	}

	public void setOrgIdentities(List<OrgIdentity> orgIdentities) {
		this.orgIdentities = orgIdentities;
	}

	public OrgIdentity addOrgIdentity(OrgIdentity orgIdentity) {
		getOrgIdentities().add(orgIdentity);
		orgIdentity.setIdentity(this);

		return orgIdentity;
	}

	public OrgIdentity removeOrgIdentity(OrgIdentity orgIdentity) {
		getOrgIdentities().remove(orgIdentity);
		orgIdentity.setIdentity(null);

		return orgIdentity;
	}

}