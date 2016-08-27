package problems;

import java.util.ArrayList;
import java.util.List;

public class Problem29 {

	public static void main(String[] args) {
		
		int powerNum = distinctTerms(100);
		System.out.println(powerNum);

	}
	
	public static int distinctTerms(int howMany){
		List nums = new ArrayList();
		
		for(int i = 2; i <= howMany; ++i){
			for(int j = 2; j <= howMany; ++j){
				double num = Math.pow(i, j);
				if (!nums.contains(num)){
					nums.add(num);
					
				}
			}
		}
		
		return nums.size();
	}

}

