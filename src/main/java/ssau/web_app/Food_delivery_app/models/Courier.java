package ssau.web_app.Food_delivery_app.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "courier")
@Entity
public class Courier implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cour_id_seq")
    @SequenceGenerator(name = "cour_id_seq", sequenceName = "courier_id_seq", allocationSize = 1)
    private int courier_id;

    @Column(nullable = false, length = 40, unique = true)
    private String courier_name;

    @Column(nullable = false, length = 100)
    private String courier_location;

    @Column(length = 150)
    private String courier_details;

    @OneToMany(mappedBy = "courier", cascade = CascadeType.ALL)
    private List<Delivery> courier_orders;


    public Courier() {
    }

    public Courier(String courier_name, String courier_location) {
        this.courier_name = courier_name;
        this.courier_location = courier_location;
    }

    public Courier(String courier_name, String courier_location, String courier_details) {
        this.courier_name = courier_name;
        this.courier_location = courier_location;
        this.courier_details = courier_details;
    }

    public int getCourier_id() {
        return courier_id;
    }

    public void setCourier_id(int courier_id) {
        this.courier_id = courier_id;
    }

    public String getCourier_name() {
        return courier_name;
    }

    public void setCourier_name(String courier_name) {
        this.courier_name = courier_name;
    }

    public String getCourier_location() {
        return courier_location;
    }

    public void setCourier_location(String courier_location) {
        this.courier_location = courier_location;
    }

    public String getCourier_details() {
        return courier_details;
    }

    public void setCourier_details(String courier_details) {
        this.courier_details = courier_details;
    }
}
