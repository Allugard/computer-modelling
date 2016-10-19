package lab1;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by 111 on 28.09.2016.
 */
public class Statistic {
    private double relevance;
    private final double[] k={-2,-1,-1,3,5};
    private int tasks;
    private int endedTasks;
    private double timeTasksInSystem;
    private double timeTasksInQueue;
    private List <Double> listTimeInSystem;
    public Statistic(){
        tasks=0;
        endedTasks=0;
        timeTasksInQueue=0;
        relevance=0;
        listTimeInSystem=new ArrayList<>();

    }

    public void incTasks(){
        tasks++;
    }
    public void incEndedTasks(){
        endedTasks++;
    }
    public void addTimeTasksInQueue(double time){
        timeTasksInQueue+=time;
    }
    public void addTimeTasksInSystem(double time){
        timeTasksInSystem+=time;
    }
    public void addTimeTasks(double timeInSystem){
        listTimeInSystem.add(timeInSystem);
    }

    public void result() {
        double endedTask=(double)endedTasks/(double)tasks;
        System.out.println("Процент решенных задач="+endedTask);

        double averageTime=0;
        for (Double d:listTimeInSystem) {
            averageTime+=d;
        }
        averageTime=averageTime/(double)listTimeInSystem.size();
        System.out.println("Среднее время в системе="+averageTime);

        double D=0;
        for (int i = 0; i <listTimeInSystem.size() ; i++) {
            D+=Math.pow((listTimeInSystem.get(i)-averageTime),2);
        }
        D=D/(double)listTimeInSystem.size();
        System.out.println("Дисперсия="+D);
        relevance=relevance/(double)listTimeInSystem.size();
        System.out.println("Актуальность="+relevance);
        timeTasksInQueue=timeTasksInQueue/(double)listTimeInSystem.size();
        System.out.println("Время в очереди="+(timeTasksInQueue));

        double y=k[0]*averageTime+k[1]*D+k[2]*timeTasksInQueue+k[3]*endedTask+k[4]*relevance;
        System.out.println("y="+y);

    }

    public void addRelevance(double relevance) {
        this.relevance+=relevance;
    }
}
