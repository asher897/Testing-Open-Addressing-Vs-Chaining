
public abstract class HashTable {
	protected HashEntry[] hashTable;
	protected int tableSize;
	protected double loadFactor;
	protected int occupied;
	protected int currentSize;
	protected int modCount;
	protected int probe;
	protected int inserts;
	protected int longProbe;
	protected int currentProbe;
	protected int currentInserts;

	public HashEntry[] getHashTable() {
		return this.hashTable;
	}

	public void setHashTable(HashEntry[] hashTable) {
		this.hashTable = hashTable;
	}

	public void setTableSize(int tableSize) {
		this.tableSize = tableSize;
	}

	public void setLoadFactor(double loadFactor) {
		this.loadFactor = loadFactor;
	}

	public int getOccupied() {
		return this.occupied;
	}

	public void setOccupied(int occupied) {
		this.occupied = occupied;
	}

	public int getCurrentSize() {
		return this.currentSize;
	}

	public void setCurrentSize(int currentSize) {
		this.currentSize = currentSize;
	}

	public int getModCount() {
		return this.modCount;
	}

	public void setModCount(int modCount) {
		this.modCount = modCount;
	}

	public int getProbe() {
		return this.probe;
	}

	public void setProbe(int probe) {
		this.probe = probe;
	}

	public int getInserts() {
		return this.inserts;
	}

	public void setInserts(int inserts) {
		this.inserts = inserts;
	}

	public int getLongProbe() {
		return this.longProbe;
	}

	public void setLongProbe(int longProbe) {
		this.longProbe = longProbe;
	}

	public int getCurrentProbe() {
		return this.currentProbe;
	}

	public void setCurrentProbe(int currentProbe) {
		this.currentProbe = currentProbe;
	}

	public int getCurrentInserts() {
		return this.currentInserts;
	}

	public void setCurrentInserts(int currentInserts) {
		this.currentInserts = currentInserts;
	}

	public HashTable(int tableSize) {
		this.tableSize = nextPrime(tableSize);
		this.hashTable = new HashEntry[this.tableSize];
	}

	/**
	 * Method that determine if the number passed in as a parameter is a prime
	 * number of finds the next prime number.
	 * 
	 * @param start the satrting number to find a prime number
	 * @return start if it is a prime number or the next prime number.
	 */
	public int nextPrime(int start) {
		if (start % 2 == 0) {
			start++;
		}
		for (int i = 2; i < ((start / 2) + 1); i++) {
			if (start % i == 0) {
				start++;
				i = 2;
			}
		}
		return start;
	}

	/**
	 * calculates the load factor of the current HashTable
	 * 
	 * @return a String value of the load factor by dividing the current occupation
	 *         of the HashTable by the table size.
	 */
	public String getLoadFactor() {
		double answer = this.currentSize;
		answer = answer / this.tableSize;
		String ans = Double.toString(answer);
		return ans;
	}

	/**
	 * gives the table size of the HashTable.
	 * 
	 * @return an int value of the table size.
	 */
	public int getTableSize() {
		return this.tableSize;
	}

	/**
	 * Determines if the passed in HashEntry is active or not (i.e marked deleted or
	 * not)
	 * 
	 * @param array is the current hash table
	 * @param pos   is the position of the HashEntry that is concerned.
	 * @return boolean value, if true then not deleted.
	 */
	public boolean isActive(HashEntry[] array, int pos) {
		return array[pos] != null && array[pos].isActive();
	}

	/**
	 * calculates the HashCode of a String by mulriplying each character of the
	 * string by 31 and adding it to the hash value.
	 * 
	 * @param hashData the String for which the HashCode is calculated.
	 * @return hash an int value of the HashCode.
	 */
	public int hashCode(String hashData) {
		int hash = 0;
		for (int i = 0; i < hashData.length(); i++) {
			hash = 31 * hash + hashData.charAt(i);
		}
		hash %= tableSize;
		if (hash < 0) {
			hash += tableSize;
		}
		return hash;
	}

	/**
	 * abtract method for insert to be defined in sub concrete classes.
	 */
	public abstract void insert(HashEntry data) throws Exception;

	/**
	 * abtract method for find to be defined in sub concrete classes.
	 */
	public abstract int find(String date) throws Exception;

	/**
	 * abtract method for printDateTime to be defined in sub concrete classes.
	 */
	public abstract void printDateTime(String date) throws Exception;

}
