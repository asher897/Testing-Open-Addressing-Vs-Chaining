
public class Quadratic extends HashTable{

	private static final Exception TableFullException = null;
	private static final Exception NoSuchElementException = null;

	public Quadratic(int tableSize) {
		super(tableSize);
	}

	public int find(String date) throws Exception {
		int hash = hashCode(date)%tableSize;
		int current = hash;
		int i = 1;

		while(hashTable[current] != null) {
			probe++;
			if(hashTable[current].getDate().equals(date)) {
				return current;
			}
			current = (current + 2*i -1)%tableSize;
			i++;
		}
		System.out.println("Item does not exist");
		throw NoSuchElementException;
	}

	public void insert(HashEntry data) throws Exception{
		int hash = hashCode(data.getDate())%tableSize;
		int current = hash;
		int i = 1;
		if(loadFactor != 1) {
			while(hashTable[current] != null) {
				//System.out.println(probe);
				inserts++;
				if(probe>=tableSize) {
					//System.out.println("36");
					System.out.println("Probe above table size");
					return;
				}
				current = (current + 2*i -1)%tableSize;
				i++;
				if(hash>=tableSize) {
					hash = 0;
				}
			}
			hashTable[current] = data;
			currentSize++;
			//System.out.println(currentSize);
			//System.out.println("Inserted");
		}else {
			//System.out.println("49");
			System.out.println("Table is full");
			return;}



	}

	public void printDateTime(String date) throws Exception{
		HashEntry values = hashTable[find(date)];
		System.out.println("The global value is "+values.getGlobal() +" and the voltage value is " +values.getVoltage()+".");
	}

}
