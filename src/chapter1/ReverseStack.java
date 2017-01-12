package chapter1;

import java.awt.image.RescaleOp;
import java.util.Stack;

public class ReverseStack {
	
	public static int getlastandremove(Stack<Integer> stack){
		int result=stack.pop();
		if(stack.isEmpty())
			return result;
		else {
			int last=getlastandremove(stack);
			stack.push(result);
			return last;
			
		}
	}
	
	public static void reverse(Stack<Integer> stack){
		if(stack.isEmpty())
			return;
		int i=getlastandremove(stack);
		reverse(stack);
		stack.push(i);
	}
	
	
	public static void main(String[] args)
	{
		Stack<Integer> stack=new Stack<Integer>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		System.out.println(stack.peek()); 
		reverse(stack);
		System.out.println(stack.peek());
		
	}

}
