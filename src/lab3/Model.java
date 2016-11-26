package lab3;

import Jama.Matrix;

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

        double [][] matrixValue=new double[states.size()][states.size()];
        double [] vector=new double[states.size()];

        for (State state:states) {
            for (State innerState:state.getWiringStatesOut().keySet()) {
                matrixValue[state.genNumberName()][state.genNumberName()]-=(state.getWiringStatesOut().get(innerState).getNode().getMu()*state.getWiringStatesOut().get(innerState).getProbability());

            }
            for (State innerState:state.getWiringStatesIn()) {
                matrixValue[state.genNumberName()][innerState.genNumberName()]=innerState.getWiringStatesOut().get(state).getNode().getMu()*innerState.getWiringStatesOut().get(state).getProbability();
            }
        }
       /* for (State state:states) {
            vector[state.genNumberName()]=state.getProbabilityStaying();
            for (State innerState:state.getWiringStatesOut().keySet()) {
                matrixValue[state.genNumberName()][state.genNumberName()]-=(state.getWiringStatesOut().get(innerState).getR());

            }
            for (State innerState:state.getWiringStatesIn()) {
                matrixValue[state.genNumberName()][innerState.genNumberName()]=innerState.getWiringStatesOut().get(state).getR();
            }
            matrixValue[state.genNumberName()][state.genNumberName()]=state.getProbabilityStaying();
        }*/
        for (int i = 0; i <matrixValue.length; i++) {
            matrixValue[0][i]=1;

        }
        vector[0]=1;
       /* for (int i = 0; i <matrixValue.length ; i++) {
            System.out.println(Arrays.toString(matrixValue[i]));
        }*/
        //System.out.println(Arrays.toString(vector));

        double [] result= gauss(matrixValue,vector,matrixValue.length);

       // System.out.println(Arrays.toString(result));

        for (State state:states) {
            for (NodeInt nodeInt:state.getNodeInts()) {
                if(!nodeInt.isEmptyCores()){
                    nodeInt.getNode().incP(result[state.genNumberName()]);
                }
            }
        }
    }


    private static double[] gauss(double[][] A, double[] b, int n)
    {
        int i, j, l;
        double t;

        for (i = 0; i < n; i++)
        {
            t = A[i][i];
            for (j = i; j < n; j++)
                A[i][j] /= t;
            b[i] /= t;
            for (l = i + 1; l < n; l++)
            {
                for (j = i + 1; j < n; j++)
                    A[l][j] -= A[i][j]*A[l][i];
                b[l] -= b[i]*A[l][i];
            }
        }

        double[] x = new double[n];

        x[n-1] = b[n-1];
        for (i = n-2; i >= 0; i--)
        {
            x[i] = b[i];
            for (j = i+1; j < n; j++)
                x[i] -= A[i][j]*x[j];
        }
        return x;
    }


}
