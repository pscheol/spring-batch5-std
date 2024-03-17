package com.clairedelune.batch5.springbatch5.domain.repository;

import com.clairedelune.batch5.springbatch5.domain.entity.TbStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbStudentRepository extends JpaRepository<TbStudent, Long> {
}
