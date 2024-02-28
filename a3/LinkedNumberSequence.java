import java.util.Arrays;

import javax.sound.midi.Sequence;

// LinkedNumberSequence.java

/****************************************************************

LinkedNumberSequence represents a sequence of real numbers.
Such a sequence is defined by the interface NumberSequence.
The class uses linked nodes to store the numbers in the sequence.

Author
Fadil Galjic

****************************************************************/

public class LinkedNumberSequence implements NumberSequence
{
	private class Node
	{
		public double number;
		public Node next;

		public Node (double number)
		{
			this.number = number;
			next = null;
		}
	}

	// the first node in the node-sequence
    private Node first;

    // create the sequence
    public LinkedNumberSequence (double[] numbers)
    {
		if (numbers.length < 2)
		    throw new IllegalArgumentException("not a sequence");

        first = new Node(numbers[0]);
        Node n = first;
		for (int i = 1; i < numbers.length; i++)
		{
			n.next = new Node(numbers[i]);
			n = n.next;
		}
	}

    // toString returns the character string representing this
    // sequence
	public String toString ()
	{
		String s = "";
		Node n = first;
		while (n.next != null)
		{
		    s = s + n.number + ", ";
		    n = n.next;
		}
		s = s + n.number;

		return s;
	}


    // add code here
	// length returns the number of numbers in this sequence.
	public int length() {
		int numberOfElements = 0;
		Node n = first;
		while (n != null)
		{
			numberOfElements++;
			n = n.next;
	    }
		

		return numberOfElements;
	}

	// upperBound returns an upper bound for this sequence.
	public double upperBound() {
		
		Node n = first;

		while(n != null){
			n = n.next;
			if(n.next == null){

				return n.number;
			}
		}


	
	
		return 0;
	}

	// lowerBound returns a lower bound for this sequence.
	public double lowerBound() {
		
		return first.number;
	}

	// numberAt returns the number at the specified position
    // in this sequence.
    // The method throws IndexOutOfBoundsException if the
    // position is wrong.
	public double numberAt(int position) throws IndexOutOfBoundsException {
		Node n = first;
		int counter = 0;
		

		while(n != null){
			n = n.next;
			counter++;
			if(n == null){
				throw new IndexOutOfBoundsException("position out of bounds");
			}
			else if( counter == position){
				return n.number;
			}
			
		}
		return 0;
	}

	// positionOf returns the position of the first occurance of
    // the specified number in this sequence.
    // If the number is not present in the sequence, -1 is returned.
	public int positionOf(double number) {
		Node n = first;
		int counter = 0;
		while(n != null){
			n = n.next;
			counter++;
			if(n.number == number){
				return counter;
			}
			else if(n.next == null){
				return -1;
			}
			
		}
		return -1;
	}

	// isIncreasing returns true if this sequence is increasing,
    // else false is returned.
	public boolean isIncreasing() {
		Node n = first;
		while(n != null){
			n = n.next;

			if(n.number < n.next.number){
				return true;
			}
			else
				return false;
		}



		return false;
	}

	// isDecreasing returns true if this sequence is decreasing,
    // else false is returned.
	public boolean isDecreasing() {
		Node n = first;
		while(n != null){
			n = n.next;

			if(n.number > n.next.number){
				return true;
			}
			else
				return false;
		}

		return false;
	}

	// contains returns true if this sequence contains the
    // specified number, else false is returned.
	public boolean contains(double number) {
		Node n = first;
		while(n != null){
			n = n.next;

			if(n.number == number){
				return true;
			}
			else if(n.next == null){
				return false;
			}
		}



		return false;
	}

	// add adds the specified number to the end of this sequence.
	public void add(double number) {
		Node new_node = new Node(number);
		new_node.next = null; //creates new node with specified double

		Node n = first;

		while(n.next != null){//runs through the nodes to the second to last
			n = n.next;
		}
		n.next = new_node;//replaces the last node "null node" with the new node

	}

	// insert inserts the given number at the specified position
    // in this sequence.
    // The method throws IndexOutOfBoundsException if the
    // position is wrong.
	public void insert(int position, double number) {
		Node new_node = new Node(number);
		Node n = first;
		int counter = 1; //counter starts at 1 since it replaces the previous node and not the one to come

		while(n != null){
			n = n.next;
			counter++;

			if( n == null && counter < position){
				throw new IndexOutOfBoundsException("position is out of bounds");
			}
			else if(counter == position){
				new_node.next = n.next;//gives new_nodes.next the location of n.next
				n.next = new_node;//replaces n.next with the location of new_node
			}
		}
	}

	// removeAt removes the number at the specified position
    // in this sequence.
    // The method throws IndexOutOfBoundsException if the
    // position is wrong.
    // The method throws IllegalStateException if there are
    // just two numbers in the sequence.
	public void removeAt(int position) throws IndexOutOfBoundsException {
		Node n = first;
		int counter = 1;
		while(n != null){
			n = n.next;
			counter++;
			if(counter == 1 && n.next == null){
				throw new IllegalStateException("too few nodes");
			}
			else if(counter == position){
				n.next = n.next.next;
			}
			else if( n == null && counter < position){
				throw new IndexOutOfBoundsException("position is out of bounds");
			}
		}
	}

	// asArray returns an array containing all of the numbers in
    // this sequence, in the same order as in the sequence.
	public double[] asArray() {

		Node n = first;
		double[] asarray = new double[8];
		int i = 0;

		while(n != null){
		asarray[i] = n.number;
		i++;
		n = n.next;
		}


		return asarray;
		
	
	}





}