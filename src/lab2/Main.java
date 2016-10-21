package lab2;

/**
 * Created by 111 on 19.10.2016.
 */
public class Main {
    public static void main(String[] args) {
        PetriNet petriNet=new PetriNet();
        int numberTask =100000;
        Node cp=new Node("CP",1,new Core(),new Core(),new Core());
        Node nBridge=new Node("nBridge",1,new Core());
        Node op=new Node("OP",0.05,new Core());
        Node gp=new Node("GP",0.1,new Core());
        Node sBridge=new Node("sBridge",0.2,new Core());
        Node nA=new Node("Network Adapter",0.01,new Core());
        Node cmd=new Node("CMD",0.01,new Core());
        Node cod=new Node("COD",0.005,new Core());
        Node isa=new Node("ISA",0.05,new Core());
        Node com=new Node("COM",0.003,new Core());

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
        for (int i = 0; i < numberTask; i++) {
            petriNet.addTask(cp,new Task());
        }


        petriNet.addNode(cp);
        petriNet.addNode(op);
        petriNet.addNode(gp);
        petriNet.addNode(nBridge);
        petriNet.addNode(sBridge);
        petriNet.addNode(cmd);
        petriNet.addNode(cod);
        petriNet.addNode(nA);
        petriNet.addNode(isa);
        petriNet.addNode(com);

        petriNet.start(10000);

    }
}
