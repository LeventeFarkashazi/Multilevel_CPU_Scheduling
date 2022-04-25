import java.util.ArrayList;

public class SRTFLowPrior {
    public Task runningTask = null;
    public ArrayList<Task> waitingTasks = new ArrayList<>();

    public void run(){

        //új taszkot kell betölteni
        if(runningTask == null) {
            if(waitingTasks.size()>1){
                waitingTasks.sort(new RemainingTimeComparator());
            }
            runningTask = waitingTasks.get(0);
            waitingTasks.remove(0);
        }

        //várakozások
        for (Task t: waitingTasks) {
            t.waitOneTick();
        }

        runningTask.runOneTick();

        //lejárt a cpu löketideje
        if (runningTask.remainingBurstTime == 0){
            runningTask = null;
        }
    }

    public void dontRun(){
        //vissza a sor végére, de renezev lesz
        if (runningTask != null){
            waitingTasks.add(runningTask);
            runningTask = null;
        }

        //mindenki vár
        for (Task t: waitingTasks) {
            t.waitOneTick();
        }
    }
}
