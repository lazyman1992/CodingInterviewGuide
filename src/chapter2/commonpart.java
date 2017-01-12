package chapter2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.swing.tree.AbstractLayoutCache.NodeDimensions;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

public class commonpart {
	
	
	public  static class Node{
		public int vaule;
		public Node next;
		
		public Node(int data){
			vaule=data;
		}
		
		public void add(int n){
			next.vaule=n;
		}
		
	}
	
	public static void printNode(Node head){
		Node cur=head;
		while(cur!=null){
			System.out.print(cur.vaule+" ");
			cur=cur.next;
		}
	}
	
	
	public void printCommonPart(Node head1,Node head2){
		while(head1!=null&&head2!=null){
			if(head1.vaule<head2.vaule)
				head1=head1.next;
			else if(head1.vaule>head2.vaule)
				head2=head2.next;
			else{
				System.out.print(head1.vaule+" ");
				head1=head1.next;
				head2=head2.next;
			}
			
		}
	}
	
	
	public  Node removeLastKthNode(Node head,int k){
		if(head==null||k<1)
			return head;
		Node temp=head;
		while(temp!=null){
			k--;
			temp=temp.next;
		}
		if(k==0)
			return head.next;
		if(k<0){
			temp=head;
			while(k<0){
				temp=temp.next;
				k++;
			}
			temp.next=temp.next.next;
		}
		return head;
				
	} 
	
	public class DoubleNode{
		public int value;
		public DoubleNode next;
		public DoubleNode last;
		
		public DoubleNode(int data){
			value=data;
		}
	}
	
	public DoubleNode removeLastKthNode(DoubleNode head,int k){
		if(head==null||k<1){
			return head;
		}
		DoubleNode cur=head; 
		while(cur!=null){
			cur=cur.next;
			k--;
		}
		if(k==0){
			head.next.last=null;
			return head.next;
		}
		if(k<0){
			cur=head;
			while(k<0){
				cur=cur.next;
				k++;
			}
			DoubleNode newnext=cur.next.next;
			cur.next=newnext;
			if(newnext!=null)
				newnext=cur;
			
		}
		return head;		
	}
	
	
	public Node removeMidNode(Node head){
		if(head==null||head.next==null){
			return head;
		}
		if(head.next.next==null)
			return head.next;
		
		Node pre=head;
		Node cur=head.next.next;
		while(cur.next!=null&&cur.next.next!=null){
			pre=pre.next;
			cur=cur.next.next;
			
		}
		pre.next=pre.next.next;
		
		return head;
	}
	
	public Node removeByRatio(Node head,int a,int b){
		if(head==null||head.next==null)
			return head;
		
		int n=0;
		Node cur=head;
		while(cur!=null){
			n++;
			cur=cur.next;
		}
		
		n=(int)Math.ceil(((double)(a*n))/(double)b);
		if(n==1)
			return head.next;
		if(n>1){
			cur=head;
			while(--n != 1)
				cur=cur.next;
			cur.next=cur.next.next;
		}
		return head;
		
	}
	
	public static Node reverseList(Node head){
		Node pre=null;
		Node next=null;
		while(head!=null){
			next=head.next;
			head.next=pre;
			pre=head;
			head=next;
		}
		return pre;
	}
	
	public Node reservePart(Node head,int from,int to){
		int len=0;
		Node node1=head;
		Node fpre=null;
		Node tpos=null;
		while(node1!=null){
			len++;
			fpre=len==from-1?node1:fpre;
			tpos=len==to+1?node1:tpos;
			node1=node1.next;
		}
		if(from>to||from<1||to>len){
			return head;
		}
		node1=fpre==null?head:fpre.next;
		Node node2=node1.next;
		node1.next=tpos;
		Node next=null;
		while(node2!=tpos){
			next=node2.next;
			node2.next=node1;
			node1=node2;
			node2=next;
		}
		if(fpre!=null){
			fpre.next=node1;
			return head;
		}
		
		return node1;
	}
	
	
	
