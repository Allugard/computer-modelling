package lab2;

import lab2.Task;

/**
 * Created by 111 on 19.10.2016.
 */
public class Core {
    private Task task;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public boolean isEmpty(){
        if (task==null){
            return true;
        }
        return false;
    }

    public void removeTask() {
        this.task = null;
    }
}
