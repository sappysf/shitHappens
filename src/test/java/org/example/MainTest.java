package org.example;

import lombok.Cleanup;
import org.example.entity.Company;
import org.example.entity.Role;
import org.example.entity.User;
import org.example.util.HibernateUtil;
import org.junit.jupiter.api.Test;


class MainTest {

    @Test
    void check() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        var facebook = Company.builder()
                .name("Facebook")
                .build();
        var user = User.builder()
                .role(Role.ADMIN)
                .build();
        facebook.addUser(user);
        session.save(facebook);
        session.getTransaction().commit();
    }

    @Test
    void lazy() {
        Company company = null;
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();
            company = session.get(Company.class, 1L);
            session.getTransaction().commit();
        }
        var users = company.getUsers();
        users.forEach(System.out::println);

    }
}