package com.sfdo.ngp.po.graphql.domain.schema;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import com.sfdo.ngp.data.schema.OrgIdentity;
import com.sfdo.ngp.data.schema.Organization;
import com.sfdo.ngp.data.schema.Person;
import com.sfdo.ngp.data.schema.Processor;
import com.sfdo.ngp.data.schema.Role;
import com.sfdo.ngp.data.schema.WorkplacePartner;
import com.sfdo.ngp.po.service.OrderAdaptor;
import com.sfdo.ngp.po.service.PersonAdaptor;
import com.sfdo.ngp.po.service.ProductAdaptor;
import com.sfdo.ngp.po.service.UserAdaptor;

import me.roybailey.data.schema.OrderDto;
import me.roybailey.data.schema.ProductDto;
import me.roybailey.data.schema.UserDto;


@Service
public class Query implements GraphQLRootResolver {

    @Autowired
    ProductAdaptor productAdaptor;

    @Autowired
    UserAdaptor userAdaptor;

    @Autowired
    OrderAdaptor orderAdaptor;
    
    @Autowired
    PersonAdaptor personAdaptor;
    
    
    
    public List<com.sfdo.ngp.data.schema.Person> persons() {
        return personAdaptor.getAllPersons();
    }

    public Organization organization(Long Id) {
        return personAdaptor.getOrganization(Id);
    }

    public List<com.sfdo.ngp.data.schema.Organization> organizations() {
        return personAdaptor.getOrganizations();
    }

    public Person person(Long userId) {
        return personAdaptor.getPerson(userId);
    }

    public List<com.sfdo.ngp.data.schema.OrgIdentity> orgIdentities() {
        return personAdaptor.getOrgIdentities();
    }

    public OrgIdentity orgIdentity(Long Id) {
        return personAdaptor.getOrgIdentity(Id);
    }

    public WorkplacePartner workplacePartner(Long Id) {
        return personAdaptor.getWorkplacePartner(Id);
    }

    public Processor processor(Long Id) {
        return personAdaptor.getProcessor(Id);
    }

    public List<ProductDto> products() {
        return productAdaptor.getAllProducts();
    }

    public List<UserDto> users() {
        return userAdaptor.getAllUsers();
    }

    public List<OrderDto> orders() {
        return orderAdaptor.getAllOrders();
    }

    public ProductDto product(String productId) {
        return productAdaptor.getProduct(productId);
    }

    public UserDto user(String userId) {
        return userAdaptor.getUser(userId);
    }
    
    public Role role(Long id) {
    		return personAdaptor.getRole(id);
    }
    
    

}
