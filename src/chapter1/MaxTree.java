package chapter1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MaxTree {
	/*public static void ExValue(int[] array,int index1,int index2){
		int temp=array[index1];
		array[index1]=array[index2];
		array[index2]=temp;
	}
	
	
	
	public static void maxtree(int[] array,int treesize,int index){
		if(array==null||array.length<=1)
			return;
		int left=2*index+1;
		int right=2*index+2;
		int largest=index;
		
		if(left<treesize&&array[left]>array[index])
			largest=left;
		if(right<treesize&&array[right]>array[largest])
			largest=right;
		
		if(largest!=index){
			ExValue(array,largest,index);
			maxtree(array,treesize,largest);
		}
	}
	
	public static void bulidmax(int[] array){
		if(array==null||array.length<=1)
			return;
		int half=array.length/2;
		for(int i=half;i>=1;i--){
			maxtree(array, array.length, i);
		}
		
	}*/
	
	public static class Node{
		public int value;
		public Node left;
		public Node right;
		
		public Node(int data){
			this.value=data;
		}
	}
	
	
	public static Node getMaxTree(int[] arr){
		Node[] nArr=new Node[arr.length];
		
		for(int i=0;i<arr.length;i++){
			nArr[i]=new Node(arr[i]);
		}
		
		Stack<Node> stack=new Stack<Node>();
		HashMap<Node, Node> lBigMap=new HashMap<Node,Node>();
		HashMap<Node, Node> rBigMap=new HashMap<Node,Node>();
		
		for(int i=0;i<arr.length;i++){
			Node curNode=nArr[i];
			while((!stack.isEmpty())&&stack.peek().value<curNode.value){
				popStackSetMap(stack,lBigMap);
			}
			stack.push(curNode);
		}
		
		while(!stack.isEmpty()){
			popStackSetMap(stack,lBigMap);
		}
		
		for(int i=nArr.length-1;i!=-1;i--){
			Node curNode=nArr[i];
			while((!stack.isEmpty())&&stack.peek().value<curNode.value){
				popStackSetMap(stack,rBigMap);
			}
			stack.push(curNode);
		}
		
		while(!stack.isEmpty())
			popStackSetMap(stack,rBigMap);
		
		Node head=null;
		
		for(int i=0;i!=arr.length;i++){
			Node curNode=nArr[i];
			Node left=lBigMap.get(curNode);
			Node right=rBigMap.get(curNode);
			
			if(left==null&&right==null)
				head=curNode;
			else if(left==null){
				if(right.left==null)
					right.left=curNode;
				else
					right.right=curNode;	
			}
			else if(right==null){
				if(left.left==null)
					left.left=curNode;
				else
					left.right=curNode;
			}
			else {
				Node parent=left.value<right.value?left:right;
				if(parent.left==null)
					parent.left=curNode;
				else
					parent.right=curNode;
				
			}
				
		}
		
		return head;
	}
	
	public static void popStackSetMap(Stack<Node> stack,HashMap<Node, Node> map){
		Node popNode=stack.pop();
		if(stack.isEmpty()){
			map.put(popNode, null);
		}
		else{
			map.put(popNode,stack.peek());
		}
	}
	
	
	public static ArrayList<Integer> PrintFromTopToBottom(Node root){
		ArrayList<Integer> res=new ArrayList<Integer>();
		Queue<Node> node=new LinkedList<Node>();
//		Stack<Node> node=new Stack<Node>();
		
		if(root==null)
			return res;
		res.add(root.value);
		node.add(root);
		while(node.size()!=0){
			root=node.poll();
			if(root.left!=null){
				res.add(root.left.value);
				node.add(root.left);
			}
			if(root.right!=null){
				res.add(root.right.value);
				node.add(root.right);
			}
		}
		return res;
		
	}
	
	public static void main(String[] args){
		int[] arr={12,54,13,5,89,8,6,78,53};
		Node maxtree=getMaxTree(arr);
		ArrayList<Integer> num=PrintFromTopToBottom(maxtree);
		
		for(int i:num){
			System.out.print(i+" ");
		}
		
	}
	

}
