package com.ipnetinstitute.csc394.backend.controller;

import com.ipnetinstitute.csc394.backend.dao.StudentEntityRepository;
import com.ipnetinstitute.csc394.backend.dao.TeacherEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin
@RestController
public class StudentTeacherController {

    @Autowired
    TeacherEntityRepository teacherRepo;

    @Autowired
    StudentEntityRepository studentRepo;

    @RequestMapping(value = "/get/TeacherWithTheirUser", method = RequestMethod.GET)
    public List getfindAllTeacherWithTheirUser () {
        List result = new ArrayList();
        try {
            result=  teacherRepo.findAllTeacherWithTheirUser();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }
    @RequestMapping(value = "/get/StudentWithTheirUser", method = RequestMethod.GET)
    public List getfindAllStudentWithTheirUser () {
        List result = new ArrayList();
        try {
            result=  studentRepo.findAllStudentWithTheirUser();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }
}
