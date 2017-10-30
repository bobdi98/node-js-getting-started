package com.sfdo.ngp.po.configuration;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "data-source")
@PropertySource("application.yaml")
public class DataSourceProperties {

    @NotNull
    String urlProductService;

    @NotNull
    String urlUserService;

    @NotNull
    String urlOrderService;

	public String getUrlProductService() {
		return urlProductService;
	}

	public void setUrlProductService(String urlProductService) {
		this.urlProductService = urlProductService;
	}

	public String getUrlUserService() {
		return urlUserService;
	}

	public void setUrlUserService(String urlUserService) {
		this.urlUserService = urlUserService;
	}

	public String getUrlOrderService() {
		return urlOrderService;
	}

	public void setUrlOrderService(String urlOrderService) {
		this.urlOrderService = urlOrderService;
	}

    
}
