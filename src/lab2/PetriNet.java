package lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by 111 on 19.10.2016.
 */
public class PetriNet {
    private TimeGenerator timeGenerator=TimeGenerator.getInstance();
    private List<Node>nodes;
    private TreeMap<Double,Node> nodeWithTask;
    public PetriNet(){
        nodes=new ArrayList<>();
        nodeWithTask=new TreeMap<>();

    }

    public void addNode(Node node){
        nodes.add(node);
    }

    public void addTask(Node node, Task task){
        if(node.coresIsEmpty()) {
            double bufTime = timeGenerator.getTime(node.getMu());
            node.addTask(task, bufTime);
            nodeWithTask.put(bufTime, node);
        }else {
            node.addTaskToQueue(task);
        }
    }
    public void start(double timeAll){
        double timeNow=0;
        while (timeNow<timeAll){
            double bufTime=nodeWithTask.firstKey();
            timeNow=+bufTime;
            Node firstNode=nodeWithTask.get(bufTime);
            nodeWithTask.remove(bufTime);
            Node nextNode=firstNode.nextNode();
            if(nextNode.coresIsEmpty()) {
                double timeExec=timeNow+timeGenerator.getTime(nextNode.getMu());
                nextNode.addTask(firstNode.getTask(), timeExec);
                nodeWithTask.put(timeExec,nextNode);
            }else {
                nextNode.addTaskToQueue(firstNode.getTask());
            }
            if(firstNode.queueIsEmpty()==false){
                bufTime=timeNow+timeGenerator.getTime(firstNode.getMu());
                firstNode.addTaskFromQueue(bufTime);
                nodeWithTask.put(bufTime,firstNode);


            }
            System.out.println(firstNode+"   ->"+nextNode);

        }
    }

}
