package com.ipnetinstitute.csc394.backend.dao;

import javax.transaction.Transactional;

import com.ipnetinstitute.csc394.backend.entity.CatSurveyQuestion;


@Transactional
public interface CategorieSurveyQuestionEntityRepository  extends BaseEntityRepository<CatSurveyQuestion>{

}
