package com.ipnetinstitute.csc394.backend.dao;

import javax.transaction.Transactional;

import com.ipnetinstitute.csc394.backend.entity.CatSurvey;

@Transactional
public interface CategorySurveyEntityRepository  extends BaseEntityRepository<CatSurvey>{

}
