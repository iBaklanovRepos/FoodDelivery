package ssau.web_app.Food_delivery_app.models;

import javax.persistence.*;

@Table(name = "delivery")
@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "del_id_seq")
    @SequenceGenerator(name = "del_id_seq", sequenceName = "delivery_id_seq", allocationSize = 1)
    private int delivery_id;

    @Column(length = 150)
    private String delivery_details;

    @ManyToOne(targetEntity = Customer.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(targetEntity = Courier.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "courier_id", nullable = false)
    private Courier courier;

    @ManyToOne(targetEntity = DeliveryStatus.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "delivery_status_id", nullable = false)
    private DeliveryStatus delivery_status;

    @ManyToOne(targetEntity = Restaurant.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    public Delivery(){}

    public Delivery(String delivery_details) {
        this.delivery_details = delivery_details;
    }

    public Delivery(Customer customer, Courier courier, DeliveryStatus delivery_status, Restaurant restaurant, String delivery_details) {
        this.delivery_details = delivery_details;
        this.customer = customer;
        this.courier = courier;
        this.delivery_status = delivery_status;
        this.restaurant = restaurant;
    }

    public Delivery(Customer customer, Courier courier, DeliveryStatus delivery_status, Restaurant restaurant) {
        this.customer = customer;
        this.courier = courier;
        this.delivery_status = delivery_status;
        this.restaurant = restaurant;
    }

    public int getDelivery_id() {
        return delivery_id;
    }

    public void setDelivery_id(int delivery_id) {
        this.delivery_id = delivery_id;
    }

    public String getDelivery_details() {
        return delivery_details;
    }

    public void setDelivery_details(String delivery_details) {
        this.delivery_details = delivery_details;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    public DeliveryStatus getDelivery_status() {
        return delivery_status;
    }

    public void setDelivery_status(DeliveryStatus delivery_status) {
        this.delivery_status = delivery_status;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
