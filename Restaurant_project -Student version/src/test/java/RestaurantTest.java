import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;

    @BeforeEach
    public void setup(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);

        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        restaurant.addToMenu("Sizzling brownie", 319);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        LocalTime timeBetweenOpeningAndClosingTime=LocalTime.parse("15:30:00");
        restaurant.setCurrentTime(timeBetweenOpeningAndClosingTime);
        boolean status=restaurant.isRestaurantOpen();

        assertTrue(status);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        LocalTime timeIsOutsideOpeningAndClosingTime=LocalTime.parse("23:30:00");
        restaurant.setCurrentTime(timeIsOutsideOpeningAndClosingTime);
        boolean status=restaurant.isRestaurantOpen();

        assertFalse(status);
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());

    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());

    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>CART<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_items_to_cart_should_return_orderTotal_as_addition_of_added_items(){
        restaurant.addToCart("Sweet corn soup","Vegetable lasagne");
        assertEquals(388,restaurant.orderTotal);
    }

    @Test
    public void removing_an_items_from_cart_should_return_orderTotal_after_subtracting_that_items_price(){
        restaurant.addToCart("Sweet corn soup","Vegetable lasagne");

        restaurant.removeFromCart("Sweet corn soup");
        assertEquals(269,restaurant.orderTotal);
    }
    //<<<<<<<<<<<<<<<<<<<<<<<CART>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}