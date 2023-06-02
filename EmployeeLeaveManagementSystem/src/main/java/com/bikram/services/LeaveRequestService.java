package com.bikram.services;

import com.bikram.entities.LeaveRequest;

public interface LeaveRequestService {
	
	LeaveRequest createLeaveRequest(LeaveRequest leaveRequest, int employeeId);

	String approveLeaveRequest(int leaveRequestId);
	
	String rejectLeaveRequest(int leaveRequestId);
	
	String deleteLeaveRequest(int leaveRequestId);
	
}
