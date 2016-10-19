package lab1;

/**
 * Created by 111 on 25.09.2016.
 */
public class QueueManagementSystem {
    private Processor processor;
    private Queue queue;
    private double time;
    private TimeGenerator timeGenerator;
    private Statistic statistic=new Statistic();

    public QueueManagementSystem(Queue queue, double lambda, double [] mu) {
        processor=new Processor();
        this.queue=queue;
        timeGenerator=new TimeGenerator(lambda, mu);
    }

    public void start(int n){
        time=0;
        Task taskk=new Task(statistic,time, timeGenerator.getExecutionTime());
        processor.setTask(taskk,(time+taskk.getTimeOfExecution()));
        statistic.incTasks();
        double generationTime=timeGenerator.getNextTime();
        int i=0;
        while (i<n){
           // queue.checRelevance(time);
            if(generationTime<processor.getEndTime()){
                time=generationTime;
                statistic.incTasks();
                queue.addTask(new Task(statistic,time, timeGenerator.getExecutionTime()));
                generationTime=time+timeGenerator.getNextTime();
                if(processor.isEmpty()){
                    Task task= queue.getTask();
                    double buf=time+task.getTimeOfExecution();
                    processor.setTask(task,buf);
                }
            }else {
                time=processor.getEndTime();
                statistic.incEndedTasks();
                processor.getTask().close(time);
                processor.nullTask();
                if(!queue.isEmpty()){
                    Task task=queue.getTask();
                   // task.setTimeInQueue(time);
                    processor.setTask(task,(time+timeGenerator.getExecutionTime()));
                }
                i++;
            }
        }
        statistic.result();
    }

    public void startFB(int n, double fixedTime){
        time=0;
        QueueFB queueFB=new QueueFB();
        Task taskk=new Task(statistic,time, timeGenerator.getExecutionTime());
        processor.setTask(taskk,time+taskk.getTimeOfExecution());
        queueFB.addTask(taskk);
        statistic.incTasks();
        double generationTime=timeGenerator.getNextTime();
        int i=0;
        while (i<n){
           // queueFB.checRelevance(time);
            if(generationTime<(time+fixedTime)&&generationTime<(processor.getEndTime())){
                    time=generationTime;
                    statistic.incTasks();
                    queueFB.addTask(new Task(statistic,time, timeGenerator.getExecutionTime()));
                    generationTime=time+timeGenerator.getNextTime();
                    if(processor.isEmpty()){
                        Task task= queueFB.getTask();
                        double buf=time+task.getTimeOfExecution();
                        processor.setTask(task,buf);
                    }
            } else{
                if((time+fixedTime)<(processor.getEndTime())){
                    time += fixedTime;
                    if(!processor.isEmpty()) {
                        Task task = processor.getTask();
                        double buf=task.getTimeOfExecution() - fixedTime;
                        task.setTimeOfExecution(buf);
                        queueFB.next(task);
                        task = queueFB.getTask();
                         buf=time + task.getTimeOfExecution();
                        processor.setTask(task,buf );
                    }
                }else {
                    time=processor.getEndTime();
                    statistic.incEndedTasks();
                    Task task=processor.getTask();
                    task.closeFB(time);
                    queueFB.deleteTask(task);
                    processor.nullTask();
                    if(!queueFB.isEmpty()){
                        task=queueFB.getTask();
                        /*task.addTimeInQueue(time);
                        task.setTimeInQueue(time);*/
                        double buf=time+timeGenerator.getExecutionTime();
                        processor.setTask(task,buf);
                    }
                    i++;
                }
            }
           // System.out.println(time);
        }
        statistic.result();
    }
}
