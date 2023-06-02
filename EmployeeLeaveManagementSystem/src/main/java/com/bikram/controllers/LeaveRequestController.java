package com.bikram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bikram.entities.LeaveRequest;
import com.bikram.services.LeaveRequestService;

@RestController
public class LeaveRequestController {

	@Autowired
	private LeaveRequestService leaveRequestService;
	
	// create leave request
	@PostMapping("/employee/{employeeId}/create-leave-request")
	ResponseEntity<LeaveRequest> createLeaveRequest(@RequestBody LeaveRequest leaveRequest, @PathVariable int employeeId){
		LeaveRequest createdLeaveRequest = leaveRequestService.createLeaveRequest(leaveRequest,employeeId);
		return new ResponseEntity<LeaveRequest>(createdLeaveRequest,HttpStatus.CREATED);
	}
	
	// approve leave request
	@PutMapping("/approve-leave-request/{leaveRequestId}")
	public ResponseEntity<String> approveLeaveRequest(@PathVariable int leaveRequestId){
		String message = leaveRequestService.approveLeaveRequest(leaveRequestId);
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
	
	
	// reject leave request
	@PutMapping("/reject-leave-request/{leaveRequestId}")
	public ResponseEntity<String> rejectLeaveRequest(@PathVariable int leaveRequestId){
		String message = leaveRequestService.rejectLeaveRequest(leaveRequestId);
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
	
	// delete leave request
	@PutMapping("/delete-leave-request/{leaveRequestId}")
	public ResponseEntity<String> deleteLeaveRequest(@PathVariable int leaveRequestId){
		String message = leaveRequestService.deleteLeaveRequest(leaveRequestId);
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
	
}
