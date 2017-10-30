package com.sfdo.ngp.po.graphql.domain.schema;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import com.sfdo.ngp.data.schema.OrgIdentity;
import com.sfdo.ngp.data.schema.Organization;
import com.sfdo.ngp.data.schema.Person;
import com.sfdo.ngp.data.schema.PersonRole;
import com.sfdo.ngp.data.schema.PersonRolePK;
import com.sfdo.ngp.data.schema.Processor;
import com.sfdo.ngp.data.schema.Role;
import com.sfdo.ngp.data.schema.WorkplacePartner;
import com.sfdo.ngp.persistence.PersonRepository;
import com.sfdo.ngp.po.service.PersonAdaptor;
import com.sfdo.ngp.po.service.ProductAdaptor;

import me.roybailey.data.schema.ProductDto;

@Service
public class Mutation implements GraphQLRootResolver {

	@Autowired
	ProductAdaptor productAdaptor;

	@Autowired
	PersonAdaptor personAdaptor;
	

	public ProductDto createProduct(String name, BigDecimal price) {
		ProductDto product = ProductDto.builder().name(name).price(price).build();
		return productAdaptor.upsertProduct(product);
	}

	public ProductDto createProductObject(ProductDtoInput product) {
		//return productAdaptor.upsertProduct(product);
		return null;
	}

	public ProductDto deleteProduct(String productId) {
		return productAdaptor.deleteProduct(productId);
	}

	public Organization createOrganizationObject(OrganizationDtoInput org) {

		return personAdaptor.createOrganization(org);
		
	}
	
	public Role createRole(RoleDtoInput role) {

		Role r = new Role();
		//r.setId(new Long(1002));
		r.setName(role.getName());
		r.setDescription(role.getDescription());
		r.setCreatedDate(new Date());
		r.setCreatedBy(new Long(1234));
		return personAdaptor.createRole(r);
	}

	

	public Organization updateOrganization(Long Id, String name, String dba, String externalId, String street, String street2,
			String city, String state, String postalCode, String postalCodeExt, String country, String phone,
			String website, String defaultLanguage, String defaultCurrency, String timeZone) {
		
		Organization org = null;
		try {
			org = personAdaptor.getOrganization(Id);
		} catch (Exception e) {
			throw e; // todo
		}

		if(name != null)
			org.setName(name);
		if(dba != null)
			org.setDba(dba);
		if(externalId != null)
			org.setExternalId(externalId);
		if(street != null)
		org.setStreet(street);
		if(street2 != null)
		org.setStreet2(street2);
		if(city != null)
		org.setCity(city);
		if(state != null)
		org.setState(state);
		if(postalCode != null)
		org.setPostalCode(postalCode);
		if(postalCodeExt != null)
		org.setPostalCodeExt(postalCodeExt);
		if(country != null)
		org.setCountry(country);
		if(phone != null)
		org.setPhone(phone);
		if(website != null)
		org.setWebsite(website);
		if(defaultCurrency != null)
		org.setDefaultCurrency(defaultCurrency);
		if(defaultLanguage != null)
		org.setDefaultLanguage(defaultLanguage);
		if(timeZone != null)
		org.setTimeZone(timeZone);
		org.setCreatedDate(new Date()); // todo
		org.setCreatedBy(new Long(1234)); // todo
		personAdaptor.createOrganization(org);
		
		return org;
	}

	public Organization createOrganization(String name, String dba, String externalId, String street, String street2,
			String city, String state, String postalCode, String postalCodeExt, String country, String phone,
			String website, String defaultLanguage, String defaultCurrency, String timeZone) {
		Organization org = new Organization();
		org.setName(name);
		org.setDba(dba);
		org.setExternalId(externalId);
		org.setStreet(street);
		org.setStreet2(street2);
		org.setCity(city);
		org.setState(state);
		org.setPostalCode(postalCodeExt);
		org.setPostalCodeExt(postalCodeExt);
		org.setCountry(country);
		org.setPhone(phone);
		org.setWebsite(website);
		org.setDefaultCurrency(defaultCurrency);
		org.setDefaultLanguage(defaultLanguage);
		org.setTimeZone(timeZone);
		org.setCreatedDate(new Date()); // todo
		org.setCreatedBy(new Long(1234)); // todo
		personAdaptor.createOrganization(org);

		return org;
	}

	public Person updatePerson(Long Id, String salutation, String firstname, String middlename, String lastname,
			String suffix, String nickname, String city, String state, String postalcode, String personalemail,
			String gender, String personalphone, String addressline1, String addressline2) {

		Person person = personAdaptor.getPerson(Id);
		if (salutation != null)
			person.setSalutation(salutation);
		if (firstname != null)
			person.setFirstname(firstname);
		if (middlename != null)
			person.setMiddlename(middlename);
		if (lastname != null)
			person.setLastname(lastname);
		if (suffix != null)
			person.setSuffix(suffix);
		if (nickname != null)
			person.setNickname(nickname);
		if (city != null)
			person.setCity(city);
		if (state != null)
			person.setState(state);
		if (postalcode != null)
			person.setPostalcode(postalcode);
		if (personalemail != null)
			person.setPersonalemail(personalemail);
		if (gender != null)
			person.setGender(gender);
		if (personalphone != null)
			person.setPersonalphone(personalphone);
		if (addressline1 != null)
			person.setAddressline1(addressline1);
		if (addressline2 != null)
			person.setAddressline2(addressline2);
		person.addOrgIdentity(new OrgIdentity());

		return personAdaptor.upsertPerson(person);
	}

