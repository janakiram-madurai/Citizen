package com.citizen.controller;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.citizen.common.CommonUtilities;
import com.citizen.entity.UserReport;
import com.citizen.repository.UserReportRepository;




@RestController
@PropertySource("classpath:citizen.properties")
public class AddReportController {
	
    @Autowired
    private  UserReportRepository userReportRepo;
    
    @Autowired
    private  CommonUtilities commonUtilities;
    
    @Value("${imageUploadPath}")
	public String UPLOAD_PATH;
    
    @Value("${imageRetieveURL}")
    public String imageRetieveURL;
    
   
	@PostMapping("/postReport")
	public ResponseEntity<String> uploadFile(@RequestParam ("file") MultipartFile file,@RequestParam ("reportTittle") String reportTittle,@RequestParam ("reportType") String reportType,@RequestParam ("reportDesc") String reportDesc) throws IOException
	{
		List<String> list = new ArrayList();
		list.add(reportTittle);
		list.add(reportType);
		list.add(reportDesc);
		saveUploadImage(file,reportTittle,reportType,reportDesc);
		String mailResponse = commonUtilities.sendMail(list);
		return new ResponseEntity<>(mailResponse, HttpStatus.OK);
	}
	
	public void saveUploadImage(MultipartFile file,String reportTittle,String reportType,String reportDesc) throws IOException
	{
		try
		{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		String dateString = format.format(new Date());
		Date   date       = format.parse(dateString);    
		
		String imagePath;
		byte[] bytefile = file.getBytes();
		Long currentTime= System.currentTimeMillis();
		Path files = Paths.get(UPLOAD_PATH+ currentTime + file.getOriginalFilename());
		imagePath = currentTime +  file.getOriginalFilename();
		userReportRepo.save(new UserReport().setReportTittle(reportTittle).setReportDesc(reportDesc).setReportType(reportType).setImageURL(imagePath).setReportDate(date)
				.setStatus("Open").setUser_id(1000));
		Files.write(files,bytefile);
		}
		catch(Exception e)
		{
			
		}
		
	}
	
	List<UserReport> userReport ;
	@CrossOrigin
	@GetMapping("/getReportedIssue")
	private List findAllReports() {
		
		  userReport = new ArrayList();
	      Iterable<UserReport> iterable = userReportRepo.findAll();
	      for (UserReport employee : iterable) {
	    	  employee.setImageURL(imageRetieveURL+employee.getImageURL());
	    	  userReport.add(employee);
	      }
	      
	      return userReport;
	  }
	
	@CrossOrigin
	@PutMapping("/updateReportIssue/{id}")
	private Map updateReport(@RequestBody UserReport userreport,@PathVariable("id") Long id) {
		
		try {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dateString = format.format(new Date());
		Date   currentDate = format.parse(dateString);    
		userReportRepo.findById(id).map(employee -> {
			employee.setComments(userreport.getComments());
			employee.setStatus(userreport.getStatus());
			employee.setModifiedDate(currentDate);
			return userReportRepo.save(employee);
		});
		}
		catch(Exception e)
		{
			System.out.println();
		}
		Map<String, Object> errorAttributes = new HashMap<>();
		errorAttributes.put("result", "Successfully Updated");
		 return errorAttributes;
	}
}

