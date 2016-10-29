package lab3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 111 on 29.10.2016.
 */
public class State {
    private static NodeInt [] nodeInts;
    private List <State> wiringStates;

    public State(NodeInt ... nodeInt){
        nodeInts=nodeInt;
        wiringStates=new ArrayList<>();
    }

    public void addWiringState(Node node){
        //TODO
    }


}
