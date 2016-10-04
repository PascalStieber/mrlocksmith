package com.pascalstieber.mrlocksmith.order.web;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pascalstieber.mrlocksmith.order.clients.QuotationClient;
import com.pascalstieber.mrlocksmith.order.clients.RegisterClient;
import com.pascalstieber.mrlocksmith.order.data.OrderEntity;
import com.pascalstieber.mrlocksmith.order.data.OrderRepository;

@Controller
public class OrderController {

    private static final String REDIRECT_ON_HOST = "redirect:http://192.168.99.100:8080/";
    private final Logger log = LoggerFactory.getLogger(OrderController.class);
    
    private OrderRepository orderRepository;
    private RegisterClient registerClient;
    private QuotationClient quotationClient;

    @Autowired
    public OrderController(OrderRepository orderRepo, RegisterClient registerClient, QuotationClient quotationClient) {
	this.orderRepository = orderRepo;
	this.registerClient = registerClient;
	this.quotationClient = quotationClient;
    }


    @RequestMapping(value = "/auth2", method = RequestMethod.GET)
    public String authorization2( OAuth2Authentication auth, Principal currentUser) {
	log.trace(">>>>clientid"+auth.getPrincipal().toString());
	return "redirect:http://192.168.178.26:8585/login";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
	OrderEntity order = new OrderEntity();

	return new ModelAndView("questionnaire1", "order", order);
    }

    // auswahl button ('car' || 'home' || 'garage') wurde gedrueckt
    @RequestMapping(value = "/questionnaire1Form.html", method = RequestMethod.POST)
    public ModelAndView postQuestionnaire1(OrderEntity order, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
	String homeOrCarParam = httpServletRequest.getParameter("homeOrCar");
	order.setHomeOrCar(homeOrCarParam);
	return new ModelAndView("questionnaire1", "order", order);
    }

    // button zum speichern wurde gedrueckt. im hidden feld werden die
    // informationen mit uebertragen, da nur die parameter eines buttons
    // uebertragen werden. grund dafuer liegt darin, dass die auswahl felder
    // selbst buttons sind und nicht ueber th:text oder th:field an dem order
    // object gebunden sind.
    @RequestMapping(value = "/questionnaire1Form.html", method = RequestMethod.POST, params = "forward=forward")
    public ModelAndView postQuestionnaire11(OrderEntity order, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
	String homeOrCarParam = httpServletRequest.getParameter("homeOrCar");
	order.setHomeOrCar(homeOrCarParam);
	if (order.getHomeOrCar().equals("home")) {
	    return new ModelAndView("questionnaireHome2", "order", order);
	}
	return new ModelAndView("questionnaireExpress", "order", order);
    }

    @RequestMapping(value = "/questionnaireHome2Form.html", method = RequestMethod.POST)
    public ModelAndView postQuestionnaireHome2(OrderEntity order, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
	String homeOrCar = httpServletRequest.getParameter("homeOrCar");
	order.setHomeOrCar(homeOrCar);
	boolean keyNotAvailable = Boolean.parseBoolean(httpServletRequest.getParameter("keyNotAvailable"));
	order.setKeyNotAvailable(keyNotAvailable);
	return new ModelAndView("questionnaireHome2", "order", order);
    }

    @RequestMapping(value = "/questionnaireHome2Form.html", method = RequestMethod.POST, params = "forward=forward")
    public ModelAndView postQuestionnaireHome22(OrderEntity order, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
	String homeOrCar = httpServletRequest.getParameter("homeOrCar");
	order.setHomeOrCar(homeOrCar);
	boolean keyNotAvailable = Boolean.parseBoolean(httpServletRequest.getParameter("keyNotAvailable"));
	order.setKeyNotAvailable(keyNotAvailable);
	return new ModelAndView("questionnaireHome3", "order", order);
    }

    @RequestMapping(value = "/questionnaireHome3Form.html", method = RequestMethod.POST)
    public ModelAndView postQuestionnaireHome3(OrderEntity order, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
	String homeOrCar = httpServletRequest.getParameter("homeOrCar");
	order.setHomeOrCar(homeOrCar);
	boolean keyNotAvailable = Boolean.parseBoolean(httpServletRequest.getParameter("keyNotAvailable"));
	order.setKeyNotAvailable(keyNotAvailable);
	String door = httpServletRequest.getParameter("door");
	order.setDoor(door);
	return new ModelAndView("questionnaireHome3", "order", order);
    }

