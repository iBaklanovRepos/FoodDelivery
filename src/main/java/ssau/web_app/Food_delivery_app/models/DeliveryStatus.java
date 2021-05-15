package ssau.web_app.Food_delivery_app.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "deliverystatus")
@Entity
public class DeliveryStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ds_id_seq")
    @SequenceGenerator(name = "ds_id_seq", sequenceName = "deliverystatus_id_seq", allocationSize = 1)
    private int delivery_status_id;

    @Column(length = 30, nullable = false, unique = true)
    private String delivery_status_name;

    @Column(length = 150)
    private String delivery_status_description;

    @OneToMany(mappedBy = "delivery_status", cascade = CascadeType.ALL)
    private List<Delivery> deliveries;

    public DeliveryStatus(String delivery_status_name) {
        this.delivery_status_name = delivery_status_name;
    }

    public DeliveryStatus(String delivery_status_name, String delivery_status_description) {
        this.delivery_status_name = delivery_status_name;
        this.delivery_status_description = delivery_status_description;
    }

    public DeliveryStatus() {
    }

    public String getDelivery_status_name() {
        return delivery_status_name;
    }

    public void setDelivery_status_name(String delivery_status_name) {
        this.delivery_status_name = delivery_status_name;
    }

    public int getDelivery_status_id() {
        return delivery_status_id;
    }

    public void setDelivery_status_id(int delivery_status_id) {
        this.delivery_status_id = delivery_status_id;
    }

    public String getDelivery_status_description() {
        return delivery_status_description;
    }

    public void setDelivery_status_description(String delivery_status_description) {
        this.delivery_status_description = delivery_status_description;
    }
}
