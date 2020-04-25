package com.ipnetinstitute.csc394.backend.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ipnetinstitute.csc394.backend.dao.SurveyEntityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
public class SurveyController {
    
    @Autowired
    private SurveyEntityRepository surveyRepo;
    
	@GetMapping("/getAllSurveyByStudent/{id}")
    public List getAllSurveyByStudent(@PathVariable("id") Integer id) {

        List result = new ArrayList();
        try {
            result = (List) surveyRepo.findAllSurveyRespondbyUser(id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

}
