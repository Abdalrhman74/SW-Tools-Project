package Phase1;

import java.awt.ItemSelectable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Size;


@Entity
@Stateless

@Table(name="Order")



public class Order {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

	
    private ArrayList<Meal> items;
    
    private LocalDateTime date = LocalDateTime.now();

    
    private double total_price;

    private int completeOrder;
    private int cancledOrder;
    
    
    @Transient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;


    private int fk_runnerId;

    @Transient
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "runner_id")
    private Runner runner;
    
    private int fk_restaurantId;



    private String order_status;

    private boolean isPreparing;
    private boolean isDelivered;
    private boolean isCanceled;
    
    @Transient

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user ;


    public Order() {isPreparing=true;}

    public Order(int id, ArrayList<Meal> items, int fk_runnerId, int fk_restaurantId) {
        this.id = id;
        this.items = items;
        //this.total_price = total_price;
        this.fk_runnerId = fk_runnerId;
        this.setFk_restaurantId(fk_restaurantId);

        isPreparing=true;

    }
  
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // getters and setters
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public ArrayList<Meal> getItems() { return items; }

    public void setItems(ArrayList<Meal> item)
    {

        item.addAll(items);


    }

    public double getTotal_price() { return total_price; }

    public void setTotal_price() 
    { 
    	
    	for(int i = 0 ; i < items.size() ;i++){
    		
    		total_price +=items.get(i).getPrice();
    	}
    }

    public int getFk_runnerId() { return fk_runnerId; }

    public int getFk_restaurantId() {
        return fk_restaurantId;
    }

    public void setFk_restaurantId(int fk_restaurantId) {
        this.fk_restaurantId = fk_restaurantId;
    }

    public String getOrder_status()
    {
        if(isPreparing == true && isDelivered==false && isCanceled==false)
        {

            this.order_status="Preparing";
        }
        else if(isDelivered==true && isCanceled==false)
        {
            completeOrder++;
            this.order_status="Delivered";
        }
        else
        {
            cancledOrder++;
            this.order_status="Canceled";
        }

        return order_status;
    }


    public boolean isCanceled() {


        return isCanceled;

    }

    public void setCanceled(boolean isCanceled) {
        this.isCanceled = isCanceled;
    }

    public boolean isPreparing() {
        return isPreparing;
    }

    public void SetPreparing(boolean isPreparing) {
        this.isPreparing = isPreparing;
    }
    public boolean isDelivered() {
        return isDelivered;
    }

    public void SetDelivered(boolean isDelivered) {
        this.isDelivered = isDelivered;
    }

    public int getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(int completeOrder) {
        this.completeOrder = completeOrder;
    }

    public int getCancledOrder() {
        return cancledOrder;
    }
    public void setCancledOrder(int CancledOrder) {
        this.cancledOrder = CancledOrder;
    }

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
}
