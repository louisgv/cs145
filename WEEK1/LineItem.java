
/**
 * Created by 844920234 on 1/6/2015.
 */
public class LineItem {

    private String name;
    private int quantity;
    private double pricePerUnit;

    public LineItem(String name, int quantity, double pricePerUnit){
        this.name = name;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }

    public double getCost(){
        return pricePerUnit * quantity;
    }

    public void setQuantity(int newQuantity){
        quantity = newQuantity;
    }
}
