
/**
 * Created by 844920234 on 1/6/2015.
 */
public class ShoppingCart {

    private LineItem[] cart;
    private int cartSize;
    public ShoppingCart(){
        cart = new LineItem[10];
    }

    public void add(LineItem newItem){
        if (cartSize < 10){
            cart[cartSize] = newItem;
            cartSize++;
        }
    }

    public double getTotalCost(){
        double total = 0; 
        for (int i = 0; i < cartSize; i++){
            total += cart[i].getCost();
        }
        return total;
    }
   
}

