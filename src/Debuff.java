import java.util.Scanner;

/**
 * Created by Floglor on 21.07.2017.
 */
public class Debuff {
    Scanner sc = new Scanner(System.in);
    private int HP;
    private int atk;


    Debuff () {

        System.out.println("Введите дебафф к ХП");
        HP = sc.nextInt();

        System.out.println("Введите дебафф к атк");
        atk = sc.nextInt();

    }
}
