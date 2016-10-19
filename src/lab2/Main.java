package lab2;

/**
 * Created by 111 on 19.10.2016.
 */
public class Main {
    public static void main(String[] args) {
        TimeGenerator timeGenerator=TimeGenerator.getInstance();
        TimeGenerator timeGenerator2=TimeGenerator.getInstance();
        System.out.println(timeGenerator+"  "+timeGenerator2);
    }
}
