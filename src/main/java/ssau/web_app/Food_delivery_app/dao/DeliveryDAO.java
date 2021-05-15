package ssau.web_app.Food_delivery_app.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ssau.web_app.Food_delivery_app.models.Delivery;

import java.util.List;

public class DeliveryDAO {
    public static int insertDelivery(Delivery delivery) {
        try (Session session = SessionOpener.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            int newId = (int) session.save(delivery);
            transaction.commit();
            return newId;
        }
    }

    public static void updateDelivery(Delivery delivery) {
        try (Session session = SessionOpener.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(delivery);
            transaction.commit();
        }
    }

    public static void deleteDelivery(Delivery delivery) {
        try (Session session = SessionOpener.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(delivery);
            transaction.commit();
        }
    }

    public static Delivery selectDeliveryById(int id) {
        try (Session session = SessionOpener.getSessionFactory().openSession()) {
            return session.get(Delivery.class, id);
        }
    }

    public static List<Delivery> selectDeliveries() {
        try (Session session = SessionOpener.getSessionFactory().openSession()) {
            return session.createQuery("FROM Delivery", Delivery.class).list();
        }
    }
}
