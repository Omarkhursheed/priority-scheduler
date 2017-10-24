
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.String;
import java.util.Comparator;

public class priority_algorithm{

	public static void main(String[] args) {
	 	ArrayList processList = new ArrayList();

	}
}

class process
{

	int arrivalTime;
	int CPUBursts;
	String processName;
	int processStartTime;
	int processEndTime;
	int processed;
	int currentProcessLength;
	int pseudoArrivalTime;
	int pseudoCPUBursts;
	int priority;
	process(String pname, int arrival, int CPU, int priority)
	{
		arrivalTime = arrival;
		CPUBursts = CPU;
		processName = pname;
		pseudoArrivalTime = arrival;
		pseudoCPUBursts = CPU;
		priority = priority;
	}
	int getArrivalTimes() 
	{
		return arrivalTime;
	}
	int getCPUBursts() 
	{
		return CPUBursts;	
	}
	String getProcessName() 
	{
		return processName;
	}
	int getProcessStartTime()
	{
		return processStartTime;
	}
	int getProcessEndTime()
	{
		return processEndTime;
	}
	int setProcessStartTime(int a)
	{
		processStartTime  = a;
		return 0;
	}
	int setProcessEndTime(int b)
	{
		processEndTime = b;
		return 0;
	}

	int setProcessedFlag()
	{
		processed = 1;
		return 0;
	}
	int getProcessedFlag()
	{
		return processed;
	}
	int setCurrentProcessLength(int a)
	{
		currentProcessLength = a;
		return 0;
	}
	int getCurrentProcessLength()
	{
		return currentProcessLength; 
	}
	int decrementCurrentProcessLength()
	{
		currentProcessLength--;
		return 0;
	}
	int setCPUBursts(int a)
	{
		CPUBursts = a;
		return 0;
	}
	int decrementCPUBursts()
	{
		CPUBursts--;
		return 0;
	}
	int decrementArrivalTime()
	{
		arrivalTime--;
		return 0;
	}
	int getPseudoArrivalTime()
	{
		return pseudoArrivalTime;
	}
	int getPseudoCPUBursts()
	{
		return pseudoCPUBursts;
	}
};

class processCompare implements Comparator<process>{
	public int compare(process p1, process p2){
		if(p1.arrivalTime > p2.arrivalTime){
			return 1;
		}
		else if (p1.arrivalTime < p2.arrivalTime) {
			return -1;
		}
		else{
			if(p1.priority > p2.priority){
				return 1;
			}
			else if (p1.priority < p2.priority){
				return -1;
			}
			else{
				if(p1.pseudoArrivalTime > p2.pseudoArrivalTime){
					return 1;
				}
				else if (p1.pseudoArrivalTime < p2.pseudoArrivalTime){
					return -1;
				}
			}
		}
		return 0;
	}
}