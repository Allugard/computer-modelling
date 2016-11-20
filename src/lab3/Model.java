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
        //states=new ArrayList<>();
        states=new HashSet<>();
    }
    public void addState(State state){
        states.add(state);
    }

    public void buildTree(State state) throws CloneNotSupportedException {
    //    System.out.println(count);
    //    count++;
        states.add(state);
        if (state.getWiringStates().isEmpty()){
            //NodeInt [] nodeInts=state.getNodeInts();
            for (int i = 0; i <state.getNodeInts().length ; i++) {
                if(!state.getNodeInts()[i].isEmptyCores()) {
                    for (Node node : state.getNodeInts()[i].getNode().getWiringNodes().values()) {
                       // NodeInt [] nodeInts=new NodeInt[state.getNodeInts().length];
                        //System.arraycopy(state.getNodeInts(),0,nodeInts,0,nodeInts.length);

                        NodeInt[] nodeInts = Arrays.copyOf(state.getNodeInts(), state.getNodeInts().length);
                        nodeInts[i]=(NodeInt) nodeInts[i].clone();
                        nodeInts[i].getTask();
                        for (int j = 0; j <nodeInts.length ; j++) {
                            if(nodeInts[j].getNode()==node){
                                nodeInts[j]=(NodeInt) nodeInts[j].clone();
                                nodeInts[j].addTask();
                                break;
                            }
                        }
                        State bufState=new State(nodeInts);
                        if(!states.contains(bufState)) {
                            state.addWiringState(bufState);
                            buildTree(bufState);
                        }else {
                            bufState=findExistingState(bufState);
                            state.addWiringState(bufState);
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
                break;
            }
        }
        return state;
    }


}
