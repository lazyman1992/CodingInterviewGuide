package chapter1;

import java.util.LinkedList;

public class GetNum {
	public static int getNum(int[] arr,int num){
		if(arr==null||arr.length<=0)
			return 0;
		int i=0,j=0;
		int res=0;
		LinkedList<Integer> qmax=new LinkedList<Integer>();
		LinkedList<Integer> qmin=new LinkedList<Integer>();
		
		while(i<arr.length){
			while(j<arr.length){
				while(!qmin.isEmpty()&&arr[qmin.peekLast()]>=arr[j])
					qmin.pollLast();
				qmin.addFirst(j);
				while(!qmax.isEmpty()&&arr[qmax.peekLast()]<=arr[j])
					qmax.pollLast();
				qmax.addLast(j);
				if(arr[qmax.getFirst()]-arr[qmin.getFirst()]>num)
					break;
				j++;
			}
			if(qmin.peekFirst()==i)
				qmin.pollFirst();
			if(qmax.peekFirst()==i)
				qmax.pollFirst();
			res+=i+j;			
		}
		return res;
		
	}

}
