package lab1;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by 111 on 25.09.2016.
 */
public class QueueFB implements Queue {
    List<List> lists=new ArrayList<>();
    @Override
    public void addTask(Task task) {
        if(lists.isEmpty()){
            lists.add(new ArrayList());
        }
        lists.get(0).add(task);
    }

    @Override
    public Task getTask() {
        if(lists.isEmpty()){
            return null;
        }
        for (List<Task>list:lists) {
            if(list.isEmpty()){
                continue;
            }else {
                return list.get(0);
            }
        }
        return null;
    }
    public Task getTask(double time) {
        if(lists.isEmpty()){
            return null;
        }
        for (List<Task>list:lists) {
            if(list.isEmpty()){
                continue;
            }else {
                Task task=list.get(0);
                task.addTimeInQueue(time);
                return task;
            }
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        /*if(lists.isEmpty()){
            return true;
        }*/
        for (List<Task>list:lists) {
            if(list.isEmpty()){
                continue;
            }else {
                return false;
            }
        }
        return true;
    }

    @Override
    public void checRelevance(double time) {
        if(!lists.isEmpty()){
            ListIterator<List> iteratorListIterator=lists.listIterator();
            while (iteratorListIterator.hasNext()){
                List tasks=iteratorListIterator.next();
                ListIterator<Task> it=tasks.listIterator();
                while (it.hasNext()){
                    Task task=it.next();
                    if(!task.checkRelevance(time)){
                        // task.setTimeInQueue(time);
                        //  task.close(time);
                        it.remove();
                    }
                }
            }
        }
    }

    public void next(Task task){
        int i=0;
        for (List<Task> list:lists) {
            if(list.remove(task)){
                i=lists.indexOf(list);
                break;
            }
        }
        if(i==(lists.size()-1)){
            List list=new ArrayList();
            list.add(task);
            lists.add(list);
        }else{
            lists.get(i+1).add(task);
        }
    }

    public void deleteTask(Task task) {
        for (List<Task>list:lists) {
            list.remove(task);
        }
    }
}
