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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public double getKeyByValue(Node node) {
        double buf=0;
        Object[]bufArray= wiringNodes.keySet().toArray();
        for (int i = 0; i <bufArray.length ; i++) {
            if (wiringNodes.get(bufArray[i])==node){
                if(i==0){
                    buf=(Double)bufArray[i];
                }else {
                    buf=(Double) bufArray[i]-(Double) bufArray[i-1];
                }
                break;
            }
        }
        return buf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return name != null ? name.equals(node.name) : node.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
