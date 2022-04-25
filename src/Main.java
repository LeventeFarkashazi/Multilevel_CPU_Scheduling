import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static String result = "";

    public static void main(String[] args) {

        RRHighPrior rr = new RRHighPrior();
        SRTFLowPrior srtfl = new SRTFLowPrior();

        ArrayList<Task> tasksByArrivalTime = new ArrayList<Task>();
        ArrayList<Task> tasksByAlphabetic = new ArrayList<Task>();

        int ticks = 0;

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (!line.isEmpty()) {
                String[] taskArgs = line.split(",");
                Task task = new Task(taskArgs[0], Integer.parseInt(taskArgs[1]), Integer.parseInt(taskArgs[2]), Integer.parseInt(taskArgs[3]));
                tasksByArrivalTime.add(task);
                tasksByAlphabetic.add(task);
            }
        }
        tasksByArrivalTime.sort(new TaskComparatorTimeAlphabet());

        //start golbal
        boolean exit = false;

        while (!exit) {
            if (tasksByArrivalTime.size() > 0) {
                for (int i = 0; i < tasksByArrivalTime.size(); i++) {
                    Task t = tasksByArrivalTime.get(i);
                    if ((t.arrivalTime +1 == ticks) && t.isHighPrior) {
                        tasksByArrivalTime.remove(t);
                        i--;
                        rr.waitingTasks.add(t);
                    } else if (t.arrivalTime +1 == ticks) {
                        tasksByArrivalTime.remove(t);
                        i--;
                        srtfl.waitingTasks.add(t);
                        if (srtfl.runningTask != null){
                            srtfl.waitingTasks.add(srtfl.runningTask);
                            srtfl.runningTask = null;
                        }

                    }
                }
            }

            boolean runLock = false;

            if((!rr.waitingTasks.isEmpty()) || (rr.runningTask != null)){
                runLock = true;
                rr.run();
                srtfl.dontRun();
            }

            if(((!srtfl.waitingTasks.isEmpty()) || (srtfl.runningTask != null)) && !runLock){
                runLock = true;
                srtfl.run();
                rr.dontRun();
            }

            if(!runLock && tasksByArrivalTime.isEmpty()){
                exit = true;
            }

            ticks++;
        }

        System.out.println(result);

        System.out.print(tasksByAlphabetic.get(0).ID+":"+tasksByAlphabetic.get(0).timeWaited);
        for (int i = 1; i < tasksByAlphabetic.size(); i++) {
            Task t = tasksByAlphabetic.get(i);
            System.out.print(","+t.ID+":"+t.timeWaited);
        }
    }
}

