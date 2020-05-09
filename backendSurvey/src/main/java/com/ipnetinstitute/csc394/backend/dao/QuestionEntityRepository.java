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
    // @Query(value = "select  q.* from  question q inner join cat_survey_question  csq on q.id =csq.id_question inner join cat_survey cs on csq.id_cat_survey = cs.id  where csq.id_cat_survey = 8 group by q.id ", nativeQuery = true)
    // List listdesquestionparcategorie() ;

    @Query(value = "select  q.* from  question q inner join cat_survey_question  csq on q.id =csq.id_question inner join cat_survey cs on csq.id_cat_survey = cs.id  where csq.id_cat_survey = :CategoryID ", nativeQuery = true)
    List findAllQuestionbyCategory(@Param("CategoryID") Integer id);

    @Query(value = "SELECT Q.* FROM SURVEY S INNER JOIN CAT_SURVEY CS ON (S.ID_CAT_SURVEY = CS.ID) "
            + "INNER JOIN CAT_SURVEY_QUESTION CSQ " +

            " ON (CSQ.ID_CAT_SURVEY= CS.ID) INNER JOIN QUESTION Q ON (Q.ID = CSQ.ID_QUESTION) " +

            "WHERE S.ID=:surveyID AND CSQ.ID_QUESTION NOT IN (SELECT SS.ID_QUESTION FROM STUDENT_SURVEY SS, "
            + "STUDENT ST WHERE ST.ID = SS.ID_STUDENT AND  ST.ID_USER = :userID)", nativeQuery = true)
    List pendingSurveyQuestions(@Param("surveyID") Integer id_survey, @Param("userID") Integer id_user);

}
