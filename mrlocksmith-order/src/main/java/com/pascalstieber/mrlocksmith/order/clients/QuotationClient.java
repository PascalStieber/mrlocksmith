package com.pascalstieber.mrlocksmith.order.clients;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pascalstieber.mrlocksmith.order.data.Adress;
import com.pascalstieber.mrlocksmith.order.data.Item;
import com.pascalstieber.mrlocksmith.order.data.Quotation;
import com.pascalstieber.mrlocksmith.order.data.User;

@Component
public class QuotationClient {

    private final Logger log = LoggerFactory.getLogger(QuotationClient.class);
    private String quotationServiceHost;
    private long quotationServicePort;
    private boolean useRibbon;
    private LoadBalancerClient loadBalancer;
    private RestTemplate restTemplate;

    @Autowired
    public QuotationClient(@Value("${quotation.service.host:quotation}") String quotationServiceHost,
	    @Value("${quotation.service.port:8080}") long quotationServicePort, @Value("${ribbon.eureka.enabled:false}") boolean useRibbon) {
	super();
	this.quotationServiceHost = quotationServiceHost;
	this.quotationServicePort = quotationServicePort;
	this.useRibbon = useRibbon;
	this.restTemplate = getRestTemplate();
    }

    @Autowired(required = false)
    public void setLoadBalancer(LoadBalancerClient loadBalancer) {
	this.loadBalancer = loadBalancer;
    }

    public String getRegisterURL() {
	String url;
	if (useRibbon) {
	    ServiceInstance instance = loadBalancer.choose("QUOTATION");
	    url = "http://" + instance.getHost() + ":" + instance.getPort() + "/";
	} else {
	    url = "http://" + quotationServiceHost + ":" + quotationServicePort + "/quotation/";
	}
	log.trace("Quotation: URL {} ", url);
	return url;
    }

    public List<Item> findAllItemsByQuotationId(long quotationid) {
	try {
	    List<Item> items = restTemplate.getForObject(getRegisterURL() + "findAllItemsByQuotationId/" + quotationid, List.class);
	    log.trace(">>>> rest aufruf:" + getRegisterURL() + "findAllItemsByQuotationId/" + quotationid);
	    log.trace(">>>>Anzahl ermittelter items: " + items.size());
	    return items;
	} catch (RestClientException e) {
	    log.error(e.toString());
	}
	return null;
    }

    public List<Quotation> findAllQuotationsForOrder(long orderid) {
	try {
	    List<Quotation> quotation = restTemplate.getForObject(getRegisterURL() + "findAllQuotationsForOrder/" + orderid, List.class);
	    log.trace(">>>>Anzahl ermittelter quotations: " + quotation.size());
	    log.trace(">>>> rest aufruf:" + getRegisterURL() + "findAllQuotationsForOrder/" + orderid);
	    return quotation;
	} catch (RestClientException e) {
	    log.error(e.toString());
	}
	return null;
    }

    protected RestTemplate getRestTemplate() {
	ObjectMapper mapper = new ObjectMapper();
	mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	mapper.registerModule(new Jackson2HalModule());
	MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
	converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
	converter.setObjectMapper(mapper);
	return new RestTemplate(Collections.<HttpMessageConverter<?>> singletonList(converter));
    }

    public void acceptQuotation(long quotationid){
	try {
	    restTemplate.getForObject(getRegisterURL() + "acceptQuotation/" + quotationid, Boolean.class);
	} catch (RestClientException e) {
	    log.error(e.toString());
	}
    }

    public String getQuotationSum(long quotationid) {
	try {
	    String sum = restTemplate.getForObject(getRegisterURL() + "getSumOfQuotation/" + quotationid, String.class);
	    return sum;
	} catch (RestClientException e) {
	    log.error(e.toString());
	}
	return "";
    }
}
