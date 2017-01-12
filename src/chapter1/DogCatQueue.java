package chapter1;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;



public  class DogCatQueue {
	public static class Pet{
		private String type;
		
		public Pet(String type){
			this.type=type;
		}
		
		public String getPetType(){
			return this.type;
		}
	}
	
	
	public static class  Dog extends Pet{
		public Dog(){
			super("Dog");
		}
	}
	
	public class Cat extends Pet{
		public Cat(){
			super("Cat");
		}
	}
	
	
	public static class PetEnterQueue{
		private Pet pet;
		private long count;
		
		public PetEnterQueue(Pet pet,long count){
			this.pet=pet;
			this.count=count;
		}
		
		public Pet getPet(){
			return this.pet;
		}
		
		public long getCount(){
			return this.count;
		}
		
		public String getPetEnterType(){
			return this.pet.getPetType();
		}	
		
	}
	
	
	public static class MyDogCatQueue{
		private Queue<PetEnterQueue> dogQ;
		private Queue<PetEnterQueue> catQ;
		private long count;
		
		
		public MyDogCatQueue(){
			this.dogQ=new LinkedList<PetEnterQueue>();
			this.catQ=new LinkedList<PetEnterQueue>();
		}
		
		public void add(Pet pet){
			if(pet.getPetType().equals("dog"))
				this.dogQ.add(new PetEnterQueue(pet, this.count++));
			else if(pet.getPetType().equals("cat"))
				this.dogQ.add(new PetEnterQueue(pet, this.count++));
			else 
				throw new RuntimeException("err,not dog or cat");
		}
		
		public Pet pollAll(){
			if(!this.dogQ.isEmpty()&&!this.catQ.isEmpty()){
				if(this.dogQ.peek().getCount()<this.catQ.peek().getCount())
					return this.dogQ.poll().getPet();
				else
					return this.catQ.poll().getPet();
				
			}
			else if(!this.dogQ.isEmpty())
				return this.dogQ.poll().getPet();
			else if(!this.catQ.isEmpty())
				return this.catQ.poll().getPet();
			else {
				throw new RuntimeException("err queue is empty");
			}
				
			
		}
		
		
		public Dog pollDog(){
			if(!this.isDogQueueEmpty())
				return (Dog) this.dogQ.poll().getPet();	
			else
				throw new RuntimeException("cat queue is empty");
		
		}
		
		
		public Cat pollCat(){
			if(!this.isCatQueueEmpty())
				return (Cat) this.catQ.poll().getPet();
			else
				throw new RuntimeException("dog queue is empty");
		}
		
		public boolean isEmpty(){
			return this.dogQ.isEmpty()&&this.catQ.isEmpty();
		}
		
		public boolean isDogQueueEmpty(){
			return this.dogQ.isEmpty();
		}
		
		public boolean isCatQueueEmpty(){
			return this.catQ.isEmpty();
		}
		
		
	}
	
	
	public static void sortStackByStack(Stack <Integer> stack){
		Stack<Integer> help=new Stack<Integer>();
		
		while(!stack.isEmpty()){
			int cur=stack.pop();
			while(!help.isEmpty()&&help.peek()<cur)
			{
				stack.push(help.pop());
			}
			help.push(cur);
		}
		
		while(!help.isEmpty())
			stack.push(help.pop());
		
	}
	
	
	
	public static void main(String[] args)
	{
		Stack<Integer> stack=new Stack<Integer>();
		
		stack.push(2);
		stack.push(1);
		stack.push(4);
		stack.push(5);
		stack.push(3);
		
		System.out.println(stack.peek());
		sortStackByStack(stack);
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		
	}

}
