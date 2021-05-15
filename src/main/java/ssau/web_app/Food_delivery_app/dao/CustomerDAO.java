package ssau.web_app.Food_delivery_app.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ssau.web_app.Food_delivery_app.models.Courier;
import ssau.web_app.Food_delivery_app.models.Customer;

import java.util.List;

public class CustomerDAO {

    public static int insertCustomer(Customer customer){
        try (Session session = SessionOpener.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            int newId = (int) session.save(customer);
            transaction.commit();
            return newId;
        }
    }

    public static void updateCustomer(Customer customer){
        try (Session session = SessionOpener.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(customer);
            transaction.commit();
        }
    }

    public static void deleteCustomer(Customer customer){
        try (Session session = SessionOpener.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(customer);
            transaction.commit();
        }
    }

    public static Customer selectCustomerById(int id){
        try (Session session = SessionOpener.getSessionFactory().openSession()){
            return session.get(Customer.class, id);
        }
    }

    public static Customer selectCustomerByName(String name){
        try(Session session = SessionOpener.getSessionFactory().openSession()){
            Query query = session.createQuery("from Customer where customer_name =: name").setParameter("name", name);
            return (Customer) query.list().get(0);
        }
    }

    public static List<Customer> selectCustomers(){
        try (Session session = SessionOpener.getSessionFactory().openSession()){
            return session.createQuery("FROM Customer", Customer.class).list();
        }
    }
}
