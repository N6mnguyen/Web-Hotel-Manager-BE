package com.codeWithProject.hotelServer.services.customerFeedback;


import com.codeWithProject.hotelServer.entity.CustomerFeedback;
import com.codeWithProject.hotelServer.repository.CustomerFeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerFeedbackServiceImpl implements CustomerFeedbackService{

    @Autowired
    private CustomerFeedbackRepository customerFeedbackRepository;

    public List<CustomerFeedback> getAllFeedbacks(){
        return customerFeedbackRepository.findAll();
    }

    public CustomerFeedback addFeedback(CustomerFeedback feedback) {
        return customerFeedbackRepository.save(feedback);
    }

    public void deleteFeedback(Long id) {
        customerFeedbackRepository.deleteById(id);
    }
}
