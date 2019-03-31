
public class Linear extends HashTable {


	private static final Exception DuplicateItemException = null;
	private static final Exception TableFullException = null;
	private static final Exception NoSuchElementException = null;


	public Linear(int tableSize) {
		super(tableSize);
	}

	public int find(String date) throws Exception {
		//System.out.println(date);
		int hash = hashCode(date)%tableSize;
		//System.out.println(hash);

		while(hashTable[hash] != null) {
			if(hashTable[hash].getDate().equals(date)) {
				return hash;
			}
			//System.out.println(hash);
			hash = (hash+1)%tableSize;
		}
		System.out.println("Item does not exist");
		throw NoSuchElementException;
	}

	public void insert(HashEntry data) throws Exception {
		int hash = hashCode(data.getDate())%tableSize;
		if(loadFactor != 1) {
			while(hashTable[hash] != null) {
				probe++;
				hash = (hash+1)%tableSize;
			}
			hashTable[hash] = data;
			currentSize++;
			this.loadFactor+=0.1;
		}else {
			System.out.println("Table is full");
			throw TableFullException;}
	}

	public void printDateTime(String date) throws Exception{
		HashEntry values = hashTable[find(date)];
		System.out.println("The global value is "+values.getGlobal() +" and the voltage value is " +values.getVoltage()+".");
	}
}
