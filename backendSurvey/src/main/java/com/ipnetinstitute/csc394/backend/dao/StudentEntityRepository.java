package com.ipnetinstitute.csc394.backend.dao;
import javax.transaction.Transactional;
import com.ipnetinstitute.csc394.backend.entity.Student;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//@NoRepositoryBean
@Transactional
public interface StudentEntityRepository extends BaseEntityRepository<Student> {

    @Query(value = "select u.* , s.id as sid, s.matricule, s.create_date_time scdt, " +
            "s.mod_date_time  smdt from student s inner join  " +
            "users u where s.id_user = u.id ", nativeQuery = true)
    List findAllStudentWithTheirUser();

}
