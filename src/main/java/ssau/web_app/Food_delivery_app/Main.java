package ssau.web_app.Food_delivery_app;

import ssau.web_app.Food_delivery_app.dao.*;
import ssau.web_app.Food_delivery_app.models.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//        CourierDAO.insertCourier(new Courier("ivan","home"));
//        System.out.println(CourierDAO.selectCourierByName("ivan").getCourier_location());
        UtilDAO.fillDatabase();
//        Customer customer = CustomerDAO.selectCustomerById(5);
//        Restaurant restaurant = RestaurantDAO.selectRestaurantById(4);
//        Courier courier = CourierDAO.selectCourierById(2);
//        DeliveryStatus status = DeliveryStatusDAO.selectDeliveryStatusById(3);
//        String details = "test";
//        int id = DeliveryDAO.insertDelivery(new Delivery(customer, courier, status, restaurant, details));
//        DeliveryDAO.deleteDelivery(DeliveryDAO.selectDeliveryById(1));
//        List<Delivery> deliveries = DeliveryDAO.selectDeliveries();
//        for(Delivery delivery : deliveries){
//            System.out.println(delivery.getCourier().getCourier_name());
//        }
    }
}
