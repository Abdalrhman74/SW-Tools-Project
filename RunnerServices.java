package Phase1;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import javax.persistence.PersistenceContext;

import javax.persistence.EntityManager;



@Path("/Runner")
public class RunnerServices {
	

	@PersistenceContext
    private EntityManager entityManager;
	
	
	 public List<User> findAll() {
	        TypedQuery<User> u = entityManager.createQuery("SELECT u FROM User u", User.class);
	        return u.getResultList();
	    }
	 public List<Runner> FindAllRunner() {
	        TypedQuery<Runner> run = entityManager.createQuery("SELECT r FROM Runner r", Runner.class);
	        return run.getResultList();
	    }
	    
	    @GET
	    @Path("/markOrderDelivered/{id}")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response markOrderDelivered(@PathParam("id")int id)
	    {
	    	Runner run = entityManager.find(Runner.class,id);
	    	
	        run.setTripnum();     
	        
	        run.setDeliverdorder(true);
	     
	        run.setStatus("available");
	        
	        entityManager.merge(run);

	    
	        return Response.status(Response.Status.OK).entity(run.getOrder()).type(MediaType.APPLICATION_JSON).build();
	    }
	    
	    
	    @GET
	    @Path("/NumberOfTrips/{id}")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response GetNumberOfTrips(@PathParam("id")int id) 
	    {
	    	List<Runner> r ;
	        r = FindAllRunner();
	        for(int i = 0 ; i < r.size() ; i++ ) 
	        {
	        	if(r.get(i).getId()==id)
	        		return Response.status(Response.Status.OK).entity(r.get(i).getTripnum()).type(MediaType.APPLICATION_JSON).build();
	        		
	        }
	    	
	        return Response.status(Response.Status.NOT_FOUND).entity(null).type(MediaType.APPLICATION_JSON).build();
	    	
	    }


}
