package lab2;

import java.util.*;

/**
 * Created by 111 on 19.10.2016.
 */
public class Node {
    private String name;
    private Map<Double,Node> wiringNodes;
    private List <Core> cores;
    private TimeGenerator timeGenerator;
    private double mu;
    private List <Task> queue;

    public Node(String name, double mu, Core ... cores) {
        this.name = name;
        this.wiringNodes = new HashMap<>();
        this.cores = Arrays.asList(cores);
        this.timeGenerator = TimeGenerator.getInstance();
        this.mu = mu;
        this.queue = new ArrayList<>();
    }


}