    @RequestMapping(value = "/questionnaireHome3Form.html", method = RequestMethod.POST, params = "forward=forward")
    public ModelAndView postQuestionnaireHome33(OrderEntity order, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
	String homeOrCar = httpServletRequest.getParameter("homeOrCar");
	order.setHomeOrCar(homeOrCar);
	boolean keyNotAvailable = Boolean.parseBoolean(httpServletRequest.getParameter("keyNotAvailable"));
	order.setKeyNotAvailable(keyNotAvailable);
	String door = httpServletRequest.getParameter("door");
	order.setDoor(door);
	return new ModelAndView("questionnaireExpress", "order", order);
    }

    @RequestMapping(value = "/questionnaireExpressForm.html", method = RequestMethod.POST)
    public ModelAndView postQuestionnaireExpress(OrderEntity order, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
	String homeOrCar = httpServletRequest.getParameter("homeOrCar");
	order.setHomeOrCar(homeOrCar);
	boolean keyNotAvailable = Boolean.parseBoolean(httpServletRequest.getParameter("keyNotAvailable"));
	order.setKeyNotAvailable(keyNotAvailable);
	String door = httpServletRequest.getParameter("door");
	order.setDoor(door);
	boolean express = Boolean.parseBoolean(httpServletRequest.getParameter("express"));
	order.setKeyNotAvailable(express);
	return new ModelAndView("questionnaireExpress", "order", order);
    }

    @RequestMapping(value = "/questionnaireExpressForm.html", method = RequestMethod.POST, params = "forward=forward")
    public ModelAndView postQuestionnaireExpress2(OrderEntity order, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
	String homeOrCar = httpServletRequest.getParameter("homeOrCar");
	order.setHomeOrCar(homeOrCar);
	boolean keyNotAvailable = Boolean.parseBoolean(httpServletRequest.getParameter("keyNotAvailable"));
	order.setKeyNotAvailable(keyNotAvailable);
	String door = httpServletRequest.getParameter("door");
	order.setDoor(door);
	boolean express = Boolean.parseBoolean(httpServletRequest.getParameter("express"));
	order.setKeyNotAvailable(express);
	order = orderRepository.save(order);
	return new ModelAndView(REDIRECT_ON_HOST + "register/registerCustomerWithOrder.html?orderid=" + order.getId());
    }

    @RequestMapping(value = "/associateUserAndAdressToOrder.html", method = RequestMethod.GET)
    public ModelAndView associateUserToOrder(@RequestParam("orderid") long orderid, @RequestParam("userid") long userid,
	    @RequestParam("adressid") long adressid) {
	OrderEntity order = orderRepository.findOne(orderid);
	order.setUserid(userid);
	order.setAdressid(adressid);
	orderRepository.save(order);
	return new ModelAndView("showCustomersQuotations");
    }

    @RequestMapping(value = "/showCustomersQuotations.html", method = RequestMethod.GET)
    public ModelAndView findAllOrders() {
	Iterable<OrderEntity> orders = orderRepository.findAll();
	return new ModelAndView("showCustomersQuotations", "orders", orders);
    }

    @RequestMapping(value = "/acceptQuotation.html", method = RequestMethod.POST)
    public ModelAndView acceptQuotation(OrderEntity order, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
	long quotationid = Long.parseLong(httpServletRequest.getParameter("acceptQuotation"));
	quotationClient.acceptQuotation(quotationid);
	return new ModelAndView("showCustomersQuotations", "order", order);
    }

    @RequestMapping(value = "/findOrderByUserid/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody OrderEntity findOrder(@PathVariable("id") long id) {
	OrderEntity order = orderRepository.findByUserid(id);
	return order;
    }

    @RequestMapping(value = "/findOrderByOrderid/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody OrderEntity findOrderByOrderid(@PathVariable("id") long id) {
	OrderEntity order = orderRepository.findOne(id);
	return order;
    }

    @RequestMapping(value = "/findAllOrders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Iterable<OrderEntity> findAllOrder() {
	Iterable<OrderEntity> order = orderRepository.findAll();
	return order;
    }

}
