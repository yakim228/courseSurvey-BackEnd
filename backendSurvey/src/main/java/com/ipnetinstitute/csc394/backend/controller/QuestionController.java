package com.ipnetinstitute.csc394.backend.controller;

import java.util.ArrayList;
import java.util.List;



import com.ipnetinstitute.csc394.backend.dao.CategorieSurveyQuestionEntityRepository;
import com.ipnetinstitute.csc394.backend.dao.CategorySurveyEntityRepository;
import com.ipnetinstitute.csc394.backend.dao.QuestionEntityRepository;
// import com.ipnetinstitute.csc394.backend.entity.CatSurvey;
import com.ipnetinstitute.csc394.backend.entity.Question;


import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.jpa.repository.support.EntityManagerBeanDefinitionRegistrarPostProcessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RequestMapping(value="/question")
public class QuestionController  {
	
    @Autowired
    private QuestionEntityRepository questionRepo;

    @Autowired
    private CategorieSurveyQuestionEntityRepository cat_survey_questionRepo;
    @Autowired
    private CategorySurveyEntityRepository cat_surveyRepo;


    // @Autowired
    // QuestionService queryservice;

    @GetMapping("/questiontest")
    public List<Question> getQuery()
    {
        return (List) questionRepo.listdesquestionparcategorie();
    }



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
    // @Autowired
    // EntityManagerFactory emf;


    // @RequestMapping(value = "/questiontest", method = RequestMethod.GET)
    // public List questionCategory() throws ClassNotFoundException {
    //    EntityManager em = emf.createEntityManager();

    // //    Query query = em.createQuery("select  q.title from  question q inner join cat_survey_question  csq on q.id =csq.id_question inner join cat_survey cs on csq.id_cat_survey = cs.id  where csq.id_cat_survey = 8 group by q.id");
    //    Query query = em.createQuery("select title from question");
    //     List result = new ArrayList();
    //     try {
    //         // result = (List)questionRepo.listQuestionfromACategory(id);
    //         result = (List) query.getResultList();
    //         em.close();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     } finally {
    //         return result;
    //     }
    // }
    // public <T extends BaseEntity> Optional<T> getById(@PathVariable("id") Integer id) throws ClassNotFoundException {
    //     Optional<T> result = null;
    //     try {
    //         result = (Optional<T>) repos.get(entity).findById(id);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     } finally {
    //         return result;
    //     }
    // }
	
	

}
 
