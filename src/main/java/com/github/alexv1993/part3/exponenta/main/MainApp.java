package com.github.alexv1993.part3.exponenta.main;

import com.github.alexv1993.part3.exponenta.model.Exponents;
import com.github.alexv1993.part3.exponenta.model.ShowInterface;
import com.github.alexv1993.part3.exponenta.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Alex on 14.10.2017.
 */
public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        Exponents exponents = (Exponents) context.getBean("exponents");
        ShowInterface showInterface = (ShowInterface) context.getBean("showInterface");

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();

            //start transaction
            session.beginTransaction();
            //Save the Model object
            session.save(exponents);
            //Commit transaction
            session.getTransaction().commit();
            System.out.println("Exponents ID=" + exponents.getID());
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            //terminate session factory, otherwise program won't end
            HibernateUtil.getSessionFactory().close();
        }


        showInterface.showValue(exponents.getExpFloat());
        showInterface.showValue(exponents.getExpDouble());
        showInterface.showValue(exponents.getExpDouble2());


    }
}
