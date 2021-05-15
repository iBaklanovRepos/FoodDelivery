package ssau.web_app.Food_delivery_app.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "restaurant")
@Entity
public class Restaurant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "res_id_seq")
    @SequenceGenerator(name = "res_id_seq", sequenceName = "restaurant_id_seq", allocationSize = 1)
    private int restaurant_id;

    @Column(nullable = false, length = 40, unique = true)
    private String restaurant_name;

    @Column(nullable = false, length = 100)
    private String restaurant_location;

    @Column(length = 150)
    private String restaurant_description;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Delivery> restaurant_orders;

    public Restaurant() {
    }

    public Restaurant(String restaurant_name, String restaurant_location, String restaurant_description) {
        this.restaurant_name = restaurant_name;
        this.restaurant_location = restaurant_location;
        this.restaurant_description = restaurant_description;
    }

    public Restaurant(String restaurant_name, String restaurant_location) {
        this.restaurant_name = restaurant_name;
        this.restaurant_location = restaurant_location;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public String getRestaurant_location() {
        return restaurant_location;
    }

    public void setRestaurant_location(String restaurant_location) {
        this.restaurant_location = restaurant_location;
    }

    public String getRestaurant_description() {
        return restaurant_description;
    }

    public void setRestaurant_description(String restaurant_description) {
        this.restaurant_description = restaurant_description;
    }
}
