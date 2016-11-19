package lab3;

import lab2.Task;
import lab2.TimeGenerator;

import java.util.*;

/**
 * Created by 111 on 19.10.2016.
 */
public class Node {
    private String name;
    private Map<Double, Node> wiringNodes;
    private int cores;
    private double mu;
    private List <Task> queue;

    public Node(String name, double mu, int cores) {
        this.name = name;
        this.wiringNodes = new TreeMap<>();
        this.mu = mu;
        this.queue = new ArrayList<>();
        this.cores=cores;
    }



    public void addWiringNode(double p, Node node){
        wiringNodes.put(p,node);
    }
    public void addTask(Task task, double time){

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
        Node node=wiringNodes.get(d);
       return node;
    }
    public Task getTask(){
        return null;
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


    public void addTaskToQueue(Task task) {
        queue.add(task);
    }

    public int getCores() {
        return cores;
    }

    public void setCores(int cores) {
        this.cores = cores;
    }

    public Map<Double, Node> getWiringNodes() {
        return wiringNodes;
    }

    public void setWiringNodes(Map<Double, Node> wiringNodes) {
        this.wiringNodes = wiringNodes;
    }
}
