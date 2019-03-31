
public class Quadratic extends HashTable{

	private static final Exception TableFullException = null;
	private static final Exception NoSuchElementException = null;

	public Quadratic(int tableSize) {
		super(tableSize);
	}

	public int find(String date) throws Exception {
		int hash = date.hashCode()%tableSize;
		int current = hash;
		int i = 1;

		while(hashTable[current] != null) {
			current = Math.floorMod((current + i<<1 -1),tableSize);
			i++;
			if(hashTable[current].getDate().equals(date)) {
				return current;
			}
		}
		System.out.println("Item does not exist");
		throw NoSuchElementException;
	}

	public void insert(HashEntry data) throws Exception{
		probe = 0;
		int hash = hashCode(data.getDate())%tableSize;
		int current = find(data.getDate());
		int i = 1;
		if(hashTable[current].isActive()) {
			System.out.println("Already exists");
			return;
		}
		if(loadFactor != 1) {
			current = hash;
			while(hashTable[hash] != null) {
				probe++;
				if(probe>=tableSize) {
					System.out.println("Table is full");
					throw TableFullException;
				}
				current = Math.floorMod((current + i<<1 -1),tableSize);
				i++;
				if(hash>=tableSize) {
					hash = 0;
				}
			}
			hashTable[hash] = data;
			currentSize++;
		}else {
			System.out.println("Table is full");
			throw TableFullException;}



	}

	public void printDateTime(String date) throws Exception{
		HashEntry values = hashTable[find(date)];
		System.out.println("The global value is "+values.getGlobal() +" and the voltage value is " +values.getVoltage()+".");
	}

}
