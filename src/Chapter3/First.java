package Chapter3;

import java.sql.SQLNonTransientConnectionException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.locks.ReadWriteLock;

public class First {
	public static class Node{
		public int value;
		public Node left;
		public Node right;
		
		public Node(int data){
			value=data;
		}
	}
	
	public static void preOrderRecur(Node head){
		if(head==null)
			return;
		System.out.print(head.value+" ");
		preOrderRecur(head.left);
		preOrderRecur(head.right);
	}
	
	public static void inOrderRecur(Node head){
		if(head==null)
			return;
		inOrderRecur(head.left);
		System.out.print(head.value+" ");
		inOrderRecur(head.right);
	}
	
	
	public static void posOrderRecur(Node head){
		if(head==null)
			return;
		posOrderRecur(head.left);
		posOrderRecur(head.right);
		System.out.print(head.value+" ");
	}
	
	
	public static void preOrderUnRecur(Node head){
		if(head==null)
			return;
		Stack<Node> stack=new Stack<Node>();
		stack.push(head);
		Node cur=null;
		while(!stack.isEmpty()){
			cur=stack.pop();
			System.out.print(cur.value+" ");
			if(cur.right!=null) stack.push(cur.right);
			if(cur.left!=null) stack.push(cur.left);
			
		}
		
	}
	
	public static void inOrderUnRecur(Node head){
		if(head==null)
			return;
		Node cur=head;
		Node node=null;
		Stack<Node> stack =new Stack<Node>();
		while(!stack.isEmpty()||cur!=null){
			if(cur!=null){
				stack.push(cur);
				cur=cur.left;
			}
			else{
				node=stack.pop();
				System.out.print(node.value+" ");
				cur=node.right	;
			}
		}
		
	}
	
	
	public static void posOrderUnRecur(Node head){
		if(head==null)
			return;
		Stack<Node> stack1=new Stack<Node>();
		Stack<Node> stack2=new Stack<Node>();
		Node cur=head;
		stack1.push(cur);
		while(!stack1.isEmpty()){
			
			cur=stack1.pop();
			stack2.push(cur);
			if(cur.left!=null)stack1.push(cur.left);
			if(cur.right!=null)stack1.push(cur.right);
		}
		while(!stack2.isEmpty()){
			System.out.print(stack2.pop().value+" ");
		}	
	}
	
	public static void printEdge1(Node head){
		if(head==null)
			return;
		int height=getHeight(head,0);
		Node[][] edgeMap=new Node[height][2];
		setEdgeMap(head,0,edgeMap);
		for(int i=0;i!=edgeMap.length;i++){
			System.out.print(edgeMap[i][0].value+" ");
		}
		
		printLeafNotInMap(head,0,edgeMap);
		
		for(int i=edgeMap.length-1;i!=-1;i--){
			if(edgeMap[i][0]!=edgeMap[i][1])
				System.out.print(edgeMap[i][1].value+" ");
		}
		System.out.println();
	}
	
	
	public static int getHeight(Node head,int l){
		if(head==null)
			return l;
		return Math.max(getHeight(head.left, l+1), getHeight(head.right, l+1));
		
	}
	
	public static void setEdgeMap(Node h,int l,Node[][] edgeMap){
		if(h==null)
			return;
		edgeMap[l][0]=edgeMap[l][0]==null?h:edgeMap[l][0];
		edgeMap[l][l]=h;
		setEdgeMap(h.left, l+1, edgeMap);
		setEdgeMap(h.right, l+1, edgeMap);
	}
	
	
	public static void printLeafNotInMap(Node h,int l,Node[][] m){
		if(h==null)
			return;
		if(h.left==null&&h.right==null&&h!=m[l][0]&&h!=m[l][l])
			System.out.print(h.value+" ");
		printLeafNotInMap(h.left, l+1, m);
		printLeafNotInMap(h.right, l+1, m);
	}
	
	
	public static void printTree(Node head){
		System.out.println("Binary Tree:");
		printInOrder(head,0,"H",17);
		System.out.println();
		
	}
	
