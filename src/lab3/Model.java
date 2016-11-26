package lab3;

import java.util.*;

/**
 * Created by 111 on 29.10.2016.
 */
public class Model {
    //private List <State> states;
    private Set <State> states;
    static int count=0;
    public Model(){
        states=new LinkedHashSet<>();
    }
    public void addState(State state){
        states.add(state);
    }

    public void buildTree(State state) throws CloneNotSupportedException {
      //  System.out.println(count);
      //  count++;
        states.add(state);
        if (state.getWiringStatesOut().isEmpty()){
            for (int i = 0; i <state.getNodeInts().length ; i++) {
                if(!state.getNodeInts()[i].isEmptyCores()) {
                    for (Node node : state.getNodeInts()[i].getNode().getWiringNodes().values()) {
                        NodeInt[] nodeInts = Arrays.copyOf(state.getNodeInts(), state.getNodeInts().length);
                        nodeInts[i]=(NodeInt) nodeInts[i].clone();
                        nodeInts[i].getTask();
                       // double intensity=state.getNodeInts()[i].getNode().getMu();
                        double probability=state.getNodeInts()[i].getNode().getKeyByValue(node);
                        for (int j = 0; j <nodeInts.length ; j++) {
                            if(nodeInts[j].getNode()==node){
                                nodeInts[j]=(NodeInt) nodeInts[j].clone();
                                nodeInts[j].addTask();
                                break;
                            }
                        }
                        State bufState=new State(nodeInts);
                        if(!states.contains(bufState)) {
                            state.addWiringStateOut(bufState,probability,state.getNodeInts()[i].getNode());
                            bufState.addWiringStateIn(state);
                            buildTree(bufState);
                        }else {
                            bufState=findExistingState(bufState);
                            state.addWiringStateOut(bufState,probability,state.getNodeInts()[i].getNode());
                            bufState.addWiringStateIn(state);
                        }
                    }
                }
            }
        }
    }

    private State findExistingState(State state) {
        Iterator it=states.iterator();
        while (it.hasNext()){
            State bufState= (State) it.next();
            if(bufState.equals(state)){
                state=bufState;
                state.decCount();
                break;
            }
        }
        return state;
    }


    public void printTree(State firstState) {
        for (State state:states
             ) {
            System.out.println(state);
        }
    }

    public void solve() {

        states.forEach(state -> state.solveProbabilityStaying());
        states.forEach(state -> state.calculateR());
    }



}
