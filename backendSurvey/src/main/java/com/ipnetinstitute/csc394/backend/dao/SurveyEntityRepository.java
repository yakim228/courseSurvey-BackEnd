package com.ipnetinstitute.csc394.backend.dao;

import java.util.List;

import javax.transaction.Transactional;


import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.ipnetinstitute.csc394.backend.entity.Survey;


@Transactional
public interface SurveyEntityRepository extends BaseEntityRepository<Survey>  {
    
    @Query(value = "select sv.* from survey sv inner join student_survey " +
     "studs on sv.id = studs.id_survey  inner join student s " +
      "on studs.id_student = s.id   where s.id = :UserID  ", nativeQuery = true)
    List findAllSurveyRespondbyUser(@Param("UserID") Integer UserID);


    @Query(value = "SELECT * FROM survey s2 WHERE s2.end_date >= now()", nativeQuery = true)
    List findAllSurveyStillValid();


    @Query(value = "select * from survey sur where sur.id_cat_survey = :CategoryID and sur.id_course = :CourseID", nativeQuery = true)
    List findbyCategoryAndCourse(@Param("CategoryID") Integer id_cat_survey , @Param("CourseID") Integer id_course);

    
    @Query(value= "SELECT DISTINCT S.* FROM survey S INNER JOIN cat_survey CS ON (S.id_cat_survey = CS.id)" + 
            " INNER JOIN cat_survey_question CSQ ON (CSQ.id_cat_survey= CS.id) " +
            "WHERE CSQ.id_question NOT IN (SELECT SS.id_question " +
             "FROM student_survey SS, student ST WHERE ST.id = SS.id_student AND  ST.id_user = :UserID);", nativeQuery = true)
    List pendingSurvey(@Param("UserID") Integer id_user);


    


}