	public static void printInOrder(Node head,int height,String to,int len){
		if(head==null)
			return;
		printInOrder(head.right, height+1, "v", len);
		String val=to+head.value+to;
		int lenM=val.length();
		int lenL=(len-lenM)/2;
		int lenR=len-lenM-lenL;
		val=getSpace(lenL)+val+getSpace(lenR);
		System.out.println(getSpace(height*len)+val);
		printInOrder(head.left, height+1, "^", len);
				}
	
	public static String getSpace(int num){
		String space=" ";
		StringBuffer buffer=new StringBuffer("");
		for(int i=0;i<num;i++){
			buffer.append(space);
		}
		return buffer.toString();
		
	}
	
	
	public static String serialByPre(Node head){
		if(head==null)
			return "#!";
		String res=head.value+"!";
		res+= serialByPre(head.left);
		res += serialByPre(head.right);
		return res;
	}
	
	
	public static String serialByIn(Node head){
		if(head==null)
			return "#!";
		String res="";
		res += serialByIn(head.left);
		res += head.value+"!";
		res += serialByIn(head.right);
		return res;
	}
	
	
	public static Node reconPreOrder(Queue<String> queue){
		String value=queue.poll();
		if(value.equals("#"))
			return null;
		Node head=new Node(Integer.valueOf(value));
		head.left=reconPreOrder(queue);
		head.right=reconPreOrder(queue);
		return head;
	}
	
	public static Node reconByPreString(String preStr){
		String[] value=preStr.split("!");
		Queue<String> queue=new LinkedList<String>();
		for(int i=0;i<value.length;i++){
			queue.offer(value[i]);
		}
		
		return reconPreOrder(queue);
		
		
	}
	
	
	
	public static String serialByLevel(Node head){
		if(head==null)
			return "#!";
		
		String res=head.value+"!";
		Queue<Node> queue =new 	LinkedList<>();
		queue.offer(head);
		while(!queue.isEmpty()){
			head=queue.poll();
			if(head.left!=null){
				res+=head.left.value+"!";
				queue.offer(head.left);
			}
			else{
				res+="#!";
			}
			if(head.right!=null){
				res+=head.right.value+"!";
				queue.offer(head.right);
			}
			else{
				res+="#!";
			}
			
		}
		
		return res;
		
		
	}
	
	public static Node reconByLevelString(String levelStr){
		String[] values=levelStr.split("!");
		int index=0;
		Node head=generateNodeByString(values[index++]);
		Queue<Node> queue=new LinkedList<>();
		if(head!=null)
			queue.offer(head);
		
		Node node=null;
		while(!queue.isEmpty()){
			node =queue.poll();
			node.left=generateNodeByString(values[index++]);
			node.right=generateNodeByString(values[index++]);
			if(node.left!=null)
				queue.offer(node.left);
			if(node.right!=null)
				queue.offer(node.right);
		}
		
		return head;
	}
	
	
	public static Node generateNodeByString(String val){
		if(val.equals("#"))
			return null;
		return new Node(Integer.valueOf(val));
	}
	
	
	public static void morrisIn(Node head){
		if(head==null)
			return;
		Node cur1=head;
		Node cur2=null;
		while(cur1!=null){
			cur2=cur1.left;
			if(cur2!=null){
				while(cur2.right!=null&&cur2.right!=cur1){
					cur2=cur2.right;
				}
				if(cur2.right==null){
					cur2.right=cur1;
					cur1=cur1.left;
					continue;
				}
				else{
					cur2.right=null;
				}
				
			}
			System.out.print(cur1.value+" ");
			cur1=cur1.right;
		}
		
		System.out.println();
		
	}
	
	
	
	public static void morrisPre(Node head){
		if(head==null)
			return;
		Node cur1=head;
		Node cur2=null;
		while(cur1!=null){
			cur2=cur1.left;
			if(cur2!=null){
				while(cur2.right!=null&&cur2.right!=cur1){
					cur2=cur2.right;
				}
				if(cur2.right==null){
					cur2.right=cur1;
					System.out.print(cur1.value+" ");
					cur1=cur1.left;
					continue;
				}
				else{
					cur2.right=null;
				}
				
			}else{
				System.out.print(cur1.value+" ");
			}
		
			cur1=cur1.right;
		}
		
		System.out.println();
		
	}
	
