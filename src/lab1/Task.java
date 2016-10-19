package lab1;

/**
 * Created by 111 on 25.09.2016.
 */
public class Task {
    private double systemTime;
    private double timeOfDeath;
    private double timeInQueue;
    private static final double t1=3.0;
    private static final double t2=6.0;
    private Statistic statistic;
    private double timeOfExecution;
    private double timeSolution;
    private double stopTime;


    public Task(Statistic statistic,double time, double timeOfExecution){
        this.statistic=statistic;
        systemTime=time;
        this.timeOfExecution=timeOfExecution;
        timeSolution=timeOfExecution;
        timeInQueue=0;
        stopTime=time;
       // System.out.println("+1");
    }
    public void close(double time){
        timeOfDeath=time;
        double buf=timeOfDeath-systemTime;
        statistic.addTimeTasks(buf);
        statistic.addTimeTasksInSystem(buf);
      //  statistic.addTimeTasksInQueue(timeInQueue-systemTime);
       buf=buf-timeOfExecution;
        statistic.addTimeTasksInQueue(buf);

        statistic.addRelevance(relevance());
       // System.out.println("в очереди"+(timeInQueue-systemTime));
       // System.out.println("в системе"+(timeOfDeath-systemTime));
    }
    public void closeFB(double time){
        timeOfDeath=time;
        double buf=timeOfDeath-systemTime;
        statistic.addTimeTasks(buf);
        statistic.addTimeTasksInSystem(buf);
        //statistic.addTimeTasksInQueue(timeInQueue);
        buf=buf-timeSolution;
        statistic.addTimeTasksInQueue(buf);

        statistic.addRelevance(relevanceFB());
        // System.out.println("в очереди"+(timeInQueue-systemTime));
        // System.out.println("в системе"+(timeOfDeath-systemTime));
    }
    private double relevanceFB(){
        double a=0;
        if((timeInQueue)<t1){
            return 1.0;
        }else {
            double t=t2-(timeInQueue);
            a=t/t2;
            return a;
        }
    }
    private double relevance(){
        double a=0;
        if((timeInQueue-systemTime)<t1){
            return 1.0;
        }else {
            double t=t2-(timeInQueue-systemTime);
            a=t/t2;
            return a;
        }
    }

    public double getSystemTime() {
        return systemTime;
    }

    public void setSystemTime(double systemTime) {
        this.systemTime = systemTime;
    }

    public double getTimeOfDeath() {
        return timeOfDeath;
    }

    public void setTimeOfDeath(double timeOfDeath) {
        this.timeOfDeath = timeOfDeath;
    }

    public boolean checkRelevance(double time) {
        if((time-systemTime)<t2){
            return true;
        }
        /*if(timeInQueue>6){
            return true;
        }*/
        return false;
    }

    public void setTimeInQueue(double timeInQueue) {
        this.timeInQueue = timeInQueue;
    }

    public double getTimeInQueue() {
        return timeInQueue;
    }

    public double getTimeOfExecution() {
        return timeOfExecution;
    }

    public void setTimeOfExecution(double timeOfExecution) {
        this.timeOfExecution = timeOfExecution;
    }

    public void addTimeInQueue(double time) {
        timeInQueue=timeInQueue+(time-stopTime);
    }
    public void setStopTime(double time){
        stopTime=time;
    }
}
