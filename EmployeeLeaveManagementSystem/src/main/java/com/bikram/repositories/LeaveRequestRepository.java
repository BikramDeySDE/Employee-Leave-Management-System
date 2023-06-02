package com.bikram.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bikram.entities.LeaveRequest;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Integer> {

}
