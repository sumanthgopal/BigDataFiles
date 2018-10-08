import java.util.HashMap;
import java.util.Map;

public class TwoSum {
	public static void main(String args[]) {
		int nums[] = {1,5,7};
		int target = 12;
		/*for(int i=0;i<nums.length;i++){
	           for(int j=i+1;j<nums.length;j++){
	               if(nums[i]+nums[j] == target){
	            	   int a[] = new int[2];
	            	   a[0] = i;
	            	   a[1] = j;
	            	   System.out.println(a[0]);
	            	   System.out.println(a[1]);
	               }
	           } 
	        }*/
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		for(int i=0;i<nums.length;i++) {
			int c = target-nums[i];
			if(map.containsKey(c)) {
				int a[] = {map.get(c),i};
				System.out.println(a[0]);
				System.out.println(a[1]);
			}
			map.put(nums[i],i);
		}
	}
}