	public static void morrisPos(Node head){
		if(head==null)
			return;
		Node cur1=head;
		Node cur2=null;
		while(cur1!=null){
			cur2=cur1.left;
			if(cur2!=null){
				while(cur2.right!=null&&cur2.right!=cur1){
					cur2=cur2.right;
				}
				if(cur2.right==null){
					cur2.right=cur1;
					cur1=cur1.left;
					continue;
				}
				else{
					cur2.right=null;
					printEdge(cur1.left);
					
				}
				
			}
		
			cur1=cur1.right;
		}
		printEdge(head);
		
		System.out.println();
		
	}
	
	public static Node reverseEdge(Node from){
		Node pre=null;
		Node next=null;
		while(from!=null){
			next=from.right;
			from.right=pre;
			pre=from;
			from=next;
		}
		return pre;
	}
	
	public static void printEdge(Node head){
		Node tail=reverseEdge(head);
		Node cur=tail;
		while(cur!=null){
			System.out.print(cur.value+" ");
			cur=cur.right;
		}
		reverseEdge(tail);
	}
	
	
	//δ���������������ۼӺ�Ϊ����ֵ��������鳤��
	public static int getMaxLength(int[] arr,int k){
		if(arr==null||arr.length==0||k<=0)
			return 0;
		int left=0;
		int right=0;
		int sum=arr[0];
		int len=0;
		while(right<arr.length){
			if(sum==k){
				len=Math.max(len, right-left+1);
				sum=sum-arr[left];
			}
			else if(sum<k){
				right++;
				if(right==arr.length)
					break;
				sum+=arr[right];		
			}
			else{
				sum-=arr[left++];
			}
			
			
		}
		return len;
	}
	
	
	public static Node posOrder(Node head,int[] record){
		if(head==null){
			record[0]=0;
			record[1]=Integer.MAX_VALUE;
			record[2]=Integer.MIN_VALUE;
			return null;
		}
		
		int value=head.value;
		Node left=head.left;
		Node right=head.right;
		Node lBST=posOrder(left,record);
		int lSize=record[0];
		int lMin=record[1];
		int lMax=record[2];
		Node rBST=posOrder(right, record);
		int rSize=record[0];
		int rMin=record[1];
		int rMax=record[2];
		record[1]=Math.min(lMin, value);
		record[2]=Math.max(rMax, value);
		if(left==lBST&&right==rBST&&lMax<value&&value>rMin){
			record[0]=lSize+rSize+1;
			return head;
		}
		record[0]=Math.max(lSize,rSize);
		return lSize>rSize?lBST:rBST;
	}
	
	
	public static Node biggestSubBST(Node head){
		int[] record=new int[3];
		return posOrder(head, record);
	}
	
	
	
	
	
	public static void main(String[] args){
//		Node head=new Node(1);
//		head.left=new Node(2);
//		head.right=new Node(3);
//		head.left.left=new Node(4);
//		head.left.right=new Node(5);
//		head.right.left=new Node(6);
//		head.right.right=new Node(7);
		
		
		
		Node head=new Node(1);
		head.left=new Node(2);
		head.right=new Node(3);
		head.left.right=new Node(4);
		head.right.left=new Node(5);
		head.right.right=new Node(6);
		head.left.right.left=new Node(7);
		head.left.right.right=new Node(8);
		head.right.left.left=new Node(9);
		head.right.left.right=new Node(10);
		head.left.right.right.right=new Node(11);
		head.right.left.left.left=new Node(12);
		head.left.right.right.right.left=new Node(13);
		head.left.right.right.right.right=new Node(14);
		head.right.left.left.left.left=new Node(15);
		head.right.left.left.left.right=new Node(16);
		
		
		
		
		//preOrderUnRecur(head);
		//printEdge1(head);
//		printTree(head);
		inOrderRecur(head);
		System.out.println();
//		String string=serialByLevel(head);
//		System.out.println(string);
//		
//		Node head1=reconByLevelString(string);
		
//		preOrderRecur(head1);
		morrisIn(head);
		
		posOrderRecur(head);
		System.out.println();
		morrisPos(head);
		
		
	}
	
	

}