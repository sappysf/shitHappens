package org.example;

import lombok.Cleanup;
import org.example.entity.Payment;
import org.example.entity.User;
import org.example.util.HibernateUtil;

import java.util.List;



public class Main {
    public static void main(String[] args) {
        System.out.println(findAll());
    }

    public static List<User> findAll() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        var list = session.createQuery("select u from User u", User.class).list();
        session.getTransaction().commit();
        return list;
    }

    public static List<User> findAllByFirstName(String userName) {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        var resultList = session.createQuery("select u from User u where u.account.username like :userName",
                User.class).setParameter("userName", userName).getResultList();
        session.getTransaction().commit();
        return resultList;
    }

    public static List<User> findLimitedUsersOrderedByBirthdate(int limit) {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        var resultList = session.createQuery("select u from User u order by u.birthDate desc",
                User.class).setMaxResults(limit).getResultList();
        session.getTransaction().commit();
        return resultList;
    }

    public static List<User> findAllByCompanyName(String companyName) {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        var resultList = session.createQuery("select u from User u join u.company c where c.name like :name",
                User.class).setParameter("name", companyName).getResultList();
        session.getTransaction().commit();
        return resultList;
    }

    public static List<Payment> findAllPaymentsByCompanyName(String companyName) {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        var name = session.createQuery("select p from Payment p join fetch p.user u" +
                                       " join fetch u.company c" +
                                       " where c.name like :name" +
                                       " order by u.name,p.amount", Payment.class).setParameter("name", companyName).getResultList();
        session.getTransaction().commit();
        return name;
    }

    public static Double findAvg(String firstName) {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        var v = session.createQuery("select avg(p.amount) from Payment p join p.user u where u.name like :name",
                Double.class).setParameter("name", firstName).uniqueResult();
        session.getTransaction().commit();
        return v;
    }

}
