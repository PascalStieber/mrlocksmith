package com.pascalstieber.mrlocksmith.register.web;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pascalstieber.mrlocksmith.register.Adress;
import com.pascalstieber.mrlocksmith.register.Contractor;
import com.pascalstieber.mrlocksmith.register.User;
import com.pascalstieber.mrlocksmith.register.UserRepository;
import com.pascalstieber.mrlocksmith.register.clients.IndexClient;

@Controller
public class RegisterController {

    private UserRepository userRepository;
    private IndexClient indexClient;
    
    @Autowired
    public RegisterController(UserRepository customerRepository, IndexClient indexClient) {
	this.userRepository = customerRepository;
	this.indexClient = indexClient;
    }
    
    @RequestMapping(value = "/test7", method = RequestMethod.GET)
    public String test7(Principal principal) {
	indexClient.getOne(2);
	return "redirect:http://192.168.99.102:8080/index/";
    }  
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Principal principal) {
	return "registerIndex";
    }

    @RequestMapping(value = "/registerContractor.html", method = RequestMethod.GET)
    public ModelAndView addContractor() {
	Contractor lContractor = new Contractor();
	lContractor.addAdress(new Adress());
	return new ModelAndView("registerContractor", "contractor", lContractor);
    }

    @RequestMapping(value = "/registerCustomer.html", method = RequestMethod.GET)
    public ModelAndView addCustomer() {
	User lUser = new User();
	lUser.addAdress(new Adress());
	return new ModelAndView("registerCustomer", "user", lUser);
    }

    // update
    @RequestMapping(value = "/{id}.html", method = RequestMethod.PUT)
    public ModelAndView put(@PathVariable("id") long id, User user, HttpServletRequest httpRequest) {
	user.setId(id);
	userRepository.save(user);
	return new ModelAndView("/order/");
    }

    // create
    @RequestMapping(value = "/formCustomer.html", method = RequestMethod.POST)
    public ModelAndView postCustomer(@Valid User user, BindingResult bindingResult, HttpServletRequest httpRequest) {
	if (bindingResult.hasErrors()) {
	    return new ModelAndView("registerCustomer");
	}
	user = userRepository.save(user);
	return new ModelAndView("redirect:http://192.168.99.102:8080/index/");
    }

    // create
    @RequestMapping(value = "/formContractor.html", method = RequestMethod.POST)
    public ModelAndView postContractor(@Valid Contractor contractor, BindingResult bindingResult, HttpServletRequest httpRequest) {
	if (bindingResult.hasErrors()) {
	    return new ModelAndView("registerContractor");
	}
	contractor = userRepository.save(contractor);
	return new ModelAndView("/quotation/");
    }

}
