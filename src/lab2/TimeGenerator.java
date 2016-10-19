package lab2;

import java.util.Random;

/**
 * Created by 111 on 19.10.2016.
 */
public class TimeGenerator {
    private static TimeGenerator timeGenerator;
    Random r=new Random();
    private TimeGenerator(){
    }
     public static TimeGenerator getInstance() {
         if(timeGenerator==null){
             timeGenerator=new TimeGenerator();
         }
        return timeGenerator;
    }

    public double getTime(double mu){
        return -(1/mu)*Math.log(r.nextDouble());
    }
}
