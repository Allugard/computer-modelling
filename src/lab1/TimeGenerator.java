package lab1;

import java.util.Random;

/**
 * Created by 111 on 25.09.2016.
 */
public class TimeGenerator {
    double lambda;
    double [] mu;
    Random r;
    double p1=0.7;
    double p2=0.9;
    double p3=1;

    public TimeGenerator(double lambda, double [] mu) {
        this.lambda = lambda;
        this.mu = mu;
        r=new Random();
    }
    public double getNextTime(){
        double rand=r.nextDouble();
        return (-1/lambda)*Math.log(rand);
    }
    public double getExecutionTime(){
        double rand=r.nextDouble();
        if(rand<p1){
            return (-1/mu[0])*Math.log(rand);
        }else{
            if(rand<p2){
                return (-1/mu[1])*Math.log(rand);
            }else{
                return (-1/mu[2])*Math.log(rand);

            }
        }
       // return (-1/mu)*Math.log(rand);
    }
}
