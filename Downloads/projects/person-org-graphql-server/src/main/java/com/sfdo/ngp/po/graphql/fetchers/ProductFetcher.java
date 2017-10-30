package com.sfdo.ngp.po.graphql.fetchers;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.sfdo.ngp.po.service.ProductAdaptor;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import me.roybailey.data.schema.ProductDto;


/**
 * Instantiated by graphql-java library so we need to hook into Spring to get other beans.
 */
@Slf4j
public class ProductFetcher implements DataFetcher<ProductDto>, ApplicationListener<ContextRefreshedEvent> {

    ProductAdaptor productAdaptor;

    public ProductFetcher(ProductAdaptor productAdaptor) {
        this.productAdaptor = productAdaptor;
    }

    @Override
    public ProductDto get(DataFetchingEnvironment environment) {
        //System.out.println("getArguments {}" + environment.getArguments());
        String productId = environment.getArgument("productId");
        ProductDto product = productAdaptor.getProduct(productId);
        return product;
    }

    // only needed for annotations schema as it instantiates fetchers outside spring
    public ProductFetcher(){}

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        productAdaptor = contextRefreshedEvent.getApplicationContext().getBean(ProductAdaptor.class);
    }
}
