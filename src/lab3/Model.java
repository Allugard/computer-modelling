package lab3;

import java.util.*;

/**
 * Created by 111 on 29.10.2016.
 */
public class Model {
    //private List <State> states;
    private Set <State> states;

    public Model(){
        //states=new ArrayList<>();
        states=new HashSet<>();
    }
    public void addState(State state){
        states.add(state);
    }

    public void buildTree(State state){
        states.add(state);

        if (state.getWiringStates().isEmpty()){
            //NodeInt [] nodeInts=state.getNodeInts();
            for (int i = 0; i <state.getNodeInts().length ; i++) {
                //NodeInt [] nodeInts=new NodeInt[state.getNodeInts().length];
                //System.arraycopy(state.getNodeInts(),0,nodeInts,0,nodeInts.length);
                NodeInt [] nodeInts=Arrays.copyOf(state.getNodeInts(),state.getNodeInts().length);
                for (Node node:nodeInts[i].getNode().getWiringNodes().values()) {

                }
            }
        }

        /*for (State state1:states
             ) {
            System.out.println(state1);
        }*/

       /* ListIterator iterator=states.listIterator();
        while (iterator.hasNext()){
            System.out.println(state);
            iterator.add(state);
            iterator.next();
        }*/
    }
}