	public Node josephusKilll(Node head,int m){
		if(head==null||head.next==head||m<1)
			return head;
		Node last=head;
		while(last.next!=head)
			last=last.next;
		int count=0;
		while(head!=last){
			if(++count==m){
				last.next=head.next;
				count=0;
			}
			else{
				last=last.next;
			}
			head=last.next;
		}
		return head;
			
	}
	
	public  Node josephusKill2(Node head,int m){
		if(head==null||head.next==head||m<1){
			return head;
		}
		Node cur=head.next;
		int tmp=1;
		while(cur!=head){
			tmp++;
			cur=cur.next;;
		}
		tmp=getLive(tmp,m);
		while(--tmp!=0){
			head=head.next;
		}
		head.next=head;
		return head;
		
	}
	
	public int getLive(int i,int m){
		if(i==1)return 1;
		return (getLive(i-1, m)+m-1)%i+1;
		
	}
	
	public  boolean isPalindrome1(Node head){
		Stack<Node> stack=new Stack<Node>();
		if(head==null||head.next==head)
			return false;
		Node cur=head;
		while(cur!=null){
			stack.push(cur);
			cur=cur.next;
		}
		while(head!=null){
			if(head.vaule!=stack.pop().vaule)
				return false;
			head=head.next;
				
		}
		return true;
		
	}
	
	
	public boolean isPALINDROMEL(Node head){
		if(head==null||head.next==null)
			return false;
		Node cur=head;
		Stack<Node> stack =new Stack<Node>();
	
		//取得链表的右半部分
		/*int n=0;
		while(cur!=null){
			n++;
			cur=cur.next;
		}
		int start;
		if(n%2==0)
			start=n/2+1;
		else
			start=n/2+2;
		Node right=head;
		while(--start!=0){
			right=right.next;
		}*/
		
		
		//取得链表的右半部分
		Node right=head.next;
		while(cur.next!=null&&cur.next.next!=null){
			right=right.next;
			cur=cur.next.next;
		}
		
		
		
		while(right!=null){
			stack.push(right);
			right=right.next;
		}
		while(!stack.isEmpty()){
			if(head.vaule!=stack.pop().vaule)
				return false;
			head=head.next;
		}
		return true;
		
	}
	
	
	
	public static void swap(Node[] nodeArr,int a,int b){
		Node tmp=nodeArr[a];
		nodeArr[a]=nodeArr[b];
		nodeArr[b]=tmp;
	}
	
	public static void arrPartition(Node[] arr,int pivot){
		int small=-1;
		int big=arr.length;
		int index=0;
		while(index!=big){
			if(arr[index].vaule<pivot)
				swap(arr, ++small, index++);
			else if(arr[index].vaule==pivot)
				index++;
			else{
				swap(arr, --big, index++);
			}
		}
	}
	
	
	public static Node listPartition1(Node head,int pivot){
		Node cur=head;
		int n=0;
		while(cur!=null){
			n++;
			cur=cur.next;
		}
		cur=head;
		Node[] arr=new Node[n];
		int i;
		for( i=0;i<arr.length;i++){
			arr[i]=cur;
			cur=cur.next;
		}
		
		arrPartition(arr, pivot);
		
		for( i=1;i<arr.length;i++){
			arr[i-1].next=arr[i];
		}
		
		arr[i-1].next=null;
		
		
		
		return arr[0];
			
	}
	
	public static Node listPartition2(Node head,int pivot){
		Node sH=null;
		Node sT=null;
		Node eH=null;
		Node eT=null;
		Node bH=null;
		Node bT=null;
		Node next=null;
		
		while(head!=null){
			next=head.next;
			head.next=null;
			if(head.vaule<pivot){
				if(sH==null){
				sH=head;
				sT=head;
				}
				else {
					sT.next=head;
					sT=head;
				}				
			}
			else if(head.vaule==pivot){
				if(eH==null){
					eH=head;
					eT=head;
				}
				else{
					eH.next=head;
					eT=head;
				}
			}
			else{
				if(bH==null){
					bH=head;
					bT=head;
				}
				else{
					bH.next=head;
					bT=head;
				}
			}
			head=next;
		}
		
		
		if(sT!=null){
			sT.next=eH;
			eT=eT==null?sT:eT;
		}
		if(eT!=null){
			eT.next=bH;
		}
		
		return sH!=null?sH:(eH!=null?eH:bH);
		
		
	}
	
