package ssau.web_app.Food_delivery_app.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ssau.web_app.Food_delivery_app.models.Courier;
import ssau.web_app.Food_delivery_app.models.Restaurant;

import java.util.List;

public class RestaurantDAO {


    public static int insertRestaurant(Restaurant restaurant){
        try (Session session = SessionOpener.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            int newId = (int) session.save(restaurant);
            transaction.commit();
            return newId;
        }
    }

    public static void updateRestaurant(Restaurant restaurant){
        try (Session session = SessionOpener.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(restaurant);
            transaction.commit();
        }
    }

    public static void deleteRestaurant(Restaurant restaurant){
        try (Session session = SessionOpener.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(restaurant);
            transaction.commit();
        }
    }

    public static Restaurant selectRestaurantById(int id){
        try (Session session = SessionOpener.getSessionFactory().openSession()){
            return session.get(Restaurant.class, id);
        }
    }

    public static Restaurant selectRestaurantByName(String name){
        try(Session session = SessionOpener.getSessionFactory().openSession()){
            Query query = session.createQuery("from Restaurant where restaurant_name =: name").setParameter("name", name);
            return (Restaurant) query.list().get(0);
        }
    }

    public static List<Restaurant> selectRestaurants(){
        try (Session session = SessionOpener.getSessionFactory().openSession()){
            return session.createQuery("FROM Restaurant", Restaurant.class).list();
        }
    }
}
