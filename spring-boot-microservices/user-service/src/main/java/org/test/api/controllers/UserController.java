package org.test.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.test.api.services.UserService;
import org.test.api.services.domain.Customer;
import org.test.api.services.domain.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/service")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
    @Autowired
    UserService userService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public @ResponseBody Map<String, String> getTest() {
    	System.out.println("*********MyServiceController:getTest()*********");
        return userService.getTest();
    }

    

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<ResponseStatus> createUser(@RequestBody @Valid final Customer user) {
        System.out.println("Received request to create the " + user);
        
        userService.saveUser(user);
        System.out.println("**********Customer has been created**********");
        
        // This is going to invoke notification microservice
        userService.sendNotication();
        
        System.out.println("**********Email notification sent**********");
                       
        ResponseStatus response = new ResponseStatus();
        response.setStatus("1");
        response.setMessage("Customer has been created sucessfully and an email notification was sent to him");
        
        return new ResponseEntity<ResponseStatus>(response,HttpStatus.OK);
    }
    
    
    
    
}
