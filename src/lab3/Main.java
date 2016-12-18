package lab3;

import lab3.Node;
import lab2.Task;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by 111 on 19.10.2016.
 *
 */
public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Node cp=new Node("CPU",0.1,3);
        Node nBridge=new Node("nBridge",1,1);
        Node op=new Node("RAM" , 0.05,1);
        Node gp=new Node("GPU",0.1,1);
        Node sBridge=new Node("sBridge",0.2,1);
        Node nA=new Node("Network Adapter",0.01,1);
        Node cmd=new Node("CMD",0.01,1);
        Node cod=new Node("COD",0.005,1);
        Node isa=new Node("ISA",0.05,1);
        Node com=new Node("COM",0.003,1);

        List<Node>nodes=new ArrayList<Node>();
        nodes.add(cp);
        nodes.add(op);
        nodes.add(nBridge);
        nodes.add(gp);
        nodes.add(sBridge);
        nodes.add(nA);
        nodes.add(cmd);
        nodes.add(cod);
        nodes.add(isa);
        nodes.add(com);

        cp.addWiringNode(1,nBridge);

        nBridge.addWiringNode(0.6,gp);
        nBridge.addWiringNode(0.8,op);
        nBridge.addWiringNode(0.9,sBridge);
        nBridge.addWiringNode(1,cp);

        op.addWiringNode(1,nBridge);

        gp.addWiringNode(1,nBridge);

        sBridge.addWiringNode(0.5,nBridge);
        sBridge.addWiringNode(0.7,cmd);
        sBridge.addWiringNode(0.8,nA);
        sBridge.addWiringNode(0.9,cod);
        sBridge.addWiringNode(1,isa);

        cmd.addWiringNode(1,sBridge);

        cod.addWiringNode(1,sBridge);

        nA.addWiringNode(1,sBridge);

        isa.addWiringNode(0.5,sBridge);
        isa.addWiringNode(1,com);

        com.addWiringNode(1,isa);
        NodeInt nodeInt=new NodeInt(cp);

        for (int i = 0; i <5 ; i++) {
            nodeInt.addTask();
        }
        State firstState=new State(
                nodeInt,
                new NodeInt(nBridge),
                new NodeInt(op),
                new NodeInt(gp),
                new NodeInt(sBridge),
                new NodeInt(nA),
                new NodeInt(cmd),
                new NodeInt(cod),
                new NodeInt(isa),
                new NodeInt(com)
                );


        Model model=new Model();
        model.buildTree(firstState);
     //   model.printTree(firstState);
        model.solve();
        for (Node node : nodes) {
            System.out.println(node);
        }
      //  System.out.println("\nlab2\n");
      //  lab2.Main.main(null);
    }
}
