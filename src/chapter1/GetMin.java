package chapter1;
import java.util.*;


public class GetMin {
	public static class MyStack1{
		private Stack<Integer> stackData;
		private Stack<Integer> stackMin;
		
		public MyStack1(){
			stackData=new Stack<Integer>();
			stackMin=new Stack<Integer>();
		}
		
		public void push(int newNum){
			if(stackMin.isEmpty()){
				stackMin.push(newNum);
			}else if(newNum<=this.getmin()){
				stackMin.push(newNum);
			}
			stackData.push(newNum);
		}
		
		public int pop(){
			if(stackData.isEmpty())
				System.out.println("Your stack is empty");
			int value=stackData.pop();
			if(value==this.getmin())
				stackMin.pop();
			return value;
		}
		
		
		public int getmin(){
			if(stackMin.isEmpty())
				System.out.println("Your stack is empty");
			
			return stackMin.peek();
			
		}
	}

	public static class MyStack2{
		private Stack<Integer> stackData;
		private Stack<Integer> stackMin;
		
		public MyStack2(){
			 this.stackData=new Stack<Integer>();
			 this.stackMin=new Stack<Integer>();
		}
		
		public void push(int newNum){
			if(stackData.isEmpty())
				stackMin.push(newNum);
			if(newNum<=this.getmin())
				stackMin.push(newNum);
			else 
				stackMin.push(this.getmin());
			stackData.push(newNum);
				
		}
		
		public int pop(){
			if(stackData.isEmpty())
				System.out.println("Your stack is empty");
			int value=stackData.pop();
			stackMin.pop();
			return value;
			
		}
		
		public int getmin(){
			if(stackMin.isEmpty())
				System.out.println("Your stack is empty");
			
			return stackMin.peek();
			
		}
		
	}
	
	
	
	
	
	
	
	public static void main(String[] args)
	{
		MyStack2 stack1=new MyStack2();
		stack1.push(2);
		stack1.push(3);
		stack1.push(5);
		stack1.push(1);
		stack1.push(6);
		System.out.println(stack1.getmin());
	}
	
	
}
