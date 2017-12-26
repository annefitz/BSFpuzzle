//Anne Fitz
package application;

import java.util.EmptyStackException;

public class MyQueue<T> {

	private T[] arr; //used to store data into this array in a queue manner

	private int total; //the total number of elements in the queue
	private int first; //the location of the first element in the queue
	private int rear; //the location of the next available element (last one's next)

	//Default constructor, by default the capacity is two elements of type T 
	public MyQueue()
    {
        arr = (T[]) new Object[2];
    }

	//Resize the MyQueue to the capacity as the input argument specifies
    private void resize(int capacity)
    {
        T[] tmp = (T[]) new Object[capacity];

        for (int i = 0; i < total; i++)
            tmp[i] = arr[(first + i) % arr.length];

        arr = tmp;
        first = 0;
        rear = total;
    }
    
    //Checks if the queue is empty: if empty, returns true; otherwise returns false
    public boolean isEmpty()
    {
  	    if(total == 0)
	    	return true;
	    else
	    	return false;
    }

    //Adds one element "ele" into the queue
    //			 (1) if the current queue is full, resizes it to twice of the current size.
    //           (2) if the "rear" is already pointing to the end of the queue, but there is available space
    	//               in the beginning, "loops" the rear position.
    public void enqueue(T ele)
    {
    	if((rear + 1) % arr.length == 0){
    		resize(arr.length * 2);
    	}
    	
    	if(isEmpty()){
    		first++;
    		rear++;
    		arr[rear] = ele;
    		total++;
    		}
    	else{
    		rear =  total % arr.length;
    		arr[rear] = ele;
    		total++;
    	}
    	if(first != 0 && rear == arr.length)
    		rear = (rear +1 ) % arr.length;    	
    }

    
    //Deletes the first (oldest) element from the queue and return this element.
    //				 (1) if the current number of elements is less than or equal to 1/4 of the
    	// 			     the capacity, shrinks the capacity to 1/2 (half) of the original size.
    	//			 (2) If the "first" is pointing to the end of the queue, but there is available space
    //				 in the beginning, consider "loop" the first position.
    public T dequeue()
    {	
    	
    	if(total <= arr.length / 4){
    		resize(arr.length / 2);
    	}
    	if(isEmpty()){
    		throw new EmptyStackException();}
    	if(total <= arr.length / 4){
    		resize(arr.length / 2);
    		T ele = arr[first];
    		arr[first] = null;
    		first = (first + 1) % arr.length;
    		total--;
    		return ele;}
    	
    	else{
    		T ele = arr[first];
    		first = (first + 1) % arr.length;
    		total--;
    		return ele;
    	}
    }
 
    public String toString()
    {
        return java.util.Arrays.toString(arr);
    }
    
    public Boolean find(T n){
    	for (int i=0; i<total; i++){
    		if (arr[i]==n){
    			return true;
    		}
    	}
    	return false;
    }
}

