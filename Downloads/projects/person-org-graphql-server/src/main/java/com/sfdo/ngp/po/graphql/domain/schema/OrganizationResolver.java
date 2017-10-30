package com.sfdo.ngp.po.graphql.domain.schema;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.sfdo.ngp.data.schema.OrgIdentity;
import com.sfdo.ngp.data.schema.Organization;
import com.sfdo.ngp.persistence.OrganizationRepository;

/**
 * This class contains resolver methods for the "Data Class" type
 */
@Service
public class OrganizationResolver implements GraphQLResolver<Organization> {
	
	@Autowired
	OrganizationRepository orgRepo;

	public Organization getOrganization(Long id) {
		return orgRepo.findOne(id);
	}
	 
    public Long getOrgId(Organization org) {
        return org.getId();
    }
        
    
    public List<OrgIdentity> getOrgIdentities(Organization org) {
    		return org.getOrgIdentities();
    }

}