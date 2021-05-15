package ssau.web_app.Food_delivery_app.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ssau.web_app.Food_delivery_app.models.*;


public class SessionOpener {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            try{

                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Customer.class);
                configuration.addAnnotatedClass(Delivery.class);
                configuration.addAnnotatedClass(Courier.class);
                configuration.addAnnotatedClass(DeliveryStatus.class);
                configuration.addAnnotatedClass(Restaurant.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            }catch (Exception e) {
                System.out.println("Couldn't get session factory");
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static void shutdown(){
        getSessionFactory().close();
    }
}
