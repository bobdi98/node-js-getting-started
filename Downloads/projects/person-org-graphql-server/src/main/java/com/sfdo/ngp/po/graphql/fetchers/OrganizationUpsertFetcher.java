package com.sfdo.ngp.po.graphql.fetchers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.sfdo.ngp.data.schema.Organization;
import com.sfdo.ngp.po.service.PersonAdaptor;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;


/**
 * Instantiated by graphql-java library so we need to hook into Spring to get other beans.
 */
@Slf4j
public class OrganizationUpsertFetcher implements DataFetcher<Organization>, ApplicationListener<ContextRefreshedEvent> {

    PersonAdaptor personAdaptor;

    public OrganizationUpsertFetcher(PersonAdaptor personAdaptor) {
        this.personAdaptor = personAdaptor;
    }

    public static final List<String> EMPTY_CATEGORIES = new ArrayList<>();

    @Override
    public Organization get(DataFetchingEnvironment environment) {
        //log.info("getArguments {}", environment.getArguments());
        Object inputObject = environment.getArgument("organization");
        Organization newOrg = new Organization();
        Function<Object, String> safeString = (o) -> o == null ? null : o.toString();
        Function<Object, Long> safeLong = (o) -> (o instanceof Number) ? Long.valueOf((long)o) : null;
        if (inputObject == null) {
            newOrg.setId(safeLong.apply(environment.getArgument("Id")));
            newOrg.setExternalId(environment.getArgument("externalId"));
            newOrg.setWebsite(environment.getArgument("website"));
            newOrg.setCity(environment.getArgument("city"));
            newOrg.setCountry(environment.getArgument("country"));
            newOrg.setDba(environment.getArgument("dba"));
            newOrg.setDefaultCurrency(environment.getArgument("defaultCurrency"));
            newOrg.setDefaultLanguage(environment.getArgument("defaultLanguage"));
            newOrg.setName(environment.getArgument("name"));
            newOrg.setPhone(environment.getArgument("phone"));
            newOrg.setPostalCode(environment.getArgument("postalCode"));
            newOrg.setPostalCodeExt(environment.getArgument("postalCodeExt"));
            newOrg.setState(environment.getArgument("state"));
            newOrg.setStreet(environment.getArgument("street"));
            newOrg.setStreet2(environment.getArgument("street2"));
            newOrg.setCreatedBy(new Long(1234));// todo
            newOrg.setCreatedDate(new Date());
            
        } else {
            Map<String, Object> inputMap = (Map<String, Object>) inputObject;
            newOrg.setId(safeLong.apply(environment.getArgument("Id")));
            newOrg.setExternalId(safeString.apply(inputMap.get("externalId")));
            newOrg.setWebsite(safeString.apply(inputMap.get("website")));
            newOrg.setCity(safeString.apply(inputMap.get("city")));
            newOrg.setCountry(safeString.apply(inputMap.get("country")));
            newOrg.setDba(safeString.apply(inputMap.get("dba")));
            newOrg.setDefaultCurrency(safeString.apply(inputMap.get("defaultCurrency")));
            newOrg.setDefaultLanguage(safeString.apply(inputMap.get("defaultLanguage")));
            newOrg.setName(safeString.apply(inputMap.get("name")));
            newOrg.setPhone(safeString.apply(inputMap.get("phone")));
            newOrg.setPostalCode(safeString.apply(inputMap.get("postalCode")));
            newOrg.setPostalCodeExt(safeString.apply(inputMap.get("postalCodeExt")));
            newOrg.setState(safeString.apply(inputMap.get("state")));
            newOrg.setStreet(safeString.apply(inputMap.get("street")));
            newOrg.setStreet2(safeString.apply(inputMap.get("street2")));
            newOrg.setCreatedBy(new Long(1234));// todo
            newOrg.setCreatedDate(new Date());

        }
        Organization savedOrg = personAdaptor.upsertOrganization(newOrg);
        return savedOrg;
    }

    // only needed for annotations schema as it instantiates fetchers outside spring
    public OrganizationUpsertFetcher() {
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        personAdaptor = contextRefreshedEvent.getApplicationContext().getBean(PersonAdaptor.class);
    }
}
