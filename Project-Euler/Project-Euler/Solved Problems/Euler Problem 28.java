package problems;

import java.util.ArrayList;

public class Problem28 {
	
	
	public static void main(String[] args){
		int mySpiral = spiralCount(1001);
		System.out.println(mySpiral);
	}
	
	public static int spiralCount(int gridSize){
		ArrayList nums = new ArrayList();
		int sum = 0;
		
		for(int i = 0; i < gridSize; i = i + 2){
			if(i == 0){
				nums.add(1);
			}
			else{
				int lastNum = (Integer)nums.get(nums.size() - 1);
				nums.add(lastNum + i);
				nums.add(lastNum + i*2);
				nums.add(lastNum + i*3);
				nums.add(lastNum + i*4);
				
			}
			
		}
		
		for(int j = 0; j < nums.size(); ++j){
			sum += (Integer)nums.get(j);
		}
		
		return sum;
		
	}
	
	
	
	
}
