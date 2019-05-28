package com.citizen.common;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Configuration
@PropertySource("classpath:citizen.properties")
public class CommonUtilities {
	
	
	@Autowired
    private JavaMailSender sender;
    
	@Value("${adminMailID}")
	public String adminMailID;
    
	@Bean
   	public String sendMail(List<String> list) {
		 try {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        if(null != list && list.size() > 0)
        {
         helper.setTo(adminMailID);
            helper.setText("Dear Admin : Welcome to Citizen World)");
            helper.setSubject("Alert: User has been Register with ." + list.get(0) + " "+ "Title");
            sender.send(message);

            return "Report Added Successfully!";
        }
        
		 
        else
        {
        	  return "Report Added Successfully Without Mail!";
        }
		   }
        catch (MessagingException e) {
            e.printStackTrace();
            return "Error while sending mail ..";
        }
    }
	
	


}
