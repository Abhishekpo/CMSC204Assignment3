/**CMSC 203 Assignment 3
 * Date :10/25/2022
 * @author Abhishek Poudel
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class BasicDoubleLinkedList<T> implements Iterable<T>
{
	protected int listSize;
	protected Node<T> head;
	protected Node<T> tail;
	
	/**
	 * default constructor
	 */
	BasicDoubleLinkedList()
	{
		this.listSize=0;
		head=null;
		tail= null;
	}
	/**
	 * Node inner class
	 * @author Abhishek Poudel
	 *
	 */
	class Node<T>
	{
		T data;
		Node<T> next;
		Node <T> previous;
		
		public Node(T nodeData)
		{
			this.data=nodeData;
		}
	}
	/**
	 *  DoubleLinked list inner class
	 * @author Abhishek Poudel
	 *
	 */
		public class DoubleLinkedListIterator implements ListIterator<T>
		{
			private Node currentPrevs, currentNext;
			
			/**
			 * Constructor to initialize the current pointer to the head of the BasicDoubleLinkedList.
			 */
			public DoubleLinkedListIterator()
			{
				currentNext= currentPrevs= new Node(null);
				currentNext.next=head;
				
			}
             
			@Override
			public boolean hasNext()
			{
				return currentNext.next !=null;
			}
			@Override
			public T next() throws NoSuchElementException
			{
				if(!hasNext())
				{
					throw new NoSuchElementException();
				}
				else 
				{
					currentNext=currentNext.next;
					currentPrevs.previous = currentNext;
					return (T)currentNext.data;
				}
				
			 }
			@Override
			public boolean hasPrevious()
			{
				return currentPrevs.previous !=null;
			}
			
			@Override
			public T previous() throws NoSuchElementException
			{
				if(!hasPrevious())
				{
					throw new NoSuchElementException();
				}
				else
				{
					currentPrevs= currentPrevs.previous;
					currentNext.next= currentPrevs;
					return (T)currentPrevs.data;
				}
			}
			@Override
			public int nextIndex() throws UnsupportedOperationException
			{
				throw new UnsupportedOperationException();
			}
			@Override
			public int previousIndex() throws UnsupportedOperationException
			{
				return 0;
			}
			@Override
			public void remove()throws UnsupportedOperationException
			{
				throw new UnsupportedOperationException();
			}
			@Override
			public void set(T e) throws UnsupportedOperationException
			{
				throw new UnsupportedOperationException();
			}
			@Override
			public void add(T e) throws UnsupportedOperationException
			{
				throw new UnsupportedOperationException();
			}
			
		
	}
		/**
		 * Returns the number of nodes in the list.
              Do not traverse the list to compute the size. 
              This method just returns the value of the instance variable you use to keep track of size.
		 * @return the size of the linked list
		 */
		public int getSize() 
		{
			return listSize;
		}
		/**
		 * Adds an element to the end of the list and updated the size of the list
           DO NOT use iterators to implement this method.
		 * @param data  the data to be added to the list
		 */ 
		public void addToEnd(T data) 
		{
			Node<T> current = new Node<T>(data);
			if(listSize==0) {
				head = current;
				tail= current;
			}
			else
			{
				tail.next=current;
				current.previous=tail;
				tail=current;
			}
			listSize++;
		}
		/**
		 * Adds element to the front of the list and updated the size of the list
		 * @param data  the data to be added to the list
		 */
		public void addToFront(T data)
		{
			Node<T> current = new Node<T>(data);
			if(listSize==0)
			{
				head = current;
				tail= current;
			}
			else
			{
				head.previous=current;
				current.next=head;
				head=current;
			}
			listSize++;
		}
		
		/**
		 * Returns but does not remove the first element from the list.
           If there are no elements the method returns null.
		 * @return the data element or null
		 */
		public T getFirst()
		{
			if(head==null || tail==null)
			{
				return null;
			}
			else
				return head.data;
		}
		/**
		 * Returns but does not remove the last element from the list.
           If there are no elements the method returns null.
		 * @return the data element or null
		 */
		public T getLast() 
		{
			if(head==null || tail==null)
			{
				return null;
			}
			else
				return tail.data;
		}
		/**
		 * This method returns an object of the DoubleLinkedListIterator. 
		 * (the inner class that implements java's ListIterator interface)
		 * @return a ListIterator object
		 */
		@Override
		public ListIterator<T> iterator()
		{
			return new DoubleLinkedListIterator();
		}
		/**
		 * Removes the first instance of the targetData from the list.
		 * @param targetData - the data element to be removed
		 * @param comparator - the comparator to determine equality of data elements
		 * @return a node containing the targetData or null
		 */
		public BasicDoubleLinkedList<T>.Node<T> remove(T targetData, Comparator<T> comparator)
		{
			Node<T>current= head;
			Node<T> temp = new Node(null);
		
		while(current!= null)
		{
			if(comparator.compare(targetData, current.data)==0)
			{
				temp= new Node(targetData);
				if(current ==head) 
				{
					head =head.next;
					head.previous= null;
					listSize--;
				}
				else if(current == tail)
				{
					tail=tail.previous;
					tail.next=null;
					listSize--;
				}
				else
				{
					current.previous.next=current.next;
					current.next.previous=current.previous;
					listSize --;
				}
			}
			current = current.next;
		}
		return temp;
           
}
		/**
		 * Removes and returns the first element from the list. 
		 * If there are no elements the method returns null.
		 * @return data element or null
		 */
		public T retrieveFirstElement()
		{
			if(head==null)
				return null;
			else 
			{
				T dataCurrent = head.data;
				head = head.next;
				listSize--;
				return dataCurrent;
			}
		}
		/**
		 * Removes and returns the last element from the list. 
		 * If there are no elements the method returns null
		 * @return data element or null
		 */
		public T retrieveLastElement()
		{
			if(head==null && tail == null)
			return null;
			else
			{
				T dataCurrent= tail.data;
				
				tail=tail.previous;
				tail.next=null;
				listSize--;
				return dataCurrent;
			}
		}
		/**
		 * Returns an arraylist of all the items in the Double Linked list
		 * @return an arraylist of the items in the listS
		 */
		public ArrayList<T> toArrayList()
		{
			ArrayList<T> list=new ArrayList<T>(getSize());
			Node<T> current =head;
			
			while(current !=null)
			{
				list.add(current.data);
				current= current.next;
			}
			return list;
			}
		
		
}
