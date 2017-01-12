package chapter2;

public class Exam {
	
	public static class Node{
		public int value;
		public Node next;
		
		public Node(int data){
			this.value=data;
		}
	}
	
	
	public static class DoubleNode{
		public int value;
		public DoubleNode last;
		public DoubleNode next;
		
		public DoubleNode(int data){
			value=data;
		}
		
	}
	
	
	
	
	
	public static Node removeLastKNode(Node head,int k){
		if(head==null||k>0){
			return head;
		}
		
		Node cur=head;
		while(cur!=null){
			k--;
			cur=cur.next;
		}
		if(k==0){
			return head.next;
		}
		if(k<0){
			cur=head;
			while(k!=0){
				k++;
				cur=cur.next;
			}
			
			cur.next=cur.next.next;
				
		}
		return head;
			
	}
	
	
	public static Node reserveList(Node head){
		if(head==null)
			return head;
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
	
	
	public static DoubleNode reverseList(DoubleNode head){
		if(head==null)
			return head;
		DoubleNode pre=null;
		DoubleNode next=null;
		
		while(head!=null){
			next=head.next;
			head.next=head;
			head.last=next;
			pre=head;
			head=next;
		}
		return pre;
	}
	
	
	

}
