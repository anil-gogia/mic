package org.test.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/notification")
public class NotificationController {

	
    @RequestMapping(method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody Map<String, String> getTest(@RequestBody Map<String, Long> params) {
		
		System.out.println("*********** MyService - NotificationController:getTest() called ***********");
        System.out.println("GET TEST: " + params.get("id"));

        Map<String, String> response = new HashMap<>();
	
        response.put("name", "My Service");

        return response;
    }
    
    
    @RequestMapping(value = "/email", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody Map<String, String> sendNotifiaction(@RequestBody Map<String, String> notificationMap) {
		
		System.out.println("*********** Sending email notification to : " +notificationMap.get("to"));
/*		System.out.println("*********** Email is being sent from  email notification to : " +notificationMap.get("to"));
		System.out.println("*********** Sending email notification to : " +notificationMap.get("to"));
        
		 //vars.put("to", "a@a.com");
	        vars.put("from", "admin@a.com");
	        vars.put("message", "Thanks for registering to our portal. Your account has been created.");
*/	        
	        
		

		
        Map<String, String> response = new HashMap<>();
	
        response.put("status", "NOTIFICATION_SENT");
        
        
        return response;
    }
    

}