	public static class RandNode{
		public int value;
		public RandNode next;
		public RandNode rand;
		
		public RandNode(int data){
			value=data;
		}
		
	}
	
	
	public static RandNode copyListWithRand1(RandNode head){
		HashMap<RandNode, RandNode> map=new HashMap<RandNode,RandNode>();
		RandNode cur=head;
		while(cur!=null){
			map.put(cur, new RandNode(cur.value));
			cur=cur.next;
		}
		cur=head;
		while(cur!=head){
			map.get(cur).next=map.get(cur.next);
			map.get(cur).rand=map.get(cur.rand);
			cur=cur.next;
		}
		
		return map.get(head);
		
	}
	
	
	public static RandNode copyListWithRand2(RandNode head){
		if(head==null)
			return head;
		RandNode cur=head;
		RandNode next=null;
		while(cur!=null){
			next=cur;
			cur.next=new RandNode(cur.value);
			cur.next.next=next.next;
			cur=next.next;
		}
		cur =head;
		RandNode curCopy=null;
		while(cur!=null){
			next=cur.next.next;
			curCopy=cur.next;
			curCopy.rand=cur.rand!=null?cur.rand.next:null;
			cur=next;
		}
		
		RandNode res=head.next;
		cur=head;
		while(cur!=null){
			next=cur.next.next;
			curCopy=cur.next;
			cur.next=next;
			curCopy.next=next!=null?next.next:null;
			cur=next;
			
		}
		return res;

	}
	
	
	
	public static Node addList(Node head1,Node head2){
		if(head1==null&&head2!=null)
			return head2;
		if(head1!=null&&head2==null)
			return head1;
		Node cur1=head1;
		Node cur2=head2;
		Stack<Integer> stack1=new Stack<Integer>();
		Stack<Integer> stack2=new Stack<Integer>();
		while(cur1!=null){
			stack1.push(cur1.vaule);
			cur1=cur1.next;
		}
		while(cur2!=null){
			stack2.push(cur2.vaule);
			cur2=cur2.next;
		}
//		Node head3=null;
//		int ca=0;
//		while(!stack1.isEmpty()||!stack2.isEmpty()){
//			int temp;
//			temp=(stack1.isEmpty()?0:stack1.pop().vaule)+(stack2.isEmpty()?0:stack2.pop().vaule)+ca;
//			if(temp>=10){
//				ca=1;
//				head3=new Node(temp%10);
//			}
//			else{
//				head3=new Node(temp);
//				ca=0;
//			}
//			
//			head3.next=head3;					
//		}
		
		
		int ca=0;
		int n1=0;
		int n2=0;
		int n=0;
		
		Node node=null;
		Node pre=null;
		while(!stack1.isEmpty()||!stack2.isEmpty()){
			n1=stack1.isEmpty()?0:stack1.pop();
			n2=stack2.isEmpty()?0:stack2.pop();
			n=n1+n2+ca;
			pre=node;
			node=new Node(n%10);
			node.next=pre;
			ca=n/10;
		}
		if(ca==1){
			pre=node;
			node=new Node(1);
			node.next=pre;
		}		
		return node;
		
	}
	
	
	public static Node addList2(Node head1,Node head2){
		head1=reverseList(head1);
		head2=reverseList(head2);
		int ca=0;
		int n1=0;
		int n2=0;
		int n;
		
		Node c1=head1;
		Node c2=head2;
		Node pre=null;
		Node node=null;
		while(c1!=null||c2!=null){
			n1=c1!=null?c1.vaule:0;
			n2=c2!=null?c2.vaule:0;
			n=n1+n2+ca;
			pre=node;
			node=new Node(n%10);
			node.next=pre;
			ca=n/10;		
			c1=c1!=null?c1.next:null;
			c2=c2!=null?c2.next:null;
		}
		if(ca==1){
			pre=node;
			node =new Node(1);
			node.next=pre;
		}
		
		reverseList(head1);
		reverseList(head2);
		
		
		return node;
	}
	
	
	public static Node reserveKNode1(Node head,int K){
		if(K<2)
			return head;
		Node cur=head;
		Node start=null;
		Node pre=null;
		Node next=null;
		int count=1;
		while(cur!=null){
			next=cur.next;
			if(count==K){
				start=pre==null?head:pre.next;
				head=pre==null?cur:head;
				resign2(pre, start, cur, next);
				pre=start;
				count=0;
			}
			count++;
			cur=next;
		}
		
		return head;
		
	}
	
