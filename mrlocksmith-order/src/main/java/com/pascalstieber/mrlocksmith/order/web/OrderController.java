package com.pascalstieber.mrlocksmith.order.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
	return new ModelAndView("questionnaire1");
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
//    public ModelAndView Item(@PathVariable("id") long id) {
//	
//	return new ModelAndView("layout", "testentity", lTestE);
//    }

}
