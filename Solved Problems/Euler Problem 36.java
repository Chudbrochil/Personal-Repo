package problems;

import java.util.ArrayList;

public class Problem36 {

	public static void main(String[] args){
		ArrayList nums = new ArrayList();
		
		for(int i = 1; i <= 1000000; ++i){
			boolean palinBothWays = false;
			palinBothWays = isPalin(i);
			if (palinBothWays == true){
				nums.add(i);
				System.out.println(i);
			}
			
			
		}
		
		int sum = 0;
		
		for(int i = 0; i < nums.size(); ++i){
			sum += (int)nums.get(i);
		}
		
		System.out.println(sum);
		
		
		
		
		
	}
	
	public static boolean isPalin(int decimalNum){
		
		
		StringBuilder decimalStrB = new StringBuilder(((Integer)decimalNum).toString());	
		
		if (decimalStrB.toString().equals(decimalStrB.reverse().toString())){
			// Then the decimal is a palindrome, now we have to check if it's palindrome via binary
			String binaryRep = Integer.toString(decimalNum, 2);
			StringBuilder binaryRepB = new StringBuilder(binaryRep);
			
			if (binaryRepB.toString().equals(binaryRepB.reverse().toString())){
				return true;
			}
			else{
				return false;
			}
			
		}
		else{
			return false;
		}
		
		
	}
	
	
	
	
}
