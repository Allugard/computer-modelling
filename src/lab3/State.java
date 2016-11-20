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

    public State(NodeInt ... nodeInt){
        name+=count;
        count++;
        nodeInts=nodeInt;
        wiringStatesOut =new HashMap<>();
        wiringStatesIn =new ArrayList<>();
    }
    public void decCount(){
        count--;
    }
    public void addWiringStateOut(State state, double probability, double intensity){
        wiringStatesOut.put(state,new IntensityOfTransition(probability, intensity));
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
            b+=state.getName()+"("+state.getWiringStatesOut().get(this).getIntensity()+","+state.getWiringStatesOut().get(this).getProbability()+")";
        }
        b+="\n    Out:";
        for (State state:wiringStatesIn) {
            b+=state.getName()+"("+wiringStatesOut.get(state).getIntensity()+","+wiringStatesOut.get(state).getProbability()+")";
        }
        b+="\n}";
        return b;
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
    class IntensityOfTransition{
        private double probability;
        private double intensity;

        public IntensityOfTransition(double probability, double intensity) {
            this.probability = probability;
            this.intensity = intensity;
        }

        public double getProbability() {
            return probability;
        }

        public void setProbability(double probability) {
            this.probability = probability;
        }

        public double getIntensity() {
            return intensity;
        }

        public void setIntensity(double intensity) {
            this.intensity = intensity;
        }
    }
}

