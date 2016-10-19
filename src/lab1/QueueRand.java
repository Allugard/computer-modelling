package lab1;

import java.util.*;

/**
 * Created by 111 on 25.09.2016.
 */
public class QueueRand implements Queue {
    List<Task> tasks=new ArrayList<>();
    Random r=new Random();
    @Override
    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public Task getTask() {
        if (tasks.isEmpty()){
            return null;
        }
        int index=r.nextInt(tasks.size());
        Task task=tasks.get(index);
        tasks.remove(task);
        return task;
    }

    @Override
    public boolean isEmpty() {
        if(tasks.isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public void checRelevance(double time) {
        /*System.out.println("Время"+time);
        for (lab1.Task tasl:tasks
             ) {
            System.out.println(tasl.getSystemTime()+"такое время каждой зачади");
        }*/
        if(!tasks.isEmpty()){
            ListIterator<Task> it=tasks.listIterator();
            while (it.hasNext()){
                Task task=it.next();
                if(!task.checkRelevance(time)){
                   // task.setTimeInQueue(time);
                   // task.close(time);
                    it.remove();
                }
            }
        }
    }
}
