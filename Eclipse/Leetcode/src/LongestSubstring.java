import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LongestSubstring {

	public static void main(String[] args) {
		String s = "pwwkew";
		char[] c = s.toCharArray();
		Map<Integer,String> res = new HashMap();
		ArrayList<Character> tempArray = new ArrayList();
		String temp = "";
		int count = 0;
		for(int i=0;i<c.length;i++) {
			if(i==c.length-1) {
				
			}else if(c[i]!=c[i+1] ) {
				temp = temp + c[i];
				tempArray.add(c[i]);
			}else if(!tempArray.contains(c[i])){
				temp = temp + c[i];
				tempArray.add(c[i]);
				res.put(temp.length(), temp);
				temp = "";
			}else if(!res.containsValue(temp)){
				res.put(temp.length(), temp);
				temp = "";
			}else{
				res.put(1, Character.toString(c[i]));
			}
		}
		System.out.println(res);
	}

}
