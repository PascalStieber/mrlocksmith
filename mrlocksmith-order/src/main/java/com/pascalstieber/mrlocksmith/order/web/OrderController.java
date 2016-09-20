package com.pascalstieber.mrlocksmith.order.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pascalstieber.mrlocksmith.order.clients.RegisterClient;
import com.pascalstieber.mrlocksmith.order.data.OrderEntity;
import com.pascalstieber.mrlocksmith.order.data.OrderRepository;

@Controller
public class OrderController {

    private static final String REDIRECT_ON_HOST = "redirect:http://192.168.99.100:8080/";
    
    private OrderRepository orderRepository;
    private RegisterClient registerClient;
    
    @Autowired
    public OrderController(OrderRepository orderRepo, RegisterClient registerClient) {
	this.orderRepository = orderRepo;
	this.registerClient = registerClient;
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
	return new ModelAndView(REDIRECT_ON_HOST + "register/registerCustomerWithOrder.html?orderid="+ order.getId());
    }
    
    @RequestMapping(value = "/associateUserToOrder.html", method = RequestMethod.GET)
    public ModelAndView associateUserToOrder(@RequestParam("orderid") long orderid, @RequestParam("userid") long userid) {
	OrderEntity order = orderRepository.findById(orderid);
	order.setUserid(userid);
	orderRepository.save(order);
	return new ModelAndView("showCustomersQuotations");
    }
    
    @RequestMapping(value = "/showCustomersQuotations.html", method = RequestMethod.GET)
    public ModelAndView findAllOrders() {
	Iterable<OrderEntity> orders = orderRepository.findAll();
	return new ModelAndView("showCustomersQuotations", "orders", orders);
    }

    

}
