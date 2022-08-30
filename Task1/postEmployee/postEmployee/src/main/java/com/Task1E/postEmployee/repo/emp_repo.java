package com.Task1E.postEmployee.repo;

import com.Task1E.postEmployee.model.emp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface emp_repo extends JpaRepository<emp,Long> {
}
