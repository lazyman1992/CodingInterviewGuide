package chapter1;
import java.util.*;

public class TwoStackQueen {
	public static class MyQueue{
		public Stack<Integer> stackPush;
		public Stack<Integer> stackPop;
		
		
		
		public MyQueue(){
			this.stackPush=new Stack<Integer>();
			this.stackPop=new Stack<Integer>();
		}
		
		public void add(int item){
			stackPush.push(item);
			
		}
		
		public int poll(){
			if(stackPop.empty()&&stackPush.empty())
				System.out.println("queue is empty");
			else if(stackPop.empty()){
				while(!stackPush.empty())
					stackPop.push(stackPush.pop());
			}
			return stackPop.pop();
			
		}
		
		public int peek(){
			if(stackPop.empty()&&stackPush.empty())
				System.out.println("Queue is empty");
			else if(stackPop.empty()){
				while(!stackPush.empty())
					stackPop.push(stackPush.pop());
			}
			return stackPop.peek();
		}	
	}
	
	public static void main(String[] args){
		MyQueue queue=new MyQueue();
		queue.add(1);
		queue.add(2);
		queue.add(3);
		queue.add(4);
		queue.add(5);
		queue.poll();
		System.out.println(queue.poll()); 
		System.out.println();
		System.out.println(queue.peek());
		System.out.println("ri le gou");
	}

}
