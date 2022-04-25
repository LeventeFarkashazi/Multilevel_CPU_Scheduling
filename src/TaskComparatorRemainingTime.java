import java.util.Comparator;

public class TaskComparatorRemainingTime implements Comparator<Task> {

    public int compare(Task t1, Task t2) {
        //return Integer.compare(t1.remainingBurstTime, t2.remainingBurstTime);
        int remainingBurstTimeComparison = Integer.compare(t1.remainingBurstTime, t2.remainingBurstTime);
        return remainingBurstTimeComparison == 0 ? t1.ID.compareTo(t2.ID) : remainingBurstTimeComparison;
    }
}
