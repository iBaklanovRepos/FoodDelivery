package ssau.web_app.Food_delivery_app.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "customer")
@Entity
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cus_id_seq")
    @SequenceGenerator(name = "cus_id_seq", sequenceName = "customer_id_seq", allocationSize = 1)
    private int customer_id;

    @Column(nullable = false, length = 40, unique = true)
    private String customer_name;

    @Column(length = 100)
    private String customer_address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Delivery> customer_orders;

    public Customer() {
    }

    public Customer(String customer_name, String customer_address) {
        this.customer_name = customer_name;
        this.customer_address = customer_address;
    }


    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }
}
