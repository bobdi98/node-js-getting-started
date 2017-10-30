package com.sfdo.ngp.po.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfdo.ngp.po.configuration.DataSourceProperties;

import feign.Feign;
import feign.Headers;
import feign.Logger;
import feign.Param;
import feign.RequestLine;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.extern.slf4j.Slf4j;
import me.roybailey.data.schema.ProductDto;


@Slf4j
@Service()
public class ProductAdaptor {

    @Autowired
    DataSourceProperties properties;

    @Headers("Accept: application/json")
    private interface ProductApi {

        @RequestLine("GET /product-api/v1/product")
        List<ProductDto> getAllProducts();

        @RequestLine("GET /product-api/v1/product/{id}")
        ProductDto getProductById(@Param("id") String id);

        @Headers("Content-Type: application/json")
        @RequestLine("POST /product-api/v1/product")
        ProductDto upsertProduct(ProductDto product);

        @RequestLine("DELETE /product-api/v1/product/{id}")
        ProductDto deleteProductById(@Param("id") String id);

    }

    private ProductApi api;

    @PostConstruct
    public void apiSetup() {
        //System.out.println("Connecting {} to {}", ProductApi.class.getSimpleName(), properties.getUrlProductService());
        this.api = Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logLevel(Logger.Level.BASIC)
                .target(ProductApi.class, properties.getUrlProductService());
    }


    public List<ProductDto> getAllProducts() {
        List<ProductDto> allProducts = api.getAllProducts();
        //System.out.println("getAllProducts() : {}", allProducts);
        return allProducts;
    }

    public ProductDto getProduct(String productId) {
        ProductDto product = api.getProductById(productId);
        //System.out.println("getProduct({}) : {}", productId, product);
        return product;
    }

    public ProductDto upsertProduct(ProductDto product) {
        ProductDto updatedProduct = api.upsertProduct(product);
        //System.out.println("createProduct({}) : {}", product, updatedProduct);
        return updatedProduct;
    }

    public ProductDto deleteProduct(String productId) {
        ProductDto deletedProduct = api.deleteProductById(productId);
        //System.out.println("deleteProduct({}) : {}", productId, deletedProduct);
        return deletedProduct;
    }

}

