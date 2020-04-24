package com.ipnetinstitute.csc394.backend.dao;

import java.util.List;

import javax.transaction.Transactional;

import com.ipnetinstitute.csc394.backend.entity.Question;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.hibernate.query.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Transactional
public interface QuestionEntityRepository extends BaseEntityRepository<Question> {
    
    // @Query(value = "select id from  cat_survey_question ", nativeQuery = true)
    @Query(value = "select  q.* from  question q inner join cat_survey_question  csq on q.id =csq.id_question inner join cat_survey cs on csq.id_cat_survey = cs.id  where csq.id_cat_survey = 8 group by q.id ", nativeQuery = true)
    List listdesquestionparcategorie() ;

    @Query(value = "select  q.* from  question q inner join cat_survey_question  csq on q.id =csq.id_question inner join cat_survey cs on csq.id_cat_survey = cs.id  where csq.id_cat_survey = :CategoryID group by q.id ", nativeQuery = true)
    List findAllQuestionbyCategory(@Param("CategoryID") Integer id);

}
