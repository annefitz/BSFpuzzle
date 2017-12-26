//Anne Fitz
package application;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

import application.Solution.Node;



public class Solution {

	
	public class Node{
		private int[] value;
		private Node parent; 
		private int idx;
		private String str="";
	
		public Node(){
			
		}
		public Node(int[] value){
			this.value = value;
		}
		public int[] getValue(){
			return value;
		}
		//str_value convert array to string use for comparison function
		public void setValue(int[] value) {
			this.value = value;
		}
		//shows string value
		public String showString(){
			for (int i=0; i<value.length; i++){
				str = str + value[i];
			}
			return str;
		}
		// get/set index
		public int getIdx() {
			return idx;
		}
		public void setIdx(int idx) {
			this.idx = idx;
		}
		public void setParent(Node n){
			this.parent = n;
		}
		public Node getParent(){
			return parent;
		}
		public void generateChild(int[] arr, int mIdx, int m){
			int missing = arr[mIdx];
			int temp = arr[m];
			int[] newArray = new int[arr.length];
			for (int i=0; i<arr.length; i++){
				if (i==mIdx){
					newArray[i]=temp;
				}
				else if (i==m){
					newArray[i]=missing;
				}
				else{
					newArray[i]=arr[i];
				}
			}
			this.value = newArray;
			this.idx = m;
		}
	}

	public static void breadthFirstSearch(int[] num, int m, Vector solution)
	{
		//num : stores the combo of numbers that is not in sequential order
		//m: stores the number of the missing piece
		
		int mIdx=0;
		int[] st = {0,1,2,3,4,5,6,7,8};
		String answer = st.toString();
		Boolean found = false;
		Solution outer = new Solution();
		Node finalNode = outer.new Node();
		
		//Traverses the num[] array to find the missing piece (m) - get index of missing piece
		for (int i=0; i<num.length; i++){
			if (num[i] == m){
				mIdx = i;
			}
		}
		
		MyQueue<Node> Q = new MyQueue();
		MyQueue<Node> L = new MyQueue();
		
		Vector ch[];
		ch = new Vector[9];
		
		for(int i = 0; i < 9; i++)
		{
			ch[i] = new Vector();
		}
		
		ch[0].add(1);
		ch[0].add(3);
		ch[1].add(0);
		ch[1].add(2);
		ch[1].add(4);
		ch[2].add(1);
		ch[2].add(5);
		ch[3].add(0);
		ch[3].add(4);
		ch[3].add(6);
		ch[4].add(1);
		ch[4].add(5);
		ch[4].add(7);
		ch[4].add(3);
		ch[5].add(2);
		ch[5].add(8);
		ch[5].add(4);
		ch[6].add(3);
		ch[6].add(7);
		ch[7].add(4);
		ch[7].add(8);
		ch[7].add(6);
		ch[8].add(5);
		ch[8].add(7);
		
		Node n = outer.new Node(num);
		n.setIdx(mIdx);
		Q.enqueue(n);
		
		
		while (found == false){
			System.out.println("NEW LOOP");
			Node U = Q.dequeue();
			if (U.getValue().toString() == answer){
				found = true;
				finalNode = U;
				break;
			}
			int idx = U.getIdx();
			for (int i=0; i<ch[idx].size(); i++){
				Node child = outer.new Node();
				child.generateChild(U.getValue(), U.getIdx(), (int)ch[idx].get(i));
				child.setParent(U);
				System.out.println("child:"+child.showString());
				
				
				//if child is in the list 
				if (Q.find(child) != true){
					Q.enqueue(child);
					L.enqueue(child);
				}
				
				//else add child to queue and list
			}
		}
		
		/* 	Vector solution: output parameter
		 * 		stores the path (initially the vector is empty)
		 * 		need to assign value to the vector
		 * 		traverse back from solution to the parent, to the parent, until you get back
		 * 			to the original node/list
		 * 
		 * 	Instead of using Node.next -> makes a pointer called PARENT
		 * 
		 * 	stores the position of the of the missing piece each time
		 */
		int count = 0;
		Node temp = finalNode;
		int[] var = new int[9];
		while (temp.getParent() != null){
			var[count]=temp.getIdx();
			temp = temp.getParent();
			count++;
		}
		int[] backwards = new int[count];
		int input = 0;
		while (finalNode.getParent() != null){
			backwards[input] = var[count];
			input++;
			count--;
		}
		for (int i=0; i>=0; i++){
			solution.add(backwards[i]);
		}
	}
}
