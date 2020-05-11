package com.ipnetinstitute.csc394.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import antlr.collections.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ipnetinstitute.csc394.backend.entity.Course;


public interface CourseEntityRepository extends JpaRepository<Course, Long> {

    @Query(value = "select c.* from course c inner join teacher t on c.id_teacher = t.id where t.id = :TeacherID", nativeQuery = true)
    List findCousebyTeacher(@Param("TeacherID") Integer id);

    
}
