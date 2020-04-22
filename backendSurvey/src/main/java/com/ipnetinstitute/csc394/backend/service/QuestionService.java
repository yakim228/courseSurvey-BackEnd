package com.ipnetinstitute.csc394.backend.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.ipnetinstitute.csc394.backend.dao.QuestionEntityRepository;
import com.ipnetinstitute.csc394.backend.entity.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class QuestionService implements IQueryService {
@Autowired
QuestionEntityRepository questionRepository;

@Autowired
EntityManagerFactory emf;

@Override 
public List<Question> JPQLQueryforQuestion(){
    
    EntityManager em = emf.createEntityManager();
    Query query = em.createQuery("select  q.title from  question q inner join cat_survey_question  csq on q.id =csq.id_question inner join cat_survey cs on csq.id_cat_survey = cs.id  where csq.id_cat_survey = 8 group by q.id");
    @SuppressWarnings("unchecked")
    List<Question> list = (List<Question>)query.getResultList();
    System.out.println("Student Name :");
    em.close();

    return list;
}
 


}