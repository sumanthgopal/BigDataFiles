import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ThreeSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {-1, 0, 1, 2, -1, -4};
		List<List<Integer>> major = new ArrayList();
        for(int i=0;i<nums.length;i++){
            for(int j=i+1; j<nums.length;j++){
                for(int k=j+1; k<nums.length;k++){
                    List<Integer> minor = new ArrayList();
                    if(nums[i]+nums[k]+nums[j] == 0){
                        minor.add(nums[i]);
                        minor.add(nums[j]);
                        minor.add(nums[k]);
                        major.add(minor);
                    }
                }
            }
        }
	}

}
