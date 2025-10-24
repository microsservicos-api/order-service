package store.order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "order")
@Setter @Accessors(chain = true, fluent = true)
@NoArgsConstructor @AllArgsConstructor
public class OrderModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "sha256")
    private String sha256;

    public OrderModel(Order a) {
        this.id = a.id();
        this.name = a.name();
        this.email = a.email();
        this.sha256 = a.sha256();
    }

    public Order to() {
        return Order.builder()
            .id(this.id)
            .name(this.name)
            .email(this.email)
            .sha256(this.sha256)
            .build();
    }
    
}
