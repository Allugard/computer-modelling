package lab3;

import java.util.*;

/**
 * Created by 111 on 29.10.2016.
 */
public class State {
    private  NodeInt [] nodeInts;
    private Map<State,IntensityOfTransition> wiringStatesOut;
    private List <State> wiringStatesIn;
    private String name="M";
    private static int count=0;
    private double probabilityStaying=0;
    double dt=0.1;


    public State(NodeInt ... nodeInt){
        name+=count;
        count++;
        nodeInts=nodeInt;
        wiringStatesOut =new HashMap<>();
        wiringStatesIn =new ArrayList<>();
    }
    public int genNumberName(){
        return Integer.parseInt(name.substring(1));
    }
    public void decCount(){
        count--;
    }
    public void addWiringStateOut(State state, double probability, Node node){
        wiringStatesOut.put(state,new IntensityOfTransition(probability, node));
    }

    public Map<State, IntensityOfTransition> getWiringStatesOut() {
        return wiringStatesOut;
    }

    public void addWiringStateIn(State state){
        wiringStatesIn.add(state);
    }

    public List<State> getWiringStatesIn() {
        return wiringStatesIn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NodeInt[] getNodeInts() {
        return nodeInts;
    }

    public void setNodeInts(NodeInt[] nodeInts) {
        this.nodeInts = nodeInts;
    }

    public double getProbabilityStaying() {
        return probabilityStaying;
    }

    public void setProbabilityStaying(double probabilityStaying) {
        this.probabilityStaying = probabilityStaying;
    }

    /*@Override
    public String toString() {
        return "State{" +
                "nodeInts=" + Arrays.toString(nodeInts) +
                ", wiringStatesOut=" + wiringStatesOut +
                '}';
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(nodeInts, state.nodeInts);

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(nodeInts);
    }

    @Override
    public String toString() {
        String b=name+Arrays.toString(nodeInts)+"{\n    In:";
        for (State state:wiringStatesOut.keySet()) {
            b+=state.getName()+"("+state.getWiringStatesOut().get(this).getNode().getMu()+","+state.getWiringStatesOut().get(this).getProbability()+")";
        }
        b+="\n    Out:";
        for (State state:wiringStatesIn) {
            b+=state.getName()+"("+wiringStatesOut.get(state).getNode().getMu()+","+wiringStatesOut.get(state).getProbability()+")";
        }
        b+="\n}";
        return b;
    }

    public void solveProbabilityStaying() {
        double lambda=0;
        Set<Node>bufSetNode=new HashSet<>();
        for (IntensityOfTransition intensityOfTransition:wiringStatesOut.values()) {
            if (!bufSetNode.contains(intensityOfTransition.getNode())){
                lambda+=intensityOfTransition.getNode().getMu();
                bufSetNode.add(intensityOfTransition.getNode());
            }
        }
        probabilityStaying=Math.pow(Math.E,(-lambda*dt));
    }

    public void calculateR() {
        Map<Node,Double> auxiliaryVar=new HashMap<>();
        for (IntensityOfTransition intensityOfTransition:wiringStatesOut.values()) {
            auxiliaryVar.put(intensityOfTransition.getNode(),(1-Math.pow(Math.E,(-dt*intensityOfTransition.getNode().getMu()))));
        }
        double sum=0;
        for (Double buf:auxiliaryVar.values()) {
            sum+=buf;
        }
        for (IntensityOfTransition intensityOfTransition:wiringStatesOut.values()) {
            double buf=intensityOfTransition.getProbability()*probabilityStaying*auxiliaryVar.get(intensityOfTransition.getNode())/sum;
            intensityOfTransition.setR(buf);
        }
        probabilityStaying=1;
        for (IntensityOfTransition intensityOfTransition:wiringStatesOut.values()) {
            probabilityStaying-=intensityOfTransition.getR();
        }
    }

    /*@Override
    public String toString() {
        String node="";
        for (int i = 0; i <nodeInts.length ; i++) {
            node+=nodeInts[i]+"\n";
        }
        return "State{" +
         //       "wiringStatesOut=" + wiringStatesOut +
                "\nNodes: \n"+
                node+
                '}';
    }*/
    public class IntensityOfTransition{
        private double probability;
        private Node node;
        private double r;

        public IntensityOfTransition(double probability, Node node) {
            this.probability = probability;
            this.node = node;
        }

        public double getProbability() {
            return probability;
        }

        public void setProbability(double probability) {
            this.probability = probability;
        }

        public Node getNode() {
            return node;
        }

        public void setNode(Node node) {
            this.node = node;
        }

        public double getR() {
            return r;
        }

        public void setR(double r) {
            this.r = r;
        }
    }
}

