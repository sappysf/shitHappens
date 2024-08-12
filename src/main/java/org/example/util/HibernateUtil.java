package org.example.util;

import lombok.experimental.UtilityClass;
import org.example.entity.Company;
import org.example.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {

    public static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Company.class);
        configuration.configure();
        return configuration.buildSessionFactory();
    }
}
