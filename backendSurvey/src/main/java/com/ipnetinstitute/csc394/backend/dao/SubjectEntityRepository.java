package com.ipnetinstitute.csc394.backend.dao;

import javax.transaction.Transactional;

import com.ipnetinstitute.csc394.backend.entity.Subject;

@Transactional
public interface SubjectEntityRepository  extends BaseEntityRepository<Subject>{

}
