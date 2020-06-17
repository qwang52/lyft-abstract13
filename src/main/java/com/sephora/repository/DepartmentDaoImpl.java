package com.sephora.repository;

import com.sephora.model.Department;
import com.sephora.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao {
    private  Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public Department save(Department department) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            transaction = session.beginTransaction();
            session.save(department);
            transaction.commit();
            session.close();
            return department;
        }
        catch(Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error("failure to insert record",e);
            session.close();
            return null;
        }

    }

    @Override
    public List<Department> getDepartments() {
        String hql = "SELECT d FROM Department d";
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session s = sessionFactory.openSession();
        List<Department> result = new ArrayList<>();
       // try{
            Query query = s.createQuery(hql);
            result = query.list();
            s.close();
        //}catch(HibernateException e){
        //    logger.error("session close exception try again...", e);
        //    s.close();
       // }
        return result;
    }

    @Override
    public Department getBy(Long id) {
        return null;
    }

    @Override
    public boolean delete(Department dep) {
        String hql = "DELETE Department as dep where dep.id = :Id";
        int deletedCount = 0;
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            transaction = session.beginTransaction();
            Query<Department> query = session.createQuery(hql);
            query.setParameter("Id",dep.getId());
            deletedCount = query.executeUpdate();
            transaction.commit();
            session.close();
            return deletedCount >= 1 ? true : false;
        }
        catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            session.close();
            session.close();
            logger.error("unable to delete record",e);
        }
        return false;
    }
}







