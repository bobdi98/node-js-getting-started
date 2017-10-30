package com.sfdo.ngp.graphql.domain.schema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import com.sfdo.ngp.data.schema.OrgIdentity;
import com.sfdo.ngp.data.schema.Person;
import com.sfdo.ngp.data.schema.PersonRole;
import com.sfdo.ngp.service.PersonAdaptor;


@Service
public class Mutation implements GraphQLRootResolver {


    @Autowired
    PersonAdaptor personAdaptor;
    

	public Person updatePerson(Long Id, String salutation, String firstname, String middlename, String lastname, String suffix,
			String nickname, String city, String state, String postalcode, String personalemail, String gender,
			String personalphone, String addressline1, String addressline2) {
		

		Person person = personAdaptor.getPerson(Id);
		if(salutation != null) person.setSalutation(salutation);
		if(firstname != null) person.setFirstname(firstname);
		if(middlename != null) person.setMiddlename(middlename);
		if(lastname != null) person.setLastname(lastname);
		if(suffix != null)  person.setSuffix(suffix);
		if(nickname != null) person.setNickname(nickname);
		if(city != null) person.setCity(city);
		if(state != null) person.setState(state);
		if(postalcode != null) person.setPostalcode(postalcode);
		if(personalemail != null) person.setPersonalemail(personalemail);
		if(gender != null) person.setGender(gender);
		if(personalphone != null) person.setPersonalphone(personalphone);
		if(addressline1 != null) person.setAddressline1(addressline1);
		if(addressline2 != null) person.setAddressline2(addressline2);
		person.addOrgIdentity(new OrgIdentity());

		return personAdaptor.upsertPerson(person);
	}
    public Person createPerson(  String salutation,
						    		String firstname,
						    		String middlename,
						    		String lastname,
						    		String suffix,
						    		String nickname,
						    		String city,
						    		String state,
						    		String postalcode,
						    		String personalemail,
						    		String gender,
						    		String personalphone,
						    		String addressline1,
						    		String addressline2) 
    			{
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
		    		person.addPersonRole(new PersonRole());
		    		person.addOrgIdentity(new OrgIdentity());

		    		return personAdaptor.upsertPerson(person);
    }
    public Person createPersonObject(PersonDtoInput person) {
        return personAdaptor.upsertPerson(person);
    }

    public Person deletePerson(Long id) {
        // return personAdaptor.(productId);
    	return null;
    }
    

}
