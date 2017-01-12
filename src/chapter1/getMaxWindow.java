package chapter1;

import java.lang.reflect.Array;
import java.util.LinkedList;

public class getMaxWindow {
	public static int[] GetMaxWindow(int[] arr,int w){
		int n=arr.length;
		int[] result=new int[n-w+1];
		for(int i=0;i<n-w+1;i++ )
		{
			for(int j=i;j<i+w;j++)
				if(arr[j]>result[i])
					result[i]=arr[j];
		}
		return result;
		
	}
	
	public static int[] getmaxwindow(int[] arr,int w){
		if(arr==null||w<1||arr.length<w)
			return null;
		LinkedList<Integer> qmax=new LinkedList<Integer>();
		int[] res=new int[arr.length-w+1];
		int index=0;
		for(int i=0;i<arr.length;i++){
			while(!qmax.isEmpty()&&arr[qmax.peekLast()]<=arr[i]){
				qmax.pollLast();
			}
			qmax.addLast(i);
			if(qmax.peekFirst()==i-w)
				qmax.pollFirst();
			if(i>=w-1)
				 res[index++]=arr[qmax.peekFirst()];
		}
		return res;
	}
	
	
	public static void main(String[] args)
	{
		int[] arr=new int[8];
		arr=new int[]{4,3,5,4,3,3,6,7};
		
		int[] res=GetMaxWindow(arr,3);
		for(int e:res)
		System.out.println(e);
		
		
	}
	

}
