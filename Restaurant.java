package Phase1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;




@Table(name="Restaurant")




@Entity
public class Restaurant {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
    private String name;
    private int ownerId;
    private int menuSize;
    
    @Transient
    @OneToMany(mappedBy="restaurant" ,fetch = FetchType.LAZY)
    private Set<Runner>runner ;

    @Transient

    @OneToMany(mappedBy="restaurant" ,fetch = FetchType.LAZY)
    private Set<Meal> list_of_meals;

    @Transient

    @OneToMany(mappedBy="restaurant" ,fetch = FetchType.LAZY)
    private Set<Order> orders;
    
    //@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    //private ArrayList<Meal> list_of_meals= new ArrayList<>();
//
//    //@OneToMany(mappedBy = "restaurant")
    //private ArrayList<Order> orders = new ArrayList<>();


    public Restaurant() {}

    public Restaurant(int id, String name, int ownerId) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
    }

    // getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getOwnerId() { return ownerId; }
    public void setOwnerId(int ownerId) { this.ownerId = ownerId; }
    public  Set<Meal> getList_of_meals() { return list_of_meals; }
    public void setList_of_meals( Meal list_of_meals) { this.list_of_meals.add(list_of_meals) ; }




    public int getMenuSize() {
        return menuSize;
    }

    public void setMenuSize(int menuSize) {
        this.menuSize = menuSize;
    }
    
    
    public Meal getMealId(int meal_id) {
        Iterator<Meal> namesIterator = this.list_of_meals.iterator();
        while(namesIterator.hasNext()) {
            Meal m = namesIterator.next();
           if(m.getId()==id) {
               return m;
           }
        }
        return null;
    }
    
}
