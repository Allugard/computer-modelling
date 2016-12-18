package lab3;

/**
 * Created by 111 on 29.10.2016.
 */
public class NodeInt implements Cloneable {
    private int queue;
    private int taskInCore;
    private int freeCore;
    private Node node;

    public NodeInt(Node node){
        this.node=node;
        freeCore=node.getCores();
    }

    public void addTask(){
        if (freeCore!=0){
            freeCore--;
            taskInCore++;
        }else {
            queue++;
        }
    }

    public void getTask(){
        if(queue!=0){
            queue--;
        }else {
            freeCore++;
            taskInCore--;
        }
    }
    public boolean isEmptyCores(){
        return  taskInCore==0;
    }

    public Node getNode() {
        return node;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NodeInt nodeInt = (NodeInt) o;

        if (queue != nodeInt.queue) return false;
        if (taskInCore != nodeInt.taskInCore) return false;
        return freeCore == nodeInt.freeCore;

    }

    @Override
    public int hashCode() {
        int result = queue;
        result = 31 * result + taskInCore;
        result = 31 * result + freeCore;
        return result;
    }

    @Override
    public String toString() {
        return node.getName() +
                "<" + queue +
                "," + taskInCore +
                "," + freeCore +
                ">";
    }
}
