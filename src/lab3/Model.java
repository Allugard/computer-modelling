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
