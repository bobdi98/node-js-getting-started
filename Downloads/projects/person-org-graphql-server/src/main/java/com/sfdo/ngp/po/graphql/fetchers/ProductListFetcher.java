package com.sfdo.ngp.po.graphql.fetchers;

import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.sfdo.ngp.po.service.ProductAdaptor;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import me.roybailey.data.schema.ProductDto;


/**
 * Instantiated by graphql-java library so we need to hook into Spring to get other beans.
 */
@Slf4j
@Service
public class ProductListFetcher implements DataFetcher<List<ProductDto>>, ApplicationListener<ContextRefreshedEvent> {

    ProductAdaptor productAdaptor;

    public ProductListFetcher(ProductAdaptor productAdaptor) {
        this.productAdaptor = productAdaptor;
    }

    @Override
    public List<ProductDto> get(DataFetchingEnvironment environment) {
        //System.out.println("getArguments {}" + environment.getArguments());
        List<ProductDto> allProducts = productAdaptor.getAllProducts();
        return allProducts;
    }

    // only needed for annotations schema as it instantiates fetchers outside spring
    public ProductListFetcher() {}

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        productAdaptor = contextRefreshedEvent.getApplicationContext().getBean(ProductAdaptor.class);
    }
}
