package com.codeWithProject.hotelServer.repository;

import com.codeWithProject.hotelServer.entity.CustomerFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerFeedbackRepository extends JpaRepository<CustomerFeedback,Long> {
}
