package com.ipnetinstitute.csc394.backend.dao;

import java.util.List;

import javax.transaction.Transactional;

import com.ipnetinstitute.csc394.backend.entity.StudentSurvey;
import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


@Transactional
public interface StudentSurveyEntityRepository  extends BaseEntityRepository<StudentSurvey>{

    @Query(value = "select ss.* from student_survey ss " + 
     " inner join ( select sy.* from survey sy inner join course c " +
      " on sy.id_course = c.id WHERE c.id : CourseID) " +
       "as sy on ss.id_survey = sy.id ", nativeQuery = true)
    List<StudentSurvey> findStudentSurveyByCourse(@Param("CourseID") Integer CourseID);

}
