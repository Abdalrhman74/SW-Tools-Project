package Phase1;

import javax.ws.rs.core.MediaType;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.management.Query;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity

@Table(name="runner")

@Stateless

public class Runner {
	
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
    private  Boolean isDeliverdorder;
    
    
    private String name;
    private String RunnerEmail;
    private String RunnerPassword;
    private String status="available";
    private double delivery_fees;
    private int tripnum = 0;
    
    @Transient

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="restaurant_id")
    private Restaurant restaurant;
    
    @Transient

    @OneToOne(mappedBy ="runner")
    private Order orders ;
    
    


    public Runner() {  
    	this.isDeliverdorder=false;
    	//this.status ="available";
    }

//    public Runner(int id, String name, String status, double delivery_fees) 
//    {
//        this.id = id;
//        this.name = name;
//        this.status = status;
//        this.delivery_fees = delivery_fees;
//        this.isDeliverdorder=false;
//    }

    // getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public double getDelivery_fees() { return delivery_fees; }
    public void setDelivery_fees(double delivery_fees) { this.delivery_fees = delivery_fees; }
    
    
   

    public Boolean getDeliverdorder()
    {
        return isDeliverdorder;
    }

    public void setDeliverdorder(Boolean deliverdorder) {
        isDeliverdorder = deliverdorder;
    }

    public int getTripnum()
    {
        return tripnum;
    }
    
    public void setTripnum() {
        this.tripnum += 1;
    }
    
    public void setOrder(Order order) {
    	this.orders = order ;
    }

    public Order getOrder()
    {
        return orders;
    }

	public String getRunnerEmail() {
		return RunnerEmail;
	}

	public void setRunnerEmail(String runnerEmail) {
		RunnerEmail = runnerEmail;
	}

	public String getRunnerPassword() {
		return RunnerPassword;
	}

	public void setRunnerPassword(String runnerPassword) {
		RunnerPassword = runnerPassword;
	}
}