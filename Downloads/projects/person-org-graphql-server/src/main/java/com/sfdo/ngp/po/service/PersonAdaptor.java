package com.sfdo.ngp.po.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.sfdo.ngp.data.schema.Identity;
import com.sfdo.ngp.data.schema.OrgIdentity;
import com.sfdo.ngp.data.schema.Organization;
import com.sfdo.ngp.data.schema.Person;
import com.sfdo.ngp.data.schema.PersonRole;
import com.sfdo.ngp.data.schema.PersonRolePK;
import com.sfdo.ngp.data.schema.Processor;
import com.sfdo.ngp.data.schema.Role;
import com.sfdo.ngp.data.schema.WorkplacePartner;
import com.sfdo.ngp.persistence.IdentityRepository;
import com.sfdo.ngp.persistence.OrgIdentityRepository;
import com.sfdo.ngp.persistence.OrganizationRepository;
import com.sfdo.ngp.persistence.PersonRepository;
import com.sfdo.ngp.persistence.PersonRoleRepository;
import com.sfdo.ngp.persistence.ProcessorRespository;
import com.sfdo.ngp.persistence.RoleRepository;
import com.sfdo.ngp.persistence.WorkplacePartnerRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service()
public class PersonAdaptor {

    @Autowired
    DataSourceProperties properties;
    
	@Autowired
	SFDCConnector sfdcConnector;

	
    @Autowired
    PersonRepository personRepository;


    @Autowired
    OrganizationRepository orgRepository;
    
    @Autowired
    OrgIdentityRepository orgIdRepo;
    
    @Autowired
    WorkplacePartnerRepository wpRepo;

    @Autowired
    ProcessorRespository processorRepo;
    
    @Autowired
    RoleRepository roleRepo;
    
    @Autowired
    PersonRoleRepository proleRepo;
    
    @Autowired
    IdentityRepository identityRepo;
    

    
    public List<Person> getAllPersons() {
	        
    		return Lists.newArrayList(this.personRepository.findAll());
    }

    public Person getPerson(Long personId) {
        return personRepository.findOne(personId);
    }

    public List<Organization> getOrganizations() {
        return Lists.newArrayList(orgRepository.findAll());
    }

    public Organization getOrganization(Long orgId) {
        return orgRepository.findOne(orgId);
    }

    public Organization upsertOrganization(Organization org) {
        return orgRepository.save(org);
    }
    
    public Person upsertPerson(Person person) {
    		personRepository.save(person);
        return person;
    }

    public Person deletePerson(Long personId) {
        personRepository.delete(personId);
    		return null;
    }
    
    public WorkplacePartner createWorkplacePartner(WorkplacePartner org) {
		wpRepo.save(org);
		return org;
    }
    
    public WorkplacePartner getWorkplacePartner(Long wpId) {
    		return wpRepo.findOne(wpId);
    }

    public Processor createProcessor(Processor org) {
		processorRepo.save(org);
		return org;
    }
    
    public Processor getProcessor(Long processorId) {
    		return processorRepo.findOne(processorId);
    }
    
    public Person createPlatformAdmin(Person person) {
    	
    		PersonRolePK pk = new PersonRolePK();
		Person pp = upsertPerson(person);
    		/*
		pk.setPersonId(pp.getId());
		pk.setRoleId(new Long(1005));
		PersonRole pr = new PersonRole();
		pr.setId(pk);
		createPersonRole(pr);
		*/
		try {
			String username = person.getPersonalemail() + UUID.randomUUID().toString();
			String externalId = sfdcConnector.createUser(person.getLastname(), person.getPersonalemail(), username, true, null);
			Identity identity = new Identity();
			identity.setIdentityType("EMPLOYMENT");//todo: enums?
			identity.setUserName(username);
			identity.setPerson(pp);
			identityRepo.save(identity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return pp;
    }
    
    public Organization createOrganization(Organization org) {
    	
		try {
			Organization orgCreated = orgRepository.save(org);

			String externalId = sfdcConnector.createAccount(orgCreated.getName(), orgCreated.getId());
			orgCreated.setExternalId(externalId);
			upsertOrganization(orgCreated);
		} catch (Exception e) {
			e.printStackTrace(); // handle exception logic
		}

    		return org;
    }
    
    public Role createRole(Role role) {
    		return roleRepo.save(role);
    		
    }

    public OrgIdentity createOrgIdentity(OrgIdentity org) {
		orgIdRepo.save(org);
		return org;
    }
    
    public OrgIdentity getOrgIdentity(Long Id) {
		return orgIdRepo.findOne(Id);
    }
    

    public List<OrgIdentity> getOrgIdentities() {
        return Lists.newArrayList(orgIdRepo.findAll());
    }
    
    public Role getRole(Long Id) {
    		return roleRepo.findOne(Id);
    }
    
    public PersonRole createPersonRole(PersonRole pr) {
    		proleRepo.createPersonRole(pr.getId().getRoleId(), pr.getId().getPersonId(), pr.getOrg_id()!= null? pr.getOrg_id() : null);
    		return pr;
    }
    
    public Person createEmployee(Person person, OrgIdentity orgIdentity ) {
		Person p = personRepository.save(person);
		//p.addPersonRole(new PersonRole(new PersonRolePK(new Long(1002), person.getId()), person.getOrganization().getId()));
		
		orgIdentity.setPerson(p);
		orgIdRepo.save(orgIdentity);
		
		try {
			String username = person.getPersonalemail() + "." + UUID.randomUUID().toString();
			String externalId = sfdcConnector.createUser(person.getLastname(), person.getPersonalemail(), username, false, person.getOrganization().getExternalId());
			Identity identity = new Identity();
			identity.setIdentityType("EMPLOYMENT");//todo: enums?
			identity.setUserName(username);
			identity.setPerson(p);
			identityRepo.save(identity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return p;
		

    }
}

