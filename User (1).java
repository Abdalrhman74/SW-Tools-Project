package Phase1;

import java.util.ArrayList;

import javax.ejb.Stateful;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity

@Stateful

@Table(name="User")



public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
    private String name;
    private String role;
    private String Email;
    private String Password;

    
    
    ArrayList<User> users = new ArrayList<>();
    
    
    public User() {}
    

    public User(int id, String name, String role,String Email,String Password) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    // getters and setters
    
    public int getId() { 
    	return id; 
    	
    	}
    
    public void setId(int id) { 
    	this.id = id; 
    	}
    
    public String getName() { 
    	return name; 
    	}
    
    public void setName(String name) {
    	this.name = name; 
    	}
    
    public String getRole() {
    	return role; 
    	}
    
    public void setRole(String role) { 
    	this.role = role; 
    	
    }

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
	
	
	
}
