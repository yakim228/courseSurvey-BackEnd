package com.ipnetinstitute.csc394.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ipnetinstitute.csc394.backend.dao.CourseEntityRepository;
import com.ipnetinstitute.csc394.backend.entity.Course;

@CrossOrigin
@RestController
public class CoursController  {
    
    @Autowired
    private CourseEntityRepository courseRepo;

    @GetMapping("/getCourseByTeacher/{id}")
	public List getCourseByTeacher(@PathVariable("id") Integer id) {

        List result = new ArrayList();
        try {
            result = (List) courseRepo.findCousebyTeacher(id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

}

    



