package Phase1;

import javax.ws.rs.core.MediaType;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.management.Query;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.persistence.EntityManager;



@Stateless
@Path("/Al_Akeel")


public class Al_Akeel {

	@PersistenceContext
    private EntityManager entityManager;
	
    private  ArrayList<Restaurant> restaurantService = new ArrayList<>();


    private Order orderService;
    
    ArrayList<User> users = new ArrayList<>();
    
    
    ArrayList<Meal> m1 = new ArrayList<>();
    
    public List<User> FindAllUsers() {
        TypedQuery<User> u = entityManager.createQuery("SELECT u FROM User u", User.class);
        return u.getResultList();
    }
    
    public List<Restaurant> FindAllRestaurant() {
        TypedQuery<Restaurant> res = entityManager.createQuery("SELECT res FROM Restaurant res", Restaurant.class);
        return res.getResultList();
    }
    
   

    // Method to handle user sign up
    @POST
    @Path("/SignUp")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signUp(User user)
    {
        //user.setName(username);
        //user.setPassword(password);
        //user.setEmail(email);
        //user.setRole(Role);
    	
    			users.add(user);
    			entityManager.persist(user);
    	        return Response.status(Response.Status.OK).entity(user).type(MediaType.APPLICATION_JSON).build(); 
    		
    		
    	
    }
    
    @POST
    @Path("/SignUpRunner")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signUpRunner(Runner run)
    {
    	
        //user.setName(username);
        //user.setPassword(password);
        //user.setEmail(email);
        //user.setRole(Role);
    	
        //users.add(run);
        
        entityManager.persist(run);
        return Response.status(Response.Status.OK).entity(run).type(MediaType.APPLICATION_JSON).build(); 
    }
    
    @GET
    @Path("/Login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response login(User user) {
    	
    	List<User> user2 = FindAllUsers();
    	
    	for(int i =0 ;i < user2.size() ; i++) {
    		if(user2.get(i).equals(user)) {
    			return Response.status(Response.Status.OK).entity(user).type(MediaType.APPLICATION_JSON).build(); 
    		}
    	}
    	
    	
        return Response.status(Response.Status.NOT_FOUND).entity(null).type(MediaType.APPLICATION_JSON).build();     	
     
    }
    
    
    @POST
    @Path("/Menu/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response CreateRestaurantMenu(Meal m,@PathParam("id")int id) { //id // menuesize    meal name -- price -- id
        Restaurant restaurant = entityManager.find(Restaurant.class, id);
        // entityManager.persist(restaurant);
        restaurant.setList_of_meals(m);
        entityManager.persist(m);
        return Response.status(Response.Status.OK).entity(restaurant).type(MediaType.APPLICATION_JSON).build();  
    }
    
    
    @POST
    @Path("/CreateRestaurant")
    public Response createrestor(Restaurant restaurant) {
    	restaurantService.add(restaurant);
    	entityManager.persist(restaurant);
        return Response.status(Response.Status.OK).entity(restaurant).type(MediaType.APPLICATION_JSON).build();
    }
    
 
    @POST
    @Path("/AddItem/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response AddItem(@PathParam("id") int id , Meal meal)
    {
         Restaurant r = entityManager.find(Restaurant.class, id);
         //m1.add(meal);
         r.setList_of_meals(meal);
         //entityManager.persist(m);
        return Response.status(Response.Status.OK).entity(meal).type(MediaType.APPLICATION_JSON).build();
    }
    
    
    
    @DELETE
    @Path("/RemoveItem/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response RemoveItem(@PathParam("id") int id){
    	
    	Restaurant re = entityManager.find(Restaurant.class, id);
    	entityManager.remove(re.getMealId(id));
        return Response.status(Response.Status.OK).entity(re).type(MediaType.APPLICATION_JSON).build();
       
    }

//
    @PUT
    @Path("/UpdateItem/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response UpdateItem(@PathParam("id") int id,Meal m) {
    	

    	Restaurant re = entityManager.find(Restaurant.class, id);
    	re.getMealId(id);
    	re.getList_of_meals().add(m) ;
    	entityManager.merge(m);
        return Response.status(Response.Status.OK).entity(m).type(MediaType.APPLICATION_JSON).build();

    	
    }


    
    @GET
    @Path("/RestaurantDetails/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetRestaurantDetails(@PathParam("id") int id){
    	
    	Restaurant restaurant= entityManager.find(Restaurant.class , id);
        return Response.status(Response.Status.OK).entity(restaurant).type(MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Path("/RestaurantDetails_2/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetRestaurantDetails_2(@PathParam("id") int id)
    {
    	List<Restaurant> res ;
        res = FindAllRestaurant();
        
        
        for(int i =0 ; i < res.size();i++) {
        	if(res.get(i).getId()==id  ) {
        		
                return Response.status(Response.Status.OK).entity(res).type(MediaType.APPLICATION_JSON).build();

        	}
        }
        
        
        
    	
        return Response.status(Response.Status.NOT_FOUND).entity(null).type(MediaType.APPLICATION_JSON).build();

    }
    
    

}
