package lab1;


/**
 * Created by 111 on 25.09.2016.
 */
public class Main {
    public static void main(String[] args) {
        Queue queueRand=new QueueRand();
        Queue queueSF=new QueueSF();
        Queue queueFB=new QueueFB();
        double lambda=7;
        double mu[]={9,8,7};
        int n=1000000;
        System.out.println("-------------------------------------");
        System.out.println("Rand");
        QueueManagementSystem queueManagementSystem=new QueueManagementSystem(queueRand,lambda,mu);
        queueManagementSystem.start(n);
        System.out.println("-------------------------------------");
        System.out.println("SF");
        QueueManagementSystem queueManagementSystemSF=new QueueManagementSystem(queueSF,lambda,mu);
        queueManagementSystemSF.start(n);
        System.out.println("-------------------------------------");
        System.out.println("FB");
        QueueManagementSystem queueManagementSystemFB=new QueueManagementSystem(queueFB,lambda,mu);
       queueManagementSystemFB.startFB(n,3);
   /*     ArrayList<lab1.Task> list=new ArrayList();
        lab1.Statistic statistic=new lab1.Statistic();
        lab1.Task task=new lab1.Task(statistic,1,2);
        list.add(new lab1.Task(statistic,1,1));
        list.add(new lab1.Task(statistic,1,1));
        list.add(new lab1.Task(statistic,1,1));
        list.add(task
        );
        System.out.println(list);
        System.out.println(task);
        list.get(3).setTimeOfExecution(45);
        list.get(3).setStopTime(24);
        if(list.remove(task)){
            System.out.println(list);
        };*/
        /*lab1.QueueFB queueFB=new lab1.QueueFB();
        queueFB.addTask(new lab1.Task(1,1));
        queueFB.addTask(new lab1.Task(2,2));
        queueFB.addTask(new lab1.Task(3,3));*/
    }
}
