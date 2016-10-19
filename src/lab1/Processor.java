package lab1;

import static java.lang.Double.POSITIVE_INFINITY;

/**
 * Created by 111 on 25.09.2016.
 */
public class Processor {
    private Task task;
    private double endTime;


    public void setTask(Task task, double endTime) {
        this.task = task;
        this.endTime = endTime;
    }

    public void nullTask(){
        //System.out.println("не решена, ска. И вообще, иди нахуй пидар ебаный");
        task=null;
    }
    public boolean isEmpty(){
        if (task==null){
            return true;
        }
        return false;
    }
    public Task getTask(){
        return task;
    }
    public double getEndTime(){
        if(task==null){
            return POSITIVE_INFINITY;
        }
        return endTime;
    }
}
