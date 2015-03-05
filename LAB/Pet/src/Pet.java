/**
 * Created by 844920234 on 1/13/2015.
 */
public abstract class Pet {

    protected String name;
    protected int age;
//    private static final String defaultName = "PetNo.";
//    private static int defaultCounter = 0;
//
//    public Pet(){
//        this.name = defaultName + defaultCounter++;
//        this.age = 1;
//    }

    public void eat(){
        System.out.println(name + " is eating!");
    }

    public void makeNoise(){
        System.out.println(name + " is making noises!");
    }

    public void lurk(){
        System.out.println(name + " is lurking!");
    }

    public void play(){
        System.out.println(name + " is playing!");
    }

}
