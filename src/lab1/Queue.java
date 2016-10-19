package lab1;

/**
 * Created by 111 on 25.09.2016.
 */
public interface Queue {
    void addTask(Task task);
    Task getTask();
    boolean isEmpty();

    void checRelevance(double time);

}
