package chapter1;

import java.util.Stack;

//import javax.print.attribute.standard.RequestingUserName;
//import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

public class HanNuo {
	
	
	
	
	public static int hanoiProblem1(int num,String left,String mid,String right){
		if(num<1)
			return 0;
		return process(num,left,mid,right,left,right);
		
	}
	
	
	public static int process(int num,String left,String mid,String right,String from,String to){
		if(num==1){
			if(from.equals(mid)||to.equals(mid)){
				System.out.println("move 1 from "+from+" to "+to);
				return 1;
			}
			else{
				System.out.println("move 1 from "+from+ " to "+mid);
				System.out.println("move 1 from "+mid+ " to "+to);
				return 2;
			}
				
		}
		if(from.equals(mid)||to.equals(mid)){
			String another=(from.equals(left)||to.equals(left))?right:left;
			int part1=process(num-1, left, mid, right, from, another);
			int part2=1;
			System.out.println("move "+num+" from "+from+ " to "+to);
			int part3=process(num-1, left, mid, right, another, to);
			return part1+part2+part3;
		}
		else{
			int part1=process(num-1, left, mid, right, from, to);
			int part2=1;
			System.out.println("move "+num+" from "+from+ " to "+mid);
			int part3=process(num-1, left, mid, right, to, from);
			int part4=1;
			System.out.println("move "+num+" from "+mid+ " to "+to);
			int part5=process(num-1, left, mid, right, from, to);
			return part1+part2+part3+part4+part5;
		}
	}
	
	
	public enum Action{
		No,LToM,MToL,MToR,RToM
	}
	
	
	public static int hanoiProblem2(int num,String left,String mid,String right){
		Stack<Integer> lS=new Stack<Integer>();
		Stack<Integer> mS=new Stack<Integer>();
		Stack<Integer> rS=new Stack<Integer>();
		lS.push(Integer.MAX_VALUE);
		mS.push(Integer.MAX_VALUE);
		rS.push(Integer.MAX_VALUE);
		
		for(int i=num;i>0;i--){
			lS.push(i);
		}
		
		Action[] record={Action.No};
		
		
		int step=0;
		while(rS.size()!=num+1){
			step+=fStackTotStack(record,Action.MToL,Action.LToM,lS,mS,left,mid);
			step+=fStackTotStack(record,Action.LToM,Action.MToL,mS,lS,mid,left);
			step+=fStackTotStack(record,Action.RToM,Action.MToR,mS,rS,mid,right);
			step+=fStackTotStack(record,Action.MToR,Action.RToM,rS,mS,right,mid);
			
		}
		return step;
		
	}
	
	public static int fStackTotStack(Action[] record,Action preNoAct,Action nowAct,Stack<Integer> fStack,
			Stack<Integer> tStack,String from,String to){
		if(record[0]!=preNoAct&&fStack.peek()<tStack.peek()){
			tStack.push(fStack.pop());
			System.out.println("Move "+tStack.peek()+" from "+from+" to "+to);
			record[0]=nowAct;
			return 1;
			
		}
		return 0;
	}
	
	public static void main(String[] args){
	hanoiProblem2(3,"left","mid","right");
		
	}
	
	
}
