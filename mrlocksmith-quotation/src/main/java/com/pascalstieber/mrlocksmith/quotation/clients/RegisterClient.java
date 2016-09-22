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
import com.pascalstieber.mrlocksmith.quotation.data.User;

@Component
public class RegisterClient {

    private final Logger log = LoggerFactory.getLogger(RegisterClient.class);
    private String registerServiceHost;
    private long registerServicePort;
    private boolean useRibbon;
    private LoadBalancerClient loadBalancer;
    private RestTemplate restTemplate;

    @Autowired
    public RegisterClient(@Value("${register.service.host:register}") String registerServiceHost,
	    @Value("${register.service.port:8080}") long registerServicePort, @Value("${ribbon.eureka.enabled:false}") boolean useRibbon) {
	super();
	this.registerServiceHost = registerServiceHost;
	this.registerServicePort = registerServicePort;
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
	    ServiceInstance instance = loadBalancer.choose("REGISTER");
	    url = "http://" + instance.getHost() + ":" + instance.getPort() + "/";
	} else {
	    url = "http://" + registerServiceHost + ":" + registerServicePort + "/register/";
	}
	log.trace("Register: URL {} ", url);
	return url;
    }

    public User getUser(long userid) {
	try {
	    User user = restTemplate.getForObject(getRegisterURL() + "findUserById/" + userid, User.class);
	    return user;
	} catch (RestClientException e) {
	    log.error(e.toString());
	}
	return null;
    }

    public List<Adress> getAdresses(long userid) {
	try {
	    List<Adress> adresses = restTemplate.getForObject(getRegisterURL() +"findAdressesByUserid/" + userid, List.class);
	    return adresses;
	} catch (RestClientException e) {
	    log.error(e.toString());
	}
	return null;
    }
    
    public Adress getAdressById(long adressid) {
	try {
	    log.trace("getAdressById adressid=" + String.valueOf(adressid));
	    Adress adress = restTemplate.getForObject(getRegisterURL() +"findAdressById/" + adressid, Adress.class);
	    return adress;
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
