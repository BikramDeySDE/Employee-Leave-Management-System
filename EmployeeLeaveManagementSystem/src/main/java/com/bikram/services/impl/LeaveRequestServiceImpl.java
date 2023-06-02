package com.bikram.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bikram.entities.Employee;
import com.bikram.entities.LeaveRequest;
import com.bikram.exceptions.ResourceNotFoundException;
import com.bikram.repository.EmployeeRepository;
import com.bikram.repository.LeaveRequestRepository;
import com.bikram.services.LeaveRequestService;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

	@Autowired
	private LeaveRequestRepository leaveRequestRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	// create leave request
	@Override
	public LeaveRequest createLeaveRequest(LeaveRequest leaveRequest, int employeeId) {
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("Employee Not Found with Employee ID : " + employeeId));
		leaveRequest.setEmployee(employee);
		leaveRequest.setLeaveStatus("Pending");
		LeaveRequest createdLeaveRequest = leaveRequestRepository.save(leaveRequest);
		return createdLeaveRequest;
	}

	@Override
	public String approveLeaveRequest(int leaveRequestId) {
		LeaveRequest leaveRequestFromDB = leaveRequestRepository.findById(leaveRequestId).orElseThrow(()->new ResourceNotFoundException("Leave Request Not Found with Request Id : " + leaveRequestId));
		
		String leaveType =  leaveRequestFromDB.getLeaveType();
		int leaveQuantity = leaveRequestFromDB.getLeaveQuantity();
		String leaveStatus = leaveRequestFromDB.getLeaveStatus();
		
		Employee employee = leaveRequestFromDB.getEmployee();
		if (leaveType.equalsIgnoreCase("sickLeave")) {
			
			if (leaveQuantity<=employee.getSickLeave() && leaveStatus.equalsIgnoreCase("Pending")) {
				int remainingSickLeave = employee.getSickLeave()-leaveRequestFromDB.getLeaveQuantity();
				employee.setSickLeave(remainingSickLeave);
				employeeRepository.save(employee);
				leaveRequestFromDB.setLeaveStatus("Approved");
			}
			
		} else if (leaveType.equalsIgnoreCase("casualLeave")) {
			
			if (leaveQuantity<=employee.getCasualLeave() && leaveStatus.equalsIgnoreCase("Pending")) {
				int remainingCasualLeave = employee.getCasualLeave()-leaveRequestFromDB.getLeaveQuantity();
				employee.setCasualLeave(remainingCasualLeave);
				employeeRepository.save(employee);
				leaveRequestFromDB.setLeaveStatus("Approved");
			}
			
		}
		
		
		leaveRequestRepository.save(leaveRequestFromDB);
		
		String message = "Your Leave Request has been approved. Now you have " + employee.getSickLeave() + " Sick Leaves and " + employee.getCasualLeave() + " Casual Leaves.";
		return message;
	}

	// reject leave request
	@Override
	public String rejectLeaveRequest(int leaveRequestId) {
		LeaveRequest leaveRequestFromDB = leaveRequestRepository.findById(leaveRequestId).orElseThrow(()->new ResourceNotFoundException("Leave Request Not Found with Request Id : " + leaveRequestId));
		leaveRequestFromDB.setLeaveStatus("Rejected");
		leaveRequestRepository.save(leaveRequestFromDB);
		String message = "Rejection for Leave Request having ID : " + leaveRequestId;
		return message;
	}
	
	// delete leave request
	public String deleteLeaveRequest(int leaveRequestId) {
		LeaveRequest leaveRequestFromDB = leaveRequestRepository.findById(leaveRequestId).orElseThrow(()->new ResourceNotFoundException("Leave Request Not Found with Request Id : " + leaveRequestId));
		leaveRequestRepository.delete(leaveRequestFromDB);
		String message = "DELETED : Leave Request having ID : " + leaveRequestId;
		return message;
	}

}
