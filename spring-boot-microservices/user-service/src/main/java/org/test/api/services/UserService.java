package org.test.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.test.api.services.exception.UserAlreadyExistsException;
import org.test.api.services.repository.CustomerRepository;
import org.test.api.services.domain.Customer;

import java.util.HashMap;
import java.util.Map;


import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    
    @Autowired
    private CustomerRepository repository;

    /*@Inject
    public UserService(final CustomerRepository repository) {
        this.repository = repository;
    }*/
    
    @Autowired
    private RestTemplate restTemplate;

    private final String notificationServiceUrl = "http://notification-service";

   /* public UserService() {
        this.serviceUrl = "http://notification-service";
    }
*/
    public Map<String, String> getTest() {
    	
    	System.out.println("*********UserService()***** About to call the notification service************");
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

        // Add converters
        // Note I use the Jackson Converter, I removed the http form converter
        // because it is not needed when posting String, used for multipart forms.
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        Map<String, Long> vars = new HashMap<>();
        vars.put("id", 1L);

        return restTemplate.postForObject(notificationServiceUrl+"/notification", vars, Map.class);
    }
    
    
    public Map<String, String> sendNotication(){
    	System.out.println("*********About to send the notification to the user************");
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
                
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        
        //Setting up data to be sent to REST service
        Map<String, String> vars = new HashMap<String,String>();
        vars.put("to", "a@a.com");
        vars.put("from", "admin@a.com");
        vars.put("message", "Thanks for registering to our portal. Your account has been created.");
        

        return restTemplate.postForObject(notificationServiceUrl+"/notification/email", vars, Map.class);
        
    }
    
    public void saveUser(Customer user){
        LOGGER.debug("Creating {}", user);
        Customer existing = repository.findOne(user.getId());
        if (existing != null) {
            throw new UserAlreadyExistsException(
                    String.format("There already exists a user with id=%s", user.getId()));
        }
        //return repository.save(user);
    	
    }
    

    
    
    
    
}
