package com.pascalstieber.mrlocksmith.index.web;

import java.security.Principal;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pascalstieber.mrlocksmith.index.Testentity;

@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(Principal principal) {
	return new ModelAndView("layout");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView Item(@PathVariable("id") long id) {
	Testentity lTestE = new Testentity();
	lTestE.setId(id);
	return new ModelAndView("layout", "testentity", lTestE);
    }

}
