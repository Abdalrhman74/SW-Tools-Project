package Phase1;

import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Meal")



public class Meal {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String name;
    private double price;
    
    @Transient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    private int fk_restaurantId;


    public Meal() {}

    public Meal(int id, String name, double price, int fk_restaurantId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.fk_restaurantId = fk_restaurantId;
    }

    // getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price){ this.price = price; }
    public int getFk_restaurantId() { return fk_restaurantId; }
    public void setFk_restaurantId(int fk_restaurantId) { this.fk_restaurantId = fk_restaurantId; }
}
