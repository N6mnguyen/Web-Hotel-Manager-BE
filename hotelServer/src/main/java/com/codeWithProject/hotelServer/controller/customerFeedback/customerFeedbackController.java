package com.codeWithProject.hotelServer.controller.customerFeedback;


import com.codeWithProject.hotelServer.entity.CustomerFeedback;
import com.codeWithProject.hotelServer.services.customerFeedback.CustomerFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedbacks/")
public class customerFeedbackController {

    @Autowired
    private CustomerFeedbackService customerFeedbackService;

    @GetMapping
    public List<CustomerFeedback> getAllFeedbacks() {
        return customerFeedbackService.getAllFeedbacks();
    }

    @PostMapping
    public CustomerFeedback addFeedback(@RequestBody CustomerFeedback feedback) {
        return customerFeedbackService.addFeedback(feedback);
    }

    @DeleteMapping("/{id}")
    public void deleteFeedback(@PathVariable Long id) {
        customerFeedbackService.deleteFeedback(id);
    }
}