	public OrgIdentity createOrgIdentity(String dept, String empId, Long employerId, String office, String region, Long orgId, Long personId ) {
		OrgIdentity orgIdentity = new OrgIdentity();
		orgIdentity.setWorkplacePartner(personAdaptor.getWorkplacePartner(employerId));
		orgIdentity.setDepartment(dept);
		orgIdentity.setEmpId(empId);
		orgIdentity.setRegion(region);
		orgIdentity.setOffice(office);
		orgIdentity.setOrganization(personAdaptor.getOrganization(orgId));
		orgIdentity.setPerson(personAdaptor.getPerson(personId));
		return personAdaptor.createOrgIdentity(orgIdentity);
	}
	
	public WorkplacePartner createWorkplacePartner( Long orgId, Long processorId, String division, Long reseller ) {
		WorkplacePartner wp = new WorkplacePartner();
		wp.setDivison(division);
		wp.setOrganization(personAdaptor.getOrganization(orgId));
		wp.setReseller(reseller);
		wp.setProcessor(personAdaptor.getProcessor(processorId));
		return personAdaptor.createWorkplacePartner(wp);
	}
	
	public Processor createProcessor(String activeFlag, Long orgId, String stripeConnectId ) {
		Processor wp = new Processor();
		wp.setIsActive(Boolean.valueOf(activeFlag));//todo
		wp.setOrganization(personAdaptor.getOrganization(orgId));
		wp.setStripeConnectId(stripeConnectId);
		return personAdaptor.createProcessor(personAdaptor.createProcessor(wp));
	}
	
	public Person createPerson(String salutation, String firstname, String middlename, String lastname, String suffix,
			String nickname, String city, String state, String postalcode, String personalemail, String gender,
			String personalphone, String addressline1, String addressline2, OrgIdentityDtoInput orgIdentity, Long orgId) {
		Person person = new Person();
		person.setSalutation(salutation);
		person.setFirstname(firstname);
		person.setMiddlename(middlename);
		person.setLastname(lastname);
		person.setSuffix(suffix);
		person.setNickname(nickname);
		person.setCity(city);
		person.setState(state);
		person.setPostalcode(postalcode);
		person.setPersonalemail(personalemail);
		person.setGender(gender);
		person.setPersonalphone(personalphone);
		person.setAddressline1(addressline1);
		person.setAddressline2(addressline2);
		if(orgId != null)
			person.setOrganization(personAdaptor.getOrganization(orgId));//todo
		if(orgIdentity != null)
			person.addOrgIdentity(orgIdentity);
		return personAdaptor.upsertPerson(person);
	}

	public Person createPlatformAdmin(String salutation, String firstname, String middlename, String lastname, String suffix,
			String nickname, String city, String state, String postalcode, String personalemail, String gender,
			String personalphone, String addressline1, String addressline2) {
		Person person = new Person();
		person.setSalutation(salutation);
		person.setFirstname(firstname);
		person.setMiddlename(middlename);
		person.setLastname(lastname);
		person.setSuffix(suffix);
		person.setNickname(nickname);
		person.setCity(city);
		person.setState(state);
		person.setPostalcode(postalcode);
		person.setPersonalemail(personalemail);
		person.setGender(gender);
		person.setPersonalphone(personalphone);
		person.setAddressline1(addressline1);
		person.setAddressline2(addressline2);

		return personAdaptor.createPlatformAdmin(person);
	}
	
	public Person createEmployee(String salutation, String firstname, String middlename, String lastname, String suffix,
			String nickname, String city, String state, String postalcode, String personalemail, String gender,
			String personalphone, String addressline1, String addressline2, Long orgId, Long roleId, OrgIdentityDtoInput orgIdentity) {

		Person person = new Person();
		person.setSalutation(salutation);
		person.setFirstname(firstname);
		person.setMiddlename(middlename);
		person.setLastname(lastname);
		person.setSuffix(suffix);
		person.setNickname(nickname);
		person.setCity(city);
		person.setState(state);
		person.setPostalcode(postalcode);
		person.setPersonalemail(personalemail);
		person.setGender(gender);
		person.setPersonalphone(personalphone);
		person.setAddressline1(addressline1);
		person.setAddressline2(addressline2);
		person.setOrganization(personAdaptor.getOrganization(orgId));//todo
		//person.addOrgIdentity(orgIdentity);
		OrgIdentity oo = new OrgIdentity();
		oo.setDepartment(orgIdentity.getDepartment());
		oo.setRegion(orgIdentity.getRegion());
		oo.setOffice(orgIdentity.getOffice());
		oo.setEmpId(orgIdentity.getEmpId());
		oo.setOrganization(personAdaptor.getOrganization(orgId));
		return personAdaptor.createEmployee(person, oo);

	}

	
	public Person createPersonObject(PersonDtoInput person) {
		return personAdaptor.upsertPerson(person);
	}

	public Person associateRole(Long personId, Long roleId, Long orgId) {
		personAdaptor.getRole(roleId);
		PersonRolePK pk = new PersonRolePK();
		pk.setPersonId(personId);
		pk.setRoleId(roleId);
		PersonRole pr = new PersonRole();
		Person person = personAdaptor.getPerson(personId);
		person.addPersonRole(pr);
		return person;
	}
	public Person deletePerson(Long id) {
		// return personAdaptor.(productId);
		return null;
	}
	
}
