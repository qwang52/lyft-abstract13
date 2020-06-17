package com.sephora.repository;


import com.sephora.model.Department;

import java.util.List;

public interface DepartmentDao {
    Department save(Department department);
    List<Department> getDepartments();
    Department getBy(Long id);
    boolean delete(Department dep);
}

