package com.ipnetinstitute.csc394.backend.dao;

import javax.transaction.Transactional;

import com.ipnetinstitute.csc394.backend.entity.Term;

@Transactional
public interface TermEntityRepository extends BaseEntityRepository<Term> {

}
