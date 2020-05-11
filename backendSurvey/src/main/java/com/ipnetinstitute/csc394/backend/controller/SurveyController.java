package com.ipnetinstitute.csc394.backend.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/getValidSurvey")
    public List getValidSurvey() {

        List result = new ArrayList();
        try {
            result = (List) surveyRepo.findAllSurveyStillValid();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }



     @GetMapping("/getSurveybyCategoryAndCourse/{CategoryID}/{CourseID}")
    public List getSurveybyCategoryAndCourse(@PathVariable("CategoryID") Integer CategoryID, @PathVariable("CourseID") Integer CourseID) {

        List result = new ArrayList();
        try {
            result = (List) surveyRepo.findbyCategoryAndCourse(CategoryID, CourseID);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }


    @GetMapping("/getPendingSurvey/{userId}")
    public List getPendingSurvey(@PathVariable("userId") Integer userId) {

        List result = new ArrayList();
        try {
            result = (List) surveyRepo.pendingSurvey(userId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

}
