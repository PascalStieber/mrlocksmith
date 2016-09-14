package com.pascalstieber.mrlocksmith.register.clients;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pascalstieber.mrlocksmith.register.Testentity;

@Component
public class IndexClient {

    private final Logger log = LoggerFactory.getLogger(IndexClient.class);
    private String indexServiceHost;
    private long indexServicePort;
    private boolean useRibbon;
    private LoadBalancerClient loadBalancer;
    private RestTemplate restTemplate;

    @Autowired
    public IndexClient(@Value("${index.service.host:index}") String indexServiceHost, @Value("${index.service.port:8080}") long indexServicePort,
	    @Value("${ribbon.eureka.enabled:false}") boolean useRibbon) {
	super();
	this.indexServiceHost = indexServiceHost;
	this.indexServicePort = indexServicePort;
	this.useRibbon = useRibbon;
	this.restTemplate = getRestTemplate();
    }

    @Autowired(required = false)
    public void setLoadBalancer(LoadBalancerClient loadBalancer) {
	this.loadBalancer = loadBalancer;
    }

    public String getIndexURL() {
	String url;
	if (useRibbon) {
	    ServiceInstance instance = loadBalancer.choose("INDEX");
	    url = "http://" + instance.getHost() + ":" + instance.getPort() + "/";
	} else {
	    url = "http://" + indexServiceHost + ":" + indexServicePort + "/index/";
	}
	log.trace("Index: URL {} ", url);
	return url;
    }

    public Testentity getOne(long testId) {
	Testentity lEntity = new Testentity();
	try {
	    lEntity = restTemplate.getForObject(getIndexURL() + testId + ".html", Testentity.class);
	} catch (RestClientException e) {
	    log.trace(e.toString());
	}
	return lEntity;
    }

    protected RestTemplate getRestTemplate() {
	ObjectMapper mapper = new ObjectMapper();
	mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	mapper.registerModule(new Jackson2HalModule());

	MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
	converter.setSupportedMediaTypes(Arrays.asList(MediaTypes.HAL_JSON));
	converter.setObjectMapper(mapper);

	return new RestTemplate(Collections.<HttpMessageConverter<?>> singletonList(converter));
    }
}