	public static void resign2(Node left,Node start,Node end,Node right){
		Node pre=start;
		Node cur=start.next;
		Node next=null;
		while(cur!=right){
			next=cur.next;
			cur.next=pre;
			pre=cur;
			cur=next;
		}
		
		if(left!=null){
			left.next=end;
		}
		
		start.next=right;
		
		
	}
	
	
	public static Node reverseKNode2(Node head,int K){
		if(K<2)
			return head;
		Stack<Node> stack=new Stack<Node>();
		Node newHead=head;
		Node cur=head;
		Node pre=null;
		Node next=null;
        while(cur!=null){
        	next=cur.next;
        	stack.push(cur);
        	if(stack.size()==K){
        		pre=resign2(stack, pre,next);
        		newHead=newHead==head?cur:newHead;
        	}
        	cur=next;
        	
        }
		
        return newHead;
		}
	
	
	public static void removeRep1(Node head){
		if(head==null)
			return ;
		HashSet<Integer> set=new HashSet<Integer>();
		set.add(head.vaule);
		Node pre=head;
		Node cur=head.next;
		while(cur!=null){
			if(set.contains(cur.vaule)){
				pre.next=cur.next;
			}
			else{
				set.add(cur.vaule);
				pre=cur;
			}
			cur=cur.next;			
		}
	}
	
	
	public static void removeRep2(Node head){
		if(head==null)
			return;
		Node pre=null;
		Node cur=head;
		Node next=null;
		while(cur!=null){
			pre=cur;
			next=cur.next;
			while(next!=null){
			if(cur.vaule==next.vaule)
				pre.next=next.next;
			else
				pre=next;
			
			next=next.next;
			}	
			cur=cur.next;
		}		
	}
		
	

	
	public static Node resign2(Stack<Node> stack,Node left,Node right){
		Node cur=stack.pop();
		if(left!=null)
			left.next=cur;
		Node next=null;
		while(!stack.isEmpty()){
			next=stack.pop();
			cur.next=next;
			cur=next;
		}
		cur.next=right;
		return cur;
	}
	
	
	
	public static Node removeValue1(Node head,int num){
		if(head==null)
			return head;
		Stack<Node> stack=new Stack<Node>();

		while(head!=null){
			if(head.vaule!=num)
				stack.push(head);
			head=head.next;
		}
		
		while(!stack.isEmpty()){
			stack.peek().next=head;
			head=stack.pop();
		}
		
		return head;		
	}
	
	public static Node removeValue2(Node head,int num){
		if(head==null)
			return head;
		
		Node cur=head;
		Node pre=null;
		while(cur!=null){
			if(cur.vaule==num)
				pre.next=cur.next;
			pre=cur;
			cur=cur.next;			
		}		
		return head;	
	}
	
	public static class TwoNode{
		public int value;
		public TwoNode left;
		public TwoNode right;
		
		public TwoNode(int data){
			value=data;
		}
	}
	
