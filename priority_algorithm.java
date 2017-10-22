
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.String;

public class priority_algorithm{

	public static void main(String[] args) {
		
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
	process(String pname, int arrival, int CPU)
	{
		arrivalTime = arrival;
		CPUBursts = CPU;
		processName = pname;
		pseudoArrivalTime = arrival;
		pseudoCPUBursts = CPU;
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
