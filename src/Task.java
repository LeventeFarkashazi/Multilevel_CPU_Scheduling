public class Task{
	
	public String ID;
	public boolean isHighPrior;
	public int arrivalTime;
	public int remainingBurstTime;
	public int timeWaited;

	//constructor
	public Task(String taskID, int taskPrior, int taskArrivalTime, int taskBurstTime) {
		ID = taskID;
		isHighPrior = taskPrior == 1;
		arrivalTime = taskArrivalTime;
		remainingBurstTime = taskBurstTime;
		timeWaited = 0;
	}

	//wait for 1 tick
	public void waitOneTick() {
		timeWaited++;
	}

	//run for 1 tick
	public void runOneTick() {
		remainingBurstTime--;
		if(Main.result.equals(""))
			Main.result+=ID;
		if(!(Main.result.substring(Main.result.length() - 1).equals(ID)))
			Main.result+=ID;
	}
}
