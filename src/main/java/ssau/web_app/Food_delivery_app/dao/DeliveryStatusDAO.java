package ssau.web_app.Food_delivery_app.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ssau.web_app.Food_delivery_app.models.Courier;
import ssau.web_app.Food_delivery_app.models.DeliveryStatus;

import java.util.List;

public class DeliveryStatusDAO {
    public static int insertDeliveryStatus(DeliveryStatus deliveryStatus){
        try (Session session = SessionOpener.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            int newId = (int) session.save(deliveryStatus);
            transaction.commit();
            return newId;
        }
    }

    public static void updateDeliveryStatus(DeliveryStatus deliveryStatus){
        try (Session session = SessionOpener.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(deliveryStatus);
            transaction.commit();
        }
    }

    public static void deleteDeliveryStatus(DeliveryStatus deliveryStatus){
        try (Session session = SessionOpener.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(deliveryStatus);
            transaction.commit();
        }
    }

    public static DeliveryStatus selectDeliveryStatusById(int id){
        try (Session session = SessionOpener.getSessionFactory().openSession()){
            return session.get(DeliveryStatus.class, id);
        }
    }

    public static DeliveryStatus selectDeliveryStatusByName(String name){
        try(Session session = SessionOpener.getSessionFactory().openSession()){
            Query query = session.createQuery("from DeliveryStatus where delivery_status_name =: name").setParameter("name", name);
            return (DeliveryStatus) query.list().get(0);
        }
    }

    public static List<DeliveryStatus> selectDeliveryStatuses(){
        try (Session session = SessionOpener.getSessionFactory().openSession()){
            return session.createQuery("FROM DeliveryStatus", DeliveryStatus.class).list();
        }
    }
}
