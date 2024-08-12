package org.example;

import org.example.entity.Company;
import org.example.entity.User;
import org.example.util.HibernateUtil;
import org.hibernate.cfg.Configuration;


public class Main {
    public static void main(String[] args) {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();
            var user1 = session.get(User.class, 8L);
            session.getTransaction().commit();
        }

    }
}
