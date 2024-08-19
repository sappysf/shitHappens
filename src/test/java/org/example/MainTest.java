package org.example;

import lombok.Cleanup;
import org.example.entity.*;
import org.example.util.HibernateUtil;
import org.hibernate.LockMode;
import org.hibernate.graph.GraphSemantic;
import org.hibernate.graph.RootGraph;
import org.junit.jupiter.api.Test;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.util.Map;


class MainTest {

    @Test
    void check() {

    }

    @Transactional
    @Test
    void nProblemsTest() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        @Cleanup var session1 = sessionFactory.openSession();
        session.beginTransaction();
        session1.beginTransaction();
        var payment = session.find(Payment.class, 2L);
        payment.setAmount(payment.getAmount() + 20);
        var payment1 = session1.find(Payment.class, 2L);
        payment1.setAmount(payment1.getAmount() + 20);
        session.getTransaction().commit();
        session1.getTransaction().commit();
    }

}