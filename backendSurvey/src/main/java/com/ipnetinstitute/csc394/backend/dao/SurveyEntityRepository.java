package com.ipnetinstitute.csc394.backend.dao;

import java.util.List;

import javax.transaction.Transactional;

import com.ipnetinstitute.csc394.backend.entity.Survey;
@Transactional
public interface SurveyEntityRepository extends BaseEntityRepository<Survey>  {
	

		
}
