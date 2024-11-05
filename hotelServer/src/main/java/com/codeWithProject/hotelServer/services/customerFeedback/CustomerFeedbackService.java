package com.codeWithProject.hotelServer.services.customerFeedback;

import com.codeWithProject.hotelServer.entity.CustomerFeedback;

import java.util.List;

public interface CustomerFeedbackService {

    List<CustomerFeedback> getAllFeedbacks();

    CustomerFeedback addFeedback(CustomerFeedback feedback);

    void deleteFeedback(Long id);
}
