package lab2;

import java.util.*;

/**
 * Created by 111 on 19.10.2016.
 */
public class Node {
    private String name;
    private Map<Double,Node> wiringNodes;
    private List <Core> cores;
    private double mu;
    private List <Task> queue;
    private double timeOfWork;
    private static TimeGenerator timeGenerator;

    public Node(String name, double mu, Core ... cores) {
        this.name = name;
        this.wiringNodes = new TreeMap<>();
        this.cores = Arrays.asList(cores);
        this.mu = mu;
        this.queue = new ArrayList<>();
        timeOfWork=0;
    }

    public void addWiringNode(double p, Node node){
        wiringNodes.put(p,node);
    }
    public void addTask(Task task, double time){
        boolean isEmpty=false;
        for (Core core:cores) {
            if(core.isEmpty()){
                core.setTask(task);
                task.setTimeEnded(time);
                isEmpty=true;
                break;
            }
        }
        if(isEmpty==false){
            queue.add(task);
        }
    }

    public double getMu(){
        return mu;
    }

    public Node nextNode(){
        Random r=new Random();
        double d=r.nextDouble();
        Iterator<Double> it=wiringNodes.keySet().iterator();
        while (it.hasNext()){
            double buf=it.next();
            if(d<buf){
                d=buf;
                break;
            }
        }
     //   System.out.println(d);
        Node node=wiringNodes.get(d);
       return node;
    }
    public Task getTask(){
        List <Double> times=new ArrayList<>();
        Task task=null;
        for (Core core:cores) {
            if (core.isEmpty()==false){
                times.add(core.getTask().getTimeEnded());
            }
        }
        Collections.sort(times);
        for (Core core: cores) {
            if (core.isEmpty() == false) {
                if (times.get(0) == core.getTask().getTimeEnded()) {
                    task = core.getTask();
                    core.removeTask();
                }
            }
        }
        return task;
    }
    public boolean queueIsEmpty(){
        return queue.isEmpty();
    }
    public void addTaskFromQueue(double time){
        this.addTask(queue.get(0),time);
        queue.remove(0);
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                '}';
    }

    public boolean coresIsEmpty() {
        boolean isEmpty=false;
        for (Core core:cores) {
            if(core.isEmpty()){
                isEmpty=true;
            }
        }
        return isEmpty;
    }

    public void addTaskToQueue(Task task) {
        queue.add(task);
    }

    public void addTimeOfWork(double timeNow, double timeExec) {
        timeOfWork=timeOfWork+timeExec-timeNow;
    }
    public double getWork(double timeAll){
        return timeOfWork/timeAll;
    }
}
