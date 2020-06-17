package com.sephora.jdbc;

import com.sephora.model.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentJDBCDao {
    static final String dbURL = System.getProperty("database.url");
    static final String username = System.getProperty("database.user");
    static final String password = System.getProperty("database.password");

    public List<Department> getDepartments(){
        List<Department> departments = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //STEP 2: Open a connection
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5431/dealer", "admin", "password");
            //STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM departments";
            rs = stmt.executeQuery(sql);
            //STEP 4: Extract data from result set
            while(rs.next()) {
                //Retrieve by column name
                Long id  = rs.getLong("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String location = rs.getString("location");
                //Fill the object
                Department department = new Department();
                department.setId(id);
                department.setName(name);
                department.setDescription(description);
                department.setLocation(location);
                departments.add(department);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            //STEP 6: finally block used to close resources
            try {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            }
            catch(SQLException se) {
                se.printStackTrace();
            }
        }
        return departments;
    }

    public static void main(String[] args){
        DepartmentJDBCDao departmentJDBCDao = new DepartmentJDBCDao();
        System.out.println(departmentJDBCDao.getDepartments());
    }




}
