package lab3;

import lab3.Node;
import lab2.Task;

import java.util.*;

/**
 * Created by 111 on 19.10.2016.
 */
public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Node cp=new Node("CPU",1,3);
        Node nBridge=new Node("nBridge",1,1);
        Node op=new Node("RAM" , 0.05,1);
        Node gp=new Node("GPU",0.1,1);
        Node sBridge=new Node("sBridge",0.2,1);
        Node nA=new Node("Network Adapter",0.01,1);
        Node cmd=new Node("CMD",0.01,1);
        Node cod=new Node("COD",0.005,1);
        Node isa=new Node("ISA",0.05,1);
        Node com=new Node("COM",0.003,1);

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

        for (int i = 0; i <6 ; i++) {
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

        Node cpu=new Node("CPU",1,21);
        Node northBridge=new Node("nBridge",1,1);
        Node ram=new Node("RAM" , 0.05,1);

        cpu.addWiringNode(1,northBridge);

        northBridge.addWiringNode(0.5,cpu);
        northBridge.addWiringNode(1,ram);

        ram.addWiringNode(1,northBridge);


        NodeInt nodeInt1=new NodeInt(cpu);
        nodeInt1.addTask();
        nodeInt1.addTask();
        nodeInt1.addTask();
        nodeInt1.addTask();
        nodeInt1.addTask();
        nodeInt1.addTask();

        State testState=new State(
                nodeInt1,
                new NodeInt(northBridge),
                new NodeInt(ram)
        );

        Model model=new Model();
        long time=System.currentTimeMillis();
        model.buildTree(firstState);
        long time1=System.currentTimeMillis();
        long res=time1-time;
        System.out.println(res);

    }
}
