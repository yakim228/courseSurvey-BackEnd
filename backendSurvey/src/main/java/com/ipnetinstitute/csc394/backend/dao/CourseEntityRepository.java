package com.ipnetinstitute.csc394.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ipnetinstitute.csc394.backend.entity.Course;

public interface CourseEntityRepository extends JpaRepository<Course, Long> {

}
