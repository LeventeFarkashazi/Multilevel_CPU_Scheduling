import java.util.Comparator;

public class TaskComparatorTimeAlphabet implements Comparator<Task> {

    public int compare(Task t1, Task t2) {
        int arrivalTimeComparison = Integer.compare(t1.arrivalTime,t2.arrivalTime);
        return arrivalTimeComparison == 0 ? t1.ID.compareTo(t2.ID) : arrivalTimeComparison;
    }
}