	public static TwoNode convert1(TwoNode head){
		Queue<TwoNode> queue =new LinkedList<TwoNode>();
		inOrderToQueue(head,queue);
		if(queue.isEmpty()){
			return head;
		}
		 head=queue.poll();
		 TwoNode pre=head;
		 pre.left=null;
		 TwoNode cur=null;
		 while(!queue.isEmpty()){
			 cur=queue.poll();
			 pre.right=cur;
			 cur.left=pre;
			 pre=cur;
		 }
		 pre.right=null;
		 return head;
	}
	
	
	public static void inOrderToQueue(TwoNode head,Queue<TwoNode> queue){
		if(head==null)
			return ;
		inOrderToQueue(head.left,queue);
		queue.offer(head);
		inOrderToQueue(head.right, queue);	
	}
	
	public static Node selectionSort(Node head){
		if(head==null)
			return head;
		Node tail=null;
		Node cur=head;
		Node smallPre=null;
		Node small=null;
		while(cur!=null){
			small=cur;
			smallPre=getSmallestPreNode(cur);
			if(smallPre!=null){
				small=smallPre.next;
				smallPre.next=small.next;
			}
			cur=cur==small?cur.next:cur;
			if(tail==null){
				head=small;
			}
			else{
				tail.next=small;
			}
			tail=small;
		}
		return head;
		
	}
	
	
	public static Node getSmallestPreNode(Node head){
		Node smallPre=null;
		Node small=head;
		Node pre=head;
		Node cur=head.next;
		while(cur!=null){
			if(cur.vaule<small.vaule){
				smallPre=pre;
				small=cur;
			}
			pre=cur;
			cur=cur.next;
		}
		return smallPre;
		}
	
	
	public static Node insertNum(Node head,int num){
		Node node=new Node(num);
		if(head==null){
			node.next=node;
			return node;	
		}
			
		Node pre=head;
		Node cur=head.next;
		while(cur!=head){
			if(node.vaule>=pre.vaule&&node.vaule<=cur.vaule){
				break;
			}
			pre=cur;
			cur=cur.next;
				
		}
		pre.next=node;
		node.next=cur;
		return head.vaule<num?head:node;
	}
	
	
	public static Node merge(Node head1,Node head2){
		Node head=head1.vaule<head2.vaule?head1:head2;
		
		Node cur1=head1;
		Node cur2=head2;
		Node pre=head;
		Node small=null;
		while(cur1!=null&&cur2!=null){
			if(cur1.vaule<cur2.vaule){
				small=cur1;
				cur1=cur1.next;
			}
			else{
				small=cur2;
				cur2=cur2.next;
			}
			pre.next=small;
			pre=small;
		}
		pre.next=cur1==null?cur2:cur1;
		return head;
	}
	
	
	
	public static void relocate(Node head){
		if(head==null||head.next==null){
			return ;
		}
		Node mid=head;
		Node right=head.next;
		while(right.next!=null&&right.next.next!=null){
			mid=mid.next;
			right=right.next.next;
		}
		right=mid.next;
		mid.next=null;
		mergeLR(head,right);
	}
	
	public static void mergeLR(Node left,Node right){
		Node next=null;
		while(left.next!=null){
			next=right.next;
			right.next=left.next;
			left.next=right;
			left=right.next;
			right=next;
		}
		left.next=right;
	}
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args){
		
		Node head= new Node(1);
		head.next=new Node(3);
		head.next.next=new Node(5);
		head.next.next.next=new Node(7);
		head.next.next.next.next=new Node(9);
		head.next.next.next.next.next=new Node(11);
		printNode(head);
		System.out.println();
		
		Node head1=new Node(2);
		head1.next=new Node(4);
		head1.next.next=new Node(6);
	//	printNode(head1);
		//System.out.println();
		

//	head=merge(head1, head);
//		relocate1(head);
		relocate(head);

		printNode(head);
			
	}
	
	

}
