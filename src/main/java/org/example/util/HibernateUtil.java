package org.example.util;

import lombok.experimental.UtilityClass;
import org.example.entity.Chat;
import org.example.entity.Company;
import org.example.entity.User;
import org.example.entity.UserChat;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {

    public static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure();
        return configuration.buildSessionFactory();
    }
}
