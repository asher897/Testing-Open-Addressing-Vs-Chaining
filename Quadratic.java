
public class Quadratic extends HashTable {

	private static final Exception TableFullException = null;
	private static final Exception NoSuchElementException = null;

	public Quadratic(int tableSize) {
		super(tableSize);
	}

	/**
	 * method to find the index of a HashEntry in a Quadratic HashTable.
	 * 
	 * @param date is the String value of the data contained in the targeted
	 *             HashEntry.
	 * @return hash an int value of the index of the HashEntry.
	 */
	public int find(String date) throws Exception {
		this.currentProbe = 0;
		int hash = hashCode(date) % tableSize;
		int current = hash;
		int i = 1;

		while (hashTable[current] != null) {
			probe++;
			currentProbe++;
			if (currentProbe > longProbe) {
				longProbe = currentProbe;
			}
			if (hashTable[current].getDate().equals(date)) {
				return current;
			}
			current = (current + (i << 1) - 1) % tableSize;
			i++;
		}
		System.out.println("Item does not exist");
		throw NoSuchElementException;
	}

	/**
	 * Inserts a HashEntry data item into the current Quadratic HashTable.
	 * 
	 * @param data the HashEntry that is being inserted.
	 */
	public void insert(HashEntry data) throws Exception {
		int hash = hashCode(data.getDate()) % tableSize;
		int current = hash;
		int i = 1;
		inserts++;
		this.currentInserts = 0;
		this.currentInserts++;
		if (loadFactor != 1) {
			while (hashTable[current] != null) {
				inserts++;
				this.currentInserts++;
				if (this.currentInserts >= tableSize) {
					System.out.println("Probe above table size");
					return;
				}
				current = (current + (i << 1) - 1) % tableSize;
				i++;
				if (hash >= tableSize) {
					hash = 0;
				}
			}
			hashTable[current] = data;
			currentSize++;
		} else {
			System.out.println("Table is full");
			return;
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
