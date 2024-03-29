import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    public LocalTime currentTime=LocalTime.now();
    private List<Item> menu = new ArrayList<Item>();
    public int orderTotal=0;

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        int value1= getCurrentTime().compareTo(openingTime);
        int value2= getCurrentTime().compareTo(closingTime);
        if(value1>0&&value2<0){return true;}
        else{return false;}
    }

    public LocalTime getCurrentTime(){ return  currentTime; }

    public void setCurrentTime(LocalTime currentTime) {
        this.currentTime = currentTime;
    }

    public List<Item> getMenu() {
        return Collections.unmodifiableList(menu);
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n" +"Location:"+ location + "\n" +"Opening time:"
                + openingTime +"\n" +"Closing time:"+ closingTime +"\n" +"Menu:"+"\n"+getMenu()); }

    public String getName() {
        return name;
    }

    public int addToCart(String... itemName){
        for (String s : itemName) {
            Item newCartItem = findItemByName(s);
            orderTotal = orderTotal + newCartItem.getPrice();
        }
        return orderTotal;
    }

    public int removeFromCart(String... itemName){
        for (String s : itemName) {
            Item newCartItem = findItemByName(s);
            orderTotal = orderTotal - newCartItem.getPrice();
        }
        return orderTotal;
    }

}
