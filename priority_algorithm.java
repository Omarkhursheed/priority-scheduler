
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.String;
import java.util.Comparator;
import java.util.Collections;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;  

public class priority_algorithm{

	public static void main(String[] args) {
	 	ArrayList<process> processList = new ArrayList();
	 	Scanner scan = new Scanner(System.in);
		System.out.println("How many processes do you wish to enter?");
		int numberOfProcesses = scan.nextInt();
		for (int i = 0; i < numberOfProcesses; i++){
			System.out.print("Enter Process name?");
			String pName = scan.next();
			System.out.println("Enter arrival time?");
			int arrival = scan.nextInt();
			System.out.println("Enter number of CPU Bursts");
			int bursts = scan.nextInt();
			System.out.println("Enter priority");
			int prio = scan.nextInt();
			process p1 = new  process(pName, arrival, bursts, prio);
			processList.add(p1);
		}
		System.out.println("Would you like to do non-preemptive (1) or preemptive (2) scheduling?");
		int choice = scan.nextInt();
		if (choice == 1)
		{
			nonPreemptive(processList);
		}
		else if (choice == 2)
		{
			preemptive(processList);
		}

	}

	public static void nonPreemptive(ArrayList<process> processList)
	{
		String fileName = "nonPreemptiveAnswers.txt";
		try
		{
		File file1 = new File(fileName);
		FileWriter out = new FileWriter(file1);

		ArrayList<process> orderedProcessList = new ArrayList();
		int currentTime = 0;
		Collections.sort(processList, new processCompare());
		int nextProcessIndex = 0;

		currentTime = processList.get(nextProcessIndex).getPseudoArrivalTime();
		int forever = currentTime;
		String stringOut = processList.get(nextProcessIndex).getProcessName()+','+currentTime+'\n';
		out.write(stringOut);
		int originalSize = processList.size();
		processList.get(nextProcessIndex).setProcessStartTime(currentTime);
		processList.get(nextProcessIndex).setProcessEndTime(currentTime+processList.get(nextProcessIndex).getCPUBursts());

		
		currentTime += processList.get(nextProcessIndex).getCPUBursts();
		orderedProcessList.add(processList.get(nextProcessIndex));
		for(int i = 0; i < processList.size(); i++)
		{
			int count = 0;
			while(processList.get(i).getArrivalTimes() != forever && count != processList.get(nextProcessIndex).getCPUBursts())
			{
				processList.get(i).decrementArrivalTime();
				count++;
			}
		}
		processList.remove(nextProcessIndex);
		while(orderedProcessList.size() != originalSize)
		{
			Collections.sort(processList, new processCompare());
			nextProcessIndex = 0;
			if(currentTime < processList.get(nextProcessIndex).getPseudoArrivalTime())
			{
				out.write("I,"+currentTime+'\n');
				currentTime = processList.get(nextProcessIndex).getPseudoArrivalTime();
			}
			out.write(processList.get(nextProcessIndex).getProcessName()+','+currentTime+'\n');
			processList.get(nextProcessIndex).setProcessStartTime(currentTime);
			processList.get(nextProcessIndex).setProcessEndTime(currentTime+processList.get(nextProcessIndex).getCPUBursts());
			currentTime+=processList.get(nextProcessIndex).getCPUBursts();
			orderedProcessList.add(processList.get(nextProcessIndex));
			for (int i = 0; i < processList.size(); i++)
			{
				int count = 0;
				while(processList.get(i).getArrivalTimes() != forever && count != processList.get(nextProcessIndex).getCPUBursts())
				{
					processList.get(i).decrementArrivalTime();
					count++;
				}
			}
			processList.remove(nextProcessIndex);
			
		}
		out.write("Last,"+orderedProcessList.get(orderedProcessList.size()-1).getProcessEndTime()+'\n');
		System.out.println("Process Name \t Turnaround Time \t Waiting Time");
		int avgWait = 0;
		int avgTurn = 0;
		for(int i = 0; i < orderedProcessList.size(); i++)
		{
			int wait = orderedProcessList.get(i).getProcessStartTime() - orderedProcessList.get(i).getPseudoArrivalTime();
			avgWait+=wait;
			int turn = orderedProcessList.get(i).getPseudoCPUBursts() + wait;
			avgTurn+=turn;
			System.out.println(orderedProcessList.get(i).getProcessName()+'\t'+'\t'+'\t'+turn+'\t'+'\t'+'\t'+wait);
		}
		double averageW = (float)avgWait/orderedProcessList.size();
		System.out.println("Average Waiting Time = "+averageW);
		double averageT = (float)avgTurn/orderedProcessList.size();
		System.out.println("Average Turnaround Time = "+averageT);
		out.close();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		/*for(int i = 0; i < processList.size(); i++)

		{
			System.out.println(processList.get(i).getProcessName());
		}*/

	}
	public static void preemptive(ArrayList<process> processList)
	{
		String fileName = "preemptiveAnswers.txt";
		try
		{
		File file1 = new File(fileName);
		FileWriter out = new FileWriter(file1);
        //BufferedWriter out = new BufferedWriter(fstream);
		
	
		ArrayList<process> orderedProcessList = new ArrayList();
		int nextProcessIndex = 0;

		int originalSize = processList.size();
		Collections.sort(processList, new processCompare());
		nextProcessIndex = 0;
		int currentTime = processList.get(nextProcessIndex).getPseudoArrivalTime();
		int forever = currentTime;
		processList.get(nextProcessIndex).setProcessStartTime(processList.get(nextProcessIndex).getArrivalTimes());
		processList.get(nextProcessIndex).decrementCPUBursts();
		String stringOut = processList.get(nextProcessIndex).getProcessName()+','+currentTime+'\n';
		out.write(stringOut);
		/*System.out.println("SORT");
		for(int i = 0; i < processList.size();i++)
		{
			System.out.println(processList.get(i).getProcessName());
		}*/
		
		currentTime++;
		for (int i = 0; i < processList.size(); i++)
		{
			if(processList.get(i).getArrivalTimes() != forever)
			{
				processList.get(i).decrementArrivalTime();
			}
		}
		if(processList.get(nextProcessIndex).getCPUBursts() == 0)
		{
			processList.get(nextProcessIndex).setProcessEndTime(currentTime);
			orderedProcessList.add(processList.get(nextProcessIndex));
			processList.remove(nextProcessIndex);
		}
		while(orderedProcessList.size() != originalSize)
		{
			Collections.sort(processList, new processCompare());
		System.out.println("SORT");
		for(int i = 0; i < processList.size();i++)
		{
			System.out.println(processList.get(i).getProcessName());
			System.out.println(processList.get(i).getArrivalTimes());
		}
			
			
			
			nextProcessIndex = 0;
			int putfile = 0;
			if(processList.get(nextProcessIndex).getCPUBursts() == processList.get(nextProcessIndex).getPseudoCPUBursts())
			{
				System.out.println(currentTime);
				if(currentTime < processList.get(nextProcessIndex).getPseudoArrivalTime())
				{
					putfile = processList.get(nextProcessIndex).getPseudoArrivalTime()-currentTime;
					for (int i = 0; i < putfile; i++)
					{

						String x = Integer.toString(currentTime+i);
						out.write("I,"+x+'\n');
						System.out.println("HERe");		
						for (int j = 0; j < processList.size(); j++)
						{
							if(processList.get(j).getArrivalTimes() != forever)
							{
								processList.get(j).decrementArrivalTime();
							}
						}	
					}

					currentTime = processList.get(nextProcessIndex).getPseudoArrivalTime();
				}
				processList.get(nextProcessIndex).setProcessStartTime(currentTime);
			}
			out.write(processList.get(nextProcessIndex).getProcessName()+','+String.valueOf(currentTime)+'\n');
			processList.get(nextProcessIndex).decrementCPUBursts();
			currentTime++;
			for(int i = 0; i < processList.size(); i++)
			{
				if(processList.get(i).getArrivalTimes() != forever)
				{
					processList.get(i).decrementArrivalTime();
				}
			}
			if(processList.get(nextProcessIndex).getCPUBursts()==0)
			{
				processList.get(nextProcessIndex).setProcessEndTime(currentTime);
				orderedProcessList.add(processList.get(nextProcessIndex));
				processList.remove(nextProcessIndex);
			}
		}
		System.out.println("Process Name \t Turnaround Time \t Waiting Time");
		int avgWait = 0;
		int avgTurn = 0;
		for(int i = 0; i < orderedProcessList.size(); i++)
		{
			int wait = orderedProcessList.get(i).getProcessEndTime() - orderedProcessList.get(i).getPseudoArrivalTime() - orderedProcessList.get(i).getPseudoCPUBursts();
			avgWait+=wait;
			int turn = orderedProcessList.get(i).getPseudoCPUBursts() + wait;
			avgTurn+=turn;
			System.out.println(orderedProcessList.get(i).getProcessName()+'\t'+'\t'+'\t'+turn+'\t'+'\t'+'\t'+wait);
		}
		double averageW = (float)avgWait/orderedProcessList.size();
		System.out.println("Average Waiting Time = "+averageW);
		double averageT = (float)avgTurn/orderedProcessList.size();
		System.out.println("Average Turnaround Time = "+averageT);

	
		

		out.close();
	}
	catch(Exception e)
		{
			System.out.println(e);
		}
	}

}



class process
{

