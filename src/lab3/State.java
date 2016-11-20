package lab3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 111 on 29.10.2016.
 */
public class State {
    private  NodeInt [] nodeInts;
    private List <State> wiringStates;

    public State(NodeInt ... nodeInt){
        nodeInts=nodeInt;
        wiringStates=new ArrayList<>();
    }

    public void addWiringState(State state){
        wiringStates.add(state);
    }

    public List<State> getWiringStates() {
        return wiringStates;
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
                ", wiringStates=" + wiringStates +
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
        String node="";
        for (int i = 0; i <nodeInts.length ; i++) {
            node+=nodeInts[i]+"\n";
        }
        return "State{" +
         //       "wiringStates=" + wiringStates +
                "\nNodes: \n"+
                node+
                '}';
    }
}
