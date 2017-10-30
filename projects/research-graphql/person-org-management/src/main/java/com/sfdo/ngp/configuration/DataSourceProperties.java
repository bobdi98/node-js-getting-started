package com.sfdo.ngp.configuration;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "data-source")
public class DataSourceProperties {

    @NotNull
    String urlProductService;

    @NotNull
    String urlUserService;

    @NotNull
    String urlOrderService;
    
    @NotNull
    String urlPersonService;
    

}
