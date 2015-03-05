/**
 * Created by 844920234 on 1/13/2015.
 */
public class Dog extends Pet{

    private static final String defaultName = "DogNo.";
    private static final String defaultNoise = " \"Ow! Ow! \" ";
    private static final String defaultPlay = " \"Srek! Srek! \" ";

    private static int defaultCounter = 0;


    public Dog(){
        this.name = defaultName + ++defaultCounter;
        this.age = 1;
    }

    public Dog(String name, int age){
        this.name = name;
        this.age = age;
    }

    public void makeNoise(){
        System.out.print(defaultNoise);
        super.makeNoise();
    }

    public void play(){
        System.out.print(defaultPlay);
        super.makeNoise();
    }
}
