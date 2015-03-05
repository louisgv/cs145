/**
 * Created by 844920234 on 1/13/2015.
 */
public class Tester {

    public static void main(String[] args){

        Dog dog1 = new Dog();
        Cat cat1 = new Cat();

        dog1.makeNoise();
        cat1.lurk();

        Dog dog2 = new Dog();
        Cat cat2 = new Cat();

        dog2.makeNoise();
        cat2.lurk();

        Cat novaTheCat = new Cat("Nova", 18);
        Dog saraTheDog = new Dog("Sara", 9);

        novaTheCat.makeNoise();
        saraTheDog.play();

        System.out.print("----------------------------\n");

        // Billion dollars feature of OOPs
        // Is_A relationship

        Pet dThree = new Dog();
        Pet dFour = new Dog();
        Pet cThree = new Cat();
        Pet cFour = new Cat();

        Pet[] pets = new Pet[] { dThree,
                dFour,
                cThree,
                cFour
        };

        for (int i=0; i < 4; i ++){
            pets[i].makeNoise();
        }
    }

}
