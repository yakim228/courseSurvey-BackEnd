package com.ipnetinstitute.csc394.backend.controller;

import java.util.ArrayList;
import java.util.List;



import com.ipnetinstitute.csc394.backend.dao.CategorieSurveyQuestionEntityRepository;
import com.ipnetinstitute.csc394.backend.dao.CategorySurveyEntityRepository;
import com.ipnetinstitute.csc394.backend.dao.QuestionEntityRepository;
// import com.ipnetinstitute.csc394.backend.entity.CatSurvey;
import com.ipnetinstitute.csc394.backend.entity.Question;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.data.jpa.repository.support.EntityManagerBeanDefinitionRegistrarPostProcessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
// @RequestMapping(value="/question")
public class QuestionController  {
	
    @Autowired
    private QuestionEntityRepository questionRepo;

    // @Autowired
    // private CategorieSurveyQuestionEntityRepository cat_survey_questionRepo;
    // @Autowired
    // private CategorySurveyEntityRepository cat_surveyRepo;


    // @Autowired
    // QuestionService queryservice;

    // @GetMapping("/questiontest")
    // public List<Question> getQuery()
    // {
    //     // return (List) questionRepo.listdesquestionparcategorie();
    // }



    @GetMapping("/getQuestionByCategory/{id}")
    public List getQuestionByCategory(@PathVariable("id") Integer id){

        List result = new ArrayList();
        try {
            result = (List)questionRepo.findAllQuestionbyCategory(id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
    }
    }
   
    @GetMapping("/getPendingSurveyQuestions/{surveyId}/{userId}")
    public List getPendingSurveyQuestions(@PathVariable("surveyId") Integer surveyId,
            @PathVariable("userId") Integer userId) {

        List result = new ArrayList();
        try {
            result = (List) questionRepo.pendingSurveyQuestions(surveyId, userId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }
	

}
 
