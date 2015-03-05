/**
 * Created by 844920234 on 1/13/2015.
 */
public class Cat extends Pet {

    private static final String defaultName = "CatNo.";
    private static final String defaultNoise = " \"Nyan! Nyan! \" ";
    private static final String defaultLurk = " \"Pomp! Pomp! \" ";
    private static int defaultCounter = 0;
    public Cat(){
        this.name = defaultName + ++defaultCounter;
        this.age = 1;
    }

    public Cat(String name, int age){
        this.name = name;
        this.age = age;
    }

    public void makeNoise(){
        System.out.print(defaultNoise);
        super.makeNoise();
    }

    public void lurk(){
        System.out.print(defaultLurk);
        super.lurk();
    }
}
