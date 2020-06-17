package com.sephora.repository;

import com.sephora.model.Department;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DepartmentDaoTest {
    private DepartmentDao departmentDao;

    @Before
    public void setUp(){
        departmentDao = new DepartmentDaoImpl();
    }

    @Test
    public void getDepartmentsTest(){
        List<Department> departments = departmentDao.getDepartments();
        int expectedNumOfDept = 1;

        Assert.assertEquals(expectedNumOfDept, departments.size());
    }
}












