package com.ipnetinstitute.csc394.backend.dao;
import javax.transaction.Transactional;

import com.ipnetinstitute.csc394.backend.entity.Survey;
import com.ipnetinstitute.csc394.backend.entity.Teacher;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//@NoRepositoryBean
@Transactional
public interface TeacherEntityRepository extends BaseEntityRepository<Teacher> {

    @Query(value = "select u.* , t.id as tid, t.matricule, t.create_date_time tcdt," +
            " t.mod_date_time  tmdt from teacher t inner join  " +
            "users u where t.id_user = u.id ", nativeQuery = true)
    List findAllTeacherWithTheirUser();
}