	int arrivalTime;
	int bursts;
	String processName;
	int processStartTime;
	int processEndTime;
	int processed;
	int currentProcessLength;
	int pseudoArrivalTime;
	int pseudoCPUBursts;
	int priority;
	process(String pname, int arrival, int cpubursts, int priorityentry)
	{
		arrivalTime = arrival;
		bursts = cpubursts;
		processName = pname;
		pseudoArrivalTime = arrival;
		pseudoCPUBursts = cpubursts;
		priority = priorityentry;
	}
	int getArrivalTimes() 
	{
		return arrivalTime;
	}
	int getCPUBursts() 
	{
		return bursts;	
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
		bursts = a;
		return 0;
	}
	int decrementCPUBursts()
	{
		bursts--;
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
		if(p1.arrivalTime > p2.arrivalTime)
		{
			return 1;
		}
		else if (p1.arrivalTime < p2.arrivalTime)
		{
			return -1;
		}
		else
		{
			if(p1.priority > p2.priority)
			{
				return 1;
			}
			else if (p1.priority < p2.priority)
			{
				return -1;
			}
			else
			{
				if(p1.pseudoArrivalTime > p2.pseudoArrivalTime)
				{
					return 1;
				}
				else if (p1.pseudoArrivalTime < p2.pseudoArrivalTime)
				{
					return -1;
				}
			}
		}
		return 0;
	}
}