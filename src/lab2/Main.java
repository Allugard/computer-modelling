package lab2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by 111 on 19.10.2016.
 */
public class Main {
    public static void main(String[] args) {
        PetriNet petriNet=new PetriNet();
        Node node1=new Node("a",10,new Core(),new Core(),new Core());
        Node node2=new Node("b",10,new Core());
        Node node3=new Node("c",10,new Core());

        node1.addWiringNode(0.5,node2);
        node1.addWiringNode(1.,node3);

        node2.addWiringNode(1.,node1);
        node3.addWiringNode(1.,node1);

        petriNet.addTask(node3,new Task());
        petriNet.addTask(node3,new Task());

        petriNet.start(1000);

    }
}
