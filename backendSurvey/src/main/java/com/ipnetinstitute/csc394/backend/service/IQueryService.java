package com.ipnetinstitute.csc394.backend.service;

import java.util.List;

import com.ipnetinstitute.csc394.backend.entity.Question;

public interface IQueryService{
    List<Question> JPQLQueryforQuestion();
}