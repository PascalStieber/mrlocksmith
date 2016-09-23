package com.pascalstieber.mrlocksmith.register.web;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pascalstieber.mrlocksmith.register.data.Adress;
import com.pascalstieber.mrlocksmith.register.data.AdressRepository;
import com.pascalstieber.mrlocksmith.register.data.Contractor;
import com.pascalstieber.mrlocksmith.register.data.User;
import com.pascalstieber.mrlocksmith.register.data.UserRepository;

@Controller
public class RegisterController {
    private final Logger log = LoggerFactory.getLogger(RegisterController.class);
    private UserRepository userRepository;
    private AdressRepository adressRepository;

    private static final String REDIRECT_ON_HOST = "redirect:http://192.168.99.100:8080/";

    @Autowired
    public RegisterController(UserRepository customerRepository, AdressRepository adressRepository) {
	this.userRepository = customerRepository;
	this.adressRepository = adressRepository;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Principal principal) {
	return "registerIndex";
    }

    @RequestMapping(value = "/findUserById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody User findUser(@PathVariable("id") long id) {
	User user = userRepository.findById(id);
	return user;
    }

    @RequestMapping(value = "/findAdressesByUserid/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Adress> getUser(@PathVariable("id") long id) {
	List<Adress> adressList = userRepository.getUserAdressesByUserId(id);
	return adressList;
    }

    @RequestMapping(value = "/findAdressById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Adress getAdressById(@PathVariable("id") long id) {
	Adress adress = adressRepository.findOne(id);
	return adress;
    }

    @RequestMapping(value = "/registerContractor.html", method = RequestMethod.GET)
    public ModelAndView addContractor() {
	Contractor lContractor = new Contractor();
	lContractor.addAdress(new Adress());
	return new ModelAndView("registerContractor", "contractor", lContractor);
    }

    @RequestMapping(value = "/registerCustomerWithOrder.html", method = RequestMethod.GET)
    public ModelAndView addCustomerWithOrder(@RequestParam("orderid") long orderid) {
	User user = new User();
	user.addAdress(new Adress());
	if (orderid != 0) {
	    user.setOrderid(orderid);
	}
	return new ModelAndView("registerCustomer", "user", user);
    }

    @RequestMapping(value = "/registerCustomer.html", method = RequestMethod.GET)
    public ModelAndView addCustomer() {
	User user = new User();
	user.setRole("Customer");
	user.addAdress(new Adress());
	return new ModelAndView("registerCustomer", "user", user);
    }

    // create
    @RequestMapping(value = "/formCustomer.html", method = RequestMethod.POST)
    public ModelAndView postCustomer(@Valid User user, BindingResult bindingResult, HttpServletRequest httpRequest) {
	if (bindingResult.hasErrors()) {
	    return new ModelAndView("registerCustomer");
	}
	user = userRepository.save(user);

	if (user.getOrderid() != 0) {
	    Adress adress = userRepository.getOneUserAdressesByUseridAndOrderid(user.getId(), user.getOrderid());
	    String url = REDIRECT_ON_HOST + "order/associateUserAndAdressToOrder.html?orderid=%s&userid=%s&adressid=%s";
	    String formattedURL = String.format(url, user.getOrderid(), user.getId(), adress.getId());
	    return new ModelAndView(formattedURL);
	}

	return new ModelAndView(REDIRECT_ON_HOST + "index/");
    }

    // create
    @RequestMapping(value = "/formContractor.html", method = RequestMethod.POST)
    public ModelAndView postContractor(@Valid Contractor contractor, BindingResult bindingResult, HttpServletRequest httpRequest) {
	if (bindingResult.hasErrors()) {
	    return new ModelAndView("registerContractor");
	}
	contractor.setRole("contractor");
	contractor = userRepository.save(contractor);
	return new ModelAndView(REDIRECT_ON_HOST + "quotation/");
    }

}
