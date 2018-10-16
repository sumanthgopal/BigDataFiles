import java.text.DecimalFormat;

import java.math.double;

public class AddTwoNumbers {

	public static void addTwoNumbers(ListNode l1,ListNode l2) {
		        double resl1 = 0;
		        double resl2 = 0;
		        double tens = 1;
		        while(l1!=null){
		            int val = l1.val;
		            resl1 = val*tens + resl1;
		            tens = tens*10;
		            l1 = l1.next;
		        }
		        tens = 1;
		        while(l2!=null){
		            int val = l2.val;
		            if(tens > 1000000000){
		                break;
		            }
		            resl2 = val*tens + resl2;
		            tens = tens*10;
		            l2 = l2.next;
		        }
		        System.out.println(resl2);
		        double res = resl1 + resl2;
		        Math.pow(a, b)
		        ListNode result = new ListNode(0);
		        ListNode pointer = result;
		        if(res==0){
		           //return pointer; 
		        }
		        String a =  new DecimalFormat("#").format(100.0);
		        char[] b = a.toCharArray();
		        b[]
		        while(res > 0){
		            result.next = new ListNode((int)(res % 10));
		            result = result.next;
		            res = res/10;
		        }
		        //return pointer.next;
		    
    }
	
	public static void main(String[] args) {
		ListNode l1 = new ListNode(9);
		ListNode l2 = new ListNode(1);
		l2.next = new ListNode(9);
		l2.next.next = new ListNode(9);
		l2.next.next.next = new ListNode(9);
		l2.next.next.next.next = new ListNode(9);
		l2.next.next.next.next.next = new ListNode(9);
		l2.next.next.next.next.next.next = new ListNode(9);
		l2.next.next.next.next.next.next.next = new ListNode(9);
		l2.next.next.next.next.next.next.next.next = new ListNode(9);
		l2.next.next.next.next.next.next.next.next.next = new ListNode(9);
		addTwoNumbers(l1,l2);
	}

}
