import java.util.*;

public class Chaining extends HashTable{
	private static final Exception NullPointerException = null;
	private static final Exception DuplicateItemException = null;
	private LinkedList<HashEntry>[] chainTable;


	public Chaining(int tableSize) {
		super(tableSize);
		int i = 0;
		this.tableSize = tableSize;
		chainTable = new LinkedList[this.tableSize];
		while((i < this.tableSize) && (chainTable[i] == null)) {
			chainTable[i] = new LinkedList<HashEntry>();
			i++;
		}
	}

	/**
	*Inserts a HashEntry data item into the current Chainng HashTable.
	*@param data the HashEntry that is being inserted.
	*/
	public void insert(HashEntry data) throws Exception {
		int hash = hashCode(data.getDate())%tableSize;
		chainTable[hash].add(data);
		currentSize++;
		inserts++;

	}

	/**
	*method to find the index of a HashEntry in a Chaining HashTable.
	*@param date is the String value of the data contained in the targeted HashEntry.
	*@return hash an int value of the index of the HashEntry.
	*/
	public int find(String date) throws Exception {
		this.currentProbe = 0;
		int hash = hashCode(date)%tableSize;
		ListIterator searchList = chainTable[hash].listIterator(0);
		currentProbe++;
		probe++;
		HashEntry current;
		int index = 0;
		while(searchList.hasNext()){
			probe++;
			currentProbe++;
			if(currentProbe>longProbe){
				longProbe = currentProbe;
			}
			current = (HashEntry)searchList.next();
			if(current.getDate().equals(date)){
				index = chainTable[hash].indexOf(current);
				break;
			}
		}
		return index;

	}

	/**
	*locates the HashEntry containng the speicifed date and returns the global and voltage values.
	*@param date the specified date to search for using the find() method.
	*/
	public void printDateTime(String date) throws Exception{
		HashEntry values = chainTable[hashCode(date)%this.tableSize].get(find(date));
		System.out.println("The global value is "+values.getGlobal() +" and the voltage value is " +values.getVoltage()+".");
	}



}
