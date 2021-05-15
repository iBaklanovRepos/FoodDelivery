package ssau.web_app.Food_delivery_app.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ssau.web_app.Food_delivery_app.models.Courier;

import java.util.List;

public class CourierDAO {
    public static int insertCourier(Courier courier){
        try (Session session = SessionOpener.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            int newId = (int) session.save(courier);
            transaction.commit();
            return newId;
        }
    }

    public static void updateCourier(Courier courier){
        try (Session session = SessionOpener.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(courier);
            transaction.commit();
        }
    }

    public static void deleteCourier(Courier courier){
        try (Session session = SessionOpener.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(courier);
            transaction.commit();
        }
    }

    public static Courier selectCourierById(int id){
        try (Session session = SessionOpener.getSessionFactory().openSession()){
            return session.get(Courier.class, id);
        }
    }

    public static Courier selectCourierByName(String name){
        try(Session session = SessionOpener.getSessionFactory().openSession()){
            Query query = session.createQuery("from Courier where courier_name =: name").setParameter("name", name);
            return (Courier) query.list().get(0);
        }
    }

    public static List<Courier> selectCouriers(){
        try (Session session = SessionOpener.getSessionFactory().openSession()){
            return session.createQuery("FROM Courier", Courier.class).list();
        }
    }
}
