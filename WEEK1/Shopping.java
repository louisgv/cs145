public class Shopping{
  
  public static void main(String[] args){
    ShoppingCart cart = new ShoppingCart();
    
    cart.add(new LineItem("Glasses", 1, 0.9));
    cart.add(new LineItem("Eggs", 10, 3.6));
    cart.add(new LineItem("Milk", 23, 3.9));
    cart.add(new LineItem("Ink", 14, 2.9));
    cart.add(new LineItem("Shoes", 11, 1.1));
    
    System.out.print(cart.getTotalCost());
  }
  
}