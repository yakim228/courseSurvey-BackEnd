package com.ipnetinstitute.csc394.backend.dao;

import javax.transaction.Transactional;

import com.ipnetinstitute.csc394.backend.entity.StudentSurvey;

@Transactional
public interface StudentSurveyEntityRepository  extends BaseEntityRepository<StudentSurvey>{

}
