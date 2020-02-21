package com.myorg.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myorg.objects.v1.EmployeeProfileResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = { "/api" })
@Slf4j
public class BankEmployeeController {

	/*
	 * @GetMapping(value = "/findUser", produces = MediaType.APPLICATION_JSON_VALUE)
	 * public ResponseEntity<EmployeeProfileResponse> getEmployeeProfile(
	 * 
	 * @Valid @RequestBody EmployeeProfileRequest request,
	 * 
	 * Errors error) { // log.info("Fetching For User -> {} ", request.getUserId());
	 * 
	 * 
	 * EmployeeProfileResponse response=new EmployeeProfileResponse();
	 * 
	 * String transactionID="1234"; log.debug("transactionID  is -> {}  ",
	 * transactionID);
	 * 
	 * 
	 * response.setEmpId("1234"); response.setEmailId("xyz@domain.com");
	 * response.setFullName("Prabhu M"); response.setMobileNumber("989898989898");
	 * 
	 * 
	 * ResponseCode responseCode=ResponseCodeConfig.FETCH_USER_SUCCESS;
	 * 
	 * return ResponseUtil.responseStatusOK(response, responseCode);
	 * 
	 * }
	 */

	@GetMapping(value = "/findAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EmployeeProfileResponse>> listAllUsers() {
		List<EmployeeProfileResponse> users = new ArrayList<EmployeeProfileResponse>();

		EmployeeProfileResponse response = new EmployeeProfileResponse();

		String transactionID = "1234";
		
		log.debug("transactionID  is -> {}  ", transactionID);

		response.setEmpId("1234");
		response.setEmailId("xyz_abc_demo@domain.com");
		response.setFullName("Prabhu M");
		response.setMobileNumber("989898989898");
		users.add(response);
		
		System.out.println("New Request");
		if (users.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<EmployeeProfileResponse>>(users, HttpStatus.OK);
	}

}
