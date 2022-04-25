import java.util.ArrayList;

public class RRHighPrior {

    private int CurrentTaskRunTime;
    public Task runningTask = null;
    public ArrayList<Task> waitingTasks = new ArrayList<>();


    public void run(){

        //lejárt a RR időszelet
        int timeSlice = 2;
        if(CurrentTaskRunTime == timeSlice){
            waitingTasks.add(runningTask);
            runningTask = null;
            CurrentTaskRunTime = 0;
        }

        //új taszkot kell betölteni
        if(runningTask == null) {
            runningTask = waitingTasks.get(0);
            waitingTasks.remove(0);
            CurrentTaskRunTime = 0;
        }

        //várakozások
        for (Task t: waitingTasks) {
            t.waitOneTick();
        }

        runningTask.runOneTick();
        CurrentTaskRunTime++;

        //lejárt a cpu löketideje
        if (runningTask.remainingBurstTime == 0){
            runningTask = null;
            CurrentTaskRunTime = 0;
        }

    }

    public void dontRun(){
        //sor végére
        if (runningTask != null){
            waitingTasks.add(runningTask);
            runningTask = null;
            CurrentTaskRunTime = 0;
        }

        //mindenki vár
        for (Task t: waitingTasks) {
            t.waitOneTick();
        }
    }
}
