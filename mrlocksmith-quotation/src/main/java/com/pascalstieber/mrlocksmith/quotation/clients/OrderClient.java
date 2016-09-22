package com.pascalstieber.mrlocksmith.quotation.clients;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pascalstieber.mrlocksmith.quotation.data.Adress;
import com.pascalstieber.mrlocksmith.quotation.data.OrderEntity;
import com.pascalstieber.mrlocksmith.quotation.data.User;

@Component
public class OrderClient {

    private final Logger log = LoggerFactory.getLogger(OrderClient.class);
    private String orderServiceHost;
    private long orderServicePort;
    private boolean useRibbon;
    private LoadBalancerClient loadBalancer;
    private RestTemplate restTemplate;

    @Autowired
    public OrderClient(@Value("${order.service.host:order}") String orderServiceHost,
	    @Value("${order.service.port:8080}") long orderServicePort, @Value("${ribbon.eureka.enabled:false}") boolean useRibbon) {
	super();
	this.orderServiceHost = orderServiceHost;
	this.orderServicePort = orderServicePort;
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
	    ServiceInstance instance = loadBalancer.choose("ORDER");
	    url = "http://" + instance.getHost() + ":" + instance.getPort() + "/";
	} else {
	    url = "http://" + orderServiceHost + ":" + orderServicePort + "/order/";
	}
	log.trace("Register: URL {} ", url);
	return url;
    }

    public OrderEntity getOrderByUserid(long userid) {
	try {
	    OrderEntity order = restTemplate.getForObject(getRegisterURL() + "findOrderByUserid/" + userid, OrderEntity.class);
	    return order;
	} catch (RestClientException e) {
	    log.error(e.toString());
	}
	return null;
    }
    public OrderEntity getOrderByOrderid(long orderid) {
	try {
	    OrderEntity order = restTemplate.getForObject(getRegisterURL() + "findOrderByOrderid/" + orderid, OrderEntity.class);
	    return order;
	} catch (RestClientException e) {
	    log.error(e.toString());
	}
	return null;
    }

    public List<OrderEntity> getAllOrders() {
	try {
	    List<OrderEntity> orders = restTemplate.getForObject(getRegisterURL() +"findAllOrders" , List.class);
	    log.trace("Anzahl ermittelter orders:"+orders.size());
	    return orders;
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
}
