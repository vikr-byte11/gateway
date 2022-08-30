package com.Task1E.getEmployee.repo;

import com.Task1E.getEmployee.model.emp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface emp_repo extends JpaRepository<emp,Long> {
}
