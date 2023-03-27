package ResourceRequestAlgorithm;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int[][] allocation = new int[5][3];
		int[][] max = new int[5][3];
		int[] avilable = new int[3];
		int[][] need = new int[5][3];
		boolean[] finish = new boolean[5];
		String safe = "";
		
		for(int i = 0; i<allocation.length; i++)
		{
			for(int j = 0; j<allocation[i].length; j++)
			{
				if(j == 0)
				{
					//getting allocation value
					System.out.print("Enter P-"+i+" allocation value for A:");
					allocation[i][j] =  input.nextInt();
					
					//getting max value
					System.out.print("Enter P-"+i+" Max value for A:");
					max[i][j] =  input.nextInt();
					
					//calculating mid value
					need[i][j] = max[i][j] - allocation[i][j];
				}
				
				if(j == 1)
				{
					//getting allocation value
					System.out.print("Enter P-"+i+" allocation value for B:");
					allocation[i][j] =  input.nextInt();
					
					//getting max value
					System.out.print("Enter P-"+i+" Max value for B:");
					max[i][j] =  input.nextInt();
					
					//calculating mid value
					need[i][j] = max[i][j] - allocation[i][j];
				}
				
				if(j == 2)
				{
					
					//getting allocation value
					System.out.print("Enter P-"+i+" allocation value for C:");
					allocation[i][j] =  input.nextInt();
					
					//getting max value
					System.out.print("Enter P-"+i+" Max value for C:");
					max[i][j] =  input.nextInt();
					
					//calculating mid value
					need[i][j] = max[i][j] - allocation[i][j];
				}
				
			}
		}
		
		System.out.println("--------------------------------------------------------");
		
		
		//getting available value
		for(int i = 0; i<3; i++)
		{
			if(i == 0)
			{
				System.out.print("Enter Available value for A:");	
				avilable[i] = input.nextInt();
			}
			
			if(i == 1)
			{
				System.out.print("Enter Available value for B:");	
				avilable[i] = input.nextInt();
			}
			
			if(i == 2)
			{
				System.out.print("Enter Available value for C:");	
				avilable[i] = input.nextInt();
			}
			
		}
		
		
		
		System.out.println("--------------------------Request a process----------------------");
		System.out.print("Enter req process index:");
		int processIndex = input.nextInt();
		int request[] = new int[3];
		
		for(int i = 0; i<3; i++)
		{
			if(i == 0) {
				System.out.print("Enter value for A:");
				request[i] = input.nextInt();
			}
			
			if(i == 1) {
				System.out.print("Enter value for B:");
				request[i] = input.nextInt();
			}
			
			if(i == 2) {
				System.out.print("Enter value for C:");
				request[i] = input.nextInt();
			}
		}
		
		
		
		if(checkRequest(request,need[processIndex]))
		{
			if(checkRequest(request,avilable))
			{
				for(int i = 0; i<avilable.length; i++)
				{
					avilable[i] =avilable[i]- request[i];
					allocation[processIndex][i] = allocation[processIndex][i]+request[i];
					need[processIndex][i] = need[processIndex][i]-request[i];
				}
			}
		}
		
		System.out.println("--------------------request process end-----------------------");
		
		
		
		System.out.println("--------------------updated value-----------------------");
		
		for(int i = 0; i<5; i++)
		{
			System.out.println("P"+i+": "+Arrays.toString(allocation[i])+"\t"+Arrays.toString(need[i]));
		}
		
		System.out.println("Avaialable value:"+Arrays.toString(avilable));
		
		System.out.println("-------------------------end------------------------------");
		
		
		
		int index = 0;
		int count = 0;
		
		while(!checkFinish(finish))
		{
			if(index<allocation.length) {
				if(finish[index] == false && check(avilable,need[index]))
				{
					finish[index] = true;
					
					for(int i = 0; i<avilable.length; i++)
					{
						avilable[i] += allocation[index][i];
					}
					
					count++;
					if(count == 5)
						safe+= "P"+index;
					else
						safe+= "P"+index+"--->";
				}
				
				index++;
			}else {
				index = 0;
			}
		}
		
		
		System.out.println("----------------------------------------------------------------------");
		System.out.println(safe);
		
		input.close();
		
	}
	
	
	//check avaiable or not
	public static boolean check(int[] avaiable, int[] need)
	{
		boolean flag = false;
		
		for(int i = 0; i<avaiable.length; i++)
		{
			if(need[i]<= avaiable[i]) {
				flag = true;
			}else {
				return false;
			}
		}
		
		return flag;
	}
	
	
	//check finish
	public static boolean checkFinish(boolean[] finish)
	{
		int count = 0;
		
		for(int i = 0; i<finish.length; i++)
		{
			if(finish[i])
				count++;
		}
		
		if(count == finish.length)
			return true;
		
		return false;
	}
	
	
	
	//check request
	public static boolean checkRequest(int req[], int need[])
	{
	   boolean flag = false;
	   
	   for(int i = 0; i<req.length; i++)
	   {
		   if(req[i]<=need[i])
		   {
			   flag = true;
		   }else {
			   return false;
		   }
	   }
	   
	   return flag;
	}
	
}