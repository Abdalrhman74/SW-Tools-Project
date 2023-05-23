package Phase1;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/Customer")

public class CustomerService {
	
	
	@PersistenceContext
    private EntityManager entityManager;
	
    
    ArrayList<Order> Allorders=new ArrayList<>();
    
    
    public List<Runner> FindAllRunner() {
        TypedQuery<Runner> r = entityManager.createQuery("SELECT r FROM Runner r", Runner.class);
        return r.getResultList();
    }
    
    public List<Restaurant> FindAllRestaurant() {
        TypedQuery<Restaurant> res = entityManager.createQuery("SELECT res FROM Restaurant res", Restaurant.class);
        return res.getResultList();
    }
    
    @POST
    @Path("/createOrder")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrder(Order order )
    {
    	List<Runner> r ;
        r = FindAllRunner();
        for(int i = 0 ;i< r.size();i++) 
        {
            Runner runner = r.get(i);
            if(runner.getStatus().equals("available"))
            {
                r.get(i).setOrder(order);
                entityManager.persist(order);
                r.get(i).setStatus("busy");
                entityManager.merge(r);
                return Response.status(Response.Status.OK).entity(runner).type(MediaType.APPLICATION_JSON).build();

            }
            
        }
        
        return Response.status(Response.Status.NOT_FOUND).entity(null).type(MediaType.APPLICATION_JSON).build();
  
     }
    
    @PUT
    @Path("/EditOrder/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response EditOrder(Order order,@PathParam("id") int id) {
    	
        order.setId(id);
        entityManager.merge(order);
        return Response.status(Response.Status.OK).entity(order).type(MediaType.APPLICATION_JSON).build();
    	
    }



    @GET
    @Path("/AllRestaurant")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAllRestaurant()
    {
    	List<Restaurant> res ;
        res = FindAllRestaurant();
        
        
    	
        return Response.status(Response.Status.OK).entity(res).type(MediaType.APPLICATION_JSON).build();

    }
    
    
}