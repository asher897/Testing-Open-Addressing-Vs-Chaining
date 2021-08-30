
public class Linear extends HashTable {

	private static final Exception TableFullException = null;
	private static final Exception NoSuchElementException = null;

	public Linear(int tableSize) {
		super(tableSize);
	}

	/**
	 * method to find the index of a HashEntry in a Linear HashTable.
	 * 
	 * @param date is the String value of the data contained in the targeted
	 *             HashEntry.
	 * @return hash an int value of the index of the HashEntry.
	 */
	public int find(String date) throws Exception {
		this.currentProbe = 0;
		int hash = hashCode(date) % tableSize;

		while (hashTable[hash] != null) {
			currentProbe++;
			probe++;
			if (currentProbe > longProbe) {
				longProbe = currentProbe;
			}
			if (hashTable[hash].getDate().equals(date)) {
				return hash;
			}
			hash = (hash + 1) % tableSize;
		}
		System.out.println("Item does not exist");
		throw NoSuchElementException;
	}

	/**
	 * Inserts a HashEntry data item into the current Linear HashTable.
	 * 
	 * @param data the HashEntry that is being inserted.
	 */
	public void insert(HashEntry data) throws Exception {
		int hash = hashCode(data.getDate()) % tableSize;
		inserts++;
		if (loadFactor != 1) {
			while (hashTable[hash] != null) {
				inserts++;
				hash = (hash + 1) % tableSize;
			}
			hashTable[hash] = data;
			currentSize++;
			this.loadFactor += 0.1;
		} else {
			System.out.println("Table is full");
			throw TableFullException;
		}
	}

	/**
	 * locates the HashEntry containng the speicifed date and returns the global and
	 * voltage values.
	 * 
	 * @param date the specified date to search for using the find() method.
	 */
	public void printDateTime(String date) throws Exception {
		HashEntry values = hashTable[find(date)];
		System.out.println(
				"The global value is " + values.getGlobal() + " and the voltage value is " + values.getVoltage() + ".");
	}
}
