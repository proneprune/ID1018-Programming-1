import java.util.Arrays;

import javax.sound.midi.Sequence;

// ArrayNumberSequence.java

/****************************************************************

ArrayNumberSequence represents a sequence of real numbers.
Such a sequence is defined by the interface NumberSequence.
The class uses an array to store the numbers in the sequence.

Author
Fadil Galjic

****************************************************************/

public class ArrayNumberSequence implements NumberSequence
{
	// numbers in the sequence
    private double[] numbers;

    // create the sequence
    public ArrayNumberSequence (double[] numbers)
    {
		if (numbers.length < 2)
		    throw new IllegalArgumentException("not a sequence");

		this.numbers = new double[numbers.length];
		for (int i = 0; i < numbers.length; i++)
		    this.numbers[i] = numbers[i];
	}

    // toString returns the character string representing this
    // sequence
	public String toString ()
	{
		String s = "";
		for (int i = 0; i < numbers.length - 1; i++)
		    s = s + numbers[i] + ", ";
		s = s + numbers[numbers.length - 1];

		return s;
	}

    // add code here

	// length returns the number of numbers in this sequence.
	public int length() {
		int length = 0;

		for (int i = 0; i < numbers.length; i++){
			length += 1;
		}


		return length;
	}

	// upperBound returns an upper bound for this sequence.
	public double upperBound() {
		
		double upperbound = numbers[numbers.length-1];
	
		return upperbound;
	}

	// lowerBound returns a lower bound for this sequence.
	public double lowerBound() {
		double lowerbound = numbers[0];
		return lowerbound;
	}

    // numberAt returns the number at the specified position
    // in this sequence.
    // The method throws IndexOutOfBoundsException if the
    // position is wrong.
	public double numberAt(int position) throws IndexOutOfBoundsException {

		double numberat = 0.0;
		if(position > numbers.length){
			throw new IndexOutOfBoundsException("position is not in the list");
		}
		for(int i = 0; i < numbers.length; i++){
			if(i == position){
				numberat += numbers[i];
			}
		}
		return numberat;
	}

	// positionOf returns the position of the first occurance of
    // the specified number in this sequence.
    // If the number is not present in the sequence, -1 is returned.
	public int positionOf(double number) {
		int positionof = -1;
		int counter = 0;
		
		for(int i = 0; i < numbers.length; i++){
			
			if(number == numbers[i]){
				positionof = counter;
			}
			counter += 1;
		}
		return positionof;
	}

	// isIncreasing returns true if this sequence is increasing,
    // else false is returned.
	public boolean isIncreasing() {
		for(int i = 1; i < numbers.length; i++){
			while(numbers[i-1] < numbers[i]){
				return true;
			}
		}
		return false;
	}

	// isDecreasing returns true if this sequence is decreasing,
    // else false is returned.
	public boolean isDecreasing() {

		for(int i = 1; i < numbers.length; i++){
			while(numbers[i-1] > numbers[i]){
				return true;
			}
		}
		return false;

	}

	// contains returns true if this sequence contains the
    // specified number, else false is returned.
	public boolean contains(double number) {

		for(int i = 0; i < numbers.length; i++){
			while(number == numbers[i]){
				return true;
			}
			
		}
		return false;
		
	}

	// add adds the specified number to the end of this sequence.
	public void add(double number) {
		double[] temp = new double[numbers.length];
		for(int i = 0; i < numbers.length; i++){
			temp[i] = numbers[i];
		}
		numbers = new double[numbers.length+1];
		for(int i = 0; i < temp.length; i++){
			numbers[i] = temp[i];
		}
		numbers[numbers.length-1] = number;
	}

	// insert inserts the given number at the specified position
    // in this sequence.
    // The method throws IndexOutOfBoundsException if the
    // position is wrong.
	public void insert(int position, double number) {
		if(position > numbers.length){ //kollar efter out of bounds
			throw new IndexOutOfBoundsException("position is out of bounds");
		}
		double[] temp = new double[numbers.length];//copies number[] in to temp[]
		for(int i = 0; i < numbers.length; i++){
			temp[i] = numbers[i];
		}

		numbers = new double[numbers.length+1];
		//fills up numbers with number in the correct position
		for(int i = 0; i < numbers.length; i++){
			if(i == position){
				numbers[i] = number;
			}
			else if(i < position){
				numbers[i] = temp[i];
			}
			else if(i > position){
				numbers[i] = temp[i-1];
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
		if(numbers.length < 2){
			throw new IllegalStateException("sequence too short");
		}
		if(position > numbers.length){
			throw new IndexOutOfBoundsException("position is not in sequence");
		}

			double[] temp = new double[numbers.length];//copies numbers[] in to temp[]
			for(int i = 0; i < numbers.length; i++){
				temp[i] = numbers[i];
			}

			numbers = new double[numbers.length-1];
		
		//inserts all number before and after the given position
		for(int i = 0; i < temp.length; i++){
			if(i < position){
				numbers[i] = temp[i];
			}
			else if(i > position){
				numbers[i-1] = temp[i];
			}
		}	
	}

	// asArray returns an array containing all of the numbers in
    // this sequence, in the same order as in the sequence.
	public double[] asArray() {
		return numbers;
	}




}