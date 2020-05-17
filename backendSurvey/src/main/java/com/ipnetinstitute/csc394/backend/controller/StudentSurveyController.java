package com.ipnetinstitute.csc394.backend.controller;

import java.util.ArrayList;
import java.util.List;

import com.ipnetinstitute.csc394.backend.dao.StudentCouseEntityRepository;
import com.ipnetinstitute.csc394.backend.dao.StudentSurveyEntityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class StudentSurveyController {

    @Autowired
    private StudentSurveyEntityRepository studentSurveyRepo;

    @GetMapping("/getStudentSurveyByCourse/{id}")
    public List getStudentSurveyByCourse(@PathVariable("id") Integer id) {

        List result = new ArrayList();
        try {
            result = (List) studentSurveyRepo.findStudentSurveyByCourse(id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }


}
