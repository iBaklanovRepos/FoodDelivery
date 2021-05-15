package ssau.web_app.Food_delivery_app.dao;


import ssau.web_app.Food_delivery_app.models.Courier;
import ssau.web_app.Food_delivery_app.models.Customer;
import ssau.web_app.Food_delivery_app.models.DeliveryStatus;
import ssau.web_app.Food_delivery_app.models.Restaurant;

public class UtilDAO {
    public static void fillDatabase(){
        CourierDAO.insertCourier(new Courier("Winford Gearhart", "789  Wescam Court"));
        CourierDAO.insertCourier(new Courier("Loren J Thomas", "2610  Fire Access Road", "On vacation"));
        CourierDAO.insertCourier(new Courier("Calvin P Davis", "3034  Small Street", "Courier of the month"));
        CourierDAO.insertCourier(new Courier("Isaiah D Peer", "52  Locust Court"));
        CourierDAO.insertCourier(new Courier("Edmundo H Dominique", "299  Nickel Road"));
        CourierDAO.insertCourier(new Courier("Karen G Frederick", "4694  Pickens Way"));
        CourierDAO.insertCourier(new Courier("Linda M Brown", "2252  Wilmar Farm Road"));
        CourierDAO.insertCourier(new Courier("Matthew C Williams", "1665  Romano Street"));

        DeliveryStatusDAO.insertDeliveryStatus(new DeliveryStatus("Payment expected"));
        DeliveryStatusDAO.insertDeliveryStatus(new DeliveryStatus("Forming"));
        DeliveryStatusDAO.insertDeliveryStatus(new DeliveryStatus("On the way"));
        DeliveryStatusDAO.insertDeliveryStatus(new DeliveryStatus("Delivered"));
        DeliveryStatusDAO.insertDeliveryStatus(new DeliveryStatus("Cancelled"));

        RestaurantDAO.insertRestaurant(new Restaurant("Swift Flash Cafe","4622  Williams Lane", "Chinese food"));
        RestaurantDAO.insertRestaurant(new Restaurant("Royal Loon Inn","226  Conaway Street", "Meat restaurant"));
        RestaurantDAO.insertRestaurant(new Restaurant("Neon Horizon Farmhouse","2129  Maple Court"));
        RestaurantDAO.insertRestaurant(new Restaurant(" Amore Supper Club","2974  Ben Street", "Cheese and wine"));
        RestaurantDAO.insertRestaurant(new Restaurant("Proud Escape Supper Club","1412  Sherwood Circle"));
        RestaurantDAO.insertRestaurant(new Restaurant("The Avenue Inn","3793  Lynn Street", "Best street food"));
        RestaurantDAO.insertRestaurant(new Restaurant("Gilded Wagon Restaurant","305  Aviation Way", "Italian cuisine"));
        RestaurantDAO.insertRestaurant(new Restaurant("Citron Roux Provisions","4578  Broad Street"));

        CustomerDAO.insertCustomer(new Customer("Paul Essary", "Bryan Street"));
        CustomerDAO.insertCustomer(new Customer("Rosina Robinett", "Whiteman Street"));
        CustomerDAO.insertCustomer(new Customer("Edward Groff", "Buck Drive"));
        CustomerDAO.insertCustomer(new Customer("Gregory Robinson", "Peck Court"));
        CustomerDAO.insertCustomer(new Customer("Brian Tousignant", "Fraggle Drive"));
        CustomerDAO.insertCustomer(new Customer("Alice Evans", "Roane Avenue"));
        CustomerDAO.insertCustomer(new Customer("James Bensen", "Duffy Street"));
        CustomerDAO.insertCustomer(new Customer("Tammy Tilley", "Pooh Bear Lane"));
        CustomerDAO.insertCustomer(new Customer("Talitha Tyler", "Charmaine Lane"));
        CustomerDAO.insertCustomer(new Customer("Rebecca Ulrich", "Nutter Street"));
        CustomerDAO.insertCustomer(new Customer("Ruth Preston", "Dovetail Estates"));
        CustomerDAO.insertCustomer(new Customer("Michael Anderson", "Brookside Drive"));


    }
}
