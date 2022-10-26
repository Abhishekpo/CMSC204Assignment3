/**CMSC 203 Assignment 3
 * Date :10/25/2022
 * @author Abhishek Poudel
 */
 import java.util.Comparator;
import java.util.ListIterator;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> implements Iterable<T> 
{
	
	public Comparator <T> comparatorList;
	/**
	 * Creates an empty list that is associated with the specified comparator.
	 * @param compareableObject - Comparator to compare data elements
	 */
	public SortedDoubleLinkedList(Comparator<T> compareableObject)
	{
		comparatorList =compareableObject;
	}
	/**
	 * Inserts the specified element at the correct position in the sorted list. 
	 * Notice we can insert the same element several times. 
	 * Your implementation must traverse the list only once in order to perform the insertion.
	 * @param data the data to be added to the list
	 */
	public void add(T data)
	{
		Node<T>current = new Node<T>(data);
		
		if(listSize==0)
		{
			head=current;
			tail=current;
		}
		else if(comparatorList.compare((T)head.data, data)>0)
		{
			head.previous=current;
			current.next=head;
			head=current;
		}
		else if(comparatorList.compare((T)tail.data, data)<0)
		{
			tail.next=current;
			current.previous=tail;
			tail=current;
		}
		else
		{
			Node<T> nodeTemp = head;
			
			while(nodeTemp !=head)
			{
				if(comparatorList.compare((T)nodeTemp.data, data)<0 && comparatorList.compare((T)nodeTemp.next.data, data)>0)
				{
					Node<T> temp =nodeTemp.next;
					nodeTemp.next.previous=current;
					nodeTemp.next = current;
					current.next=temp;
					current.previous=nodeTemp;
					
				}
				nodeTemp= nodeTemp.next;
			}
			
		}
		listSize++; 
	}
     /**
      * This operation is invalid for a sorted list. 
      * An UnsupportedOperationException will be generated using the message 
      * "Invalid operation for sorted list."
      */
	public void addToEnd(T data) throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	/**
	 * This operation is invalid for a sorted list. 
	 * An UnsupportedOperationException will be generated using the message 
	 * "Invalid operation for sorted list."
	 */
	public void addToFront(T data) throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	/**
	 * Implements the iterator by calling the super class iterator method.
	 * @return an iterator positioned at the head of the list
	 */
	public ListIterator<T>iterator()
	{
		return super.iterator();
	}
	/**
	 * Implements the remove operation by calling the super class remove method.
	 * @return a node containing the data or null
	 */
	public BasicDoubleLinkedList.Node remove(T data, Comparator<T> comparator)
	{
		return super.remove(data, comparator);
	} 
	
}  