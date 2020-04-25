package com.ipnetinstitute.csc394.backend.dao;

import java.util.List;

import javax.transaction.Transactional;


import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.ipnetinstitute.csc394.backend.entity.Survey;


@Transactional
public interface SurveyEntityRepository extends BaseEntityRepository<Survey>  {
    
    @Query(value = "select * from survey sv inner join student_survey studs on sv.id = studs.id_survey  inner join student s on studs.id_student = s.id   where s.id = :UserID group by sv.id ", nativeQuery = true)
    List findAllSurveyRespondbyUser(@Param("UserID") Integer id);



		
}
