
public abstract class HashTable {
	protected HashEntry[] hashTable;
	protected int tableSize;
	protected double loadFactor;
	protected int occupied;
	protected int currentSize;
	protected int modCount;
	protected int probe;
	protected int inserts;

	public HashTable(int tableSize) {
		this.tableSize = nextPrime(tableSize);
		this.hashTable = new HashEntry[this.tableSize];
	}

	public int nextPrime(int start) {
		if(start%2 == 0) {
			start++;
		}
		for(int i = 2; i<start/2;i++) {
			if(start%i == 0) {
				start++;
				i = 2;
			}
		}
		return start;
	}

	public void setLoadFactor() {
		for(int i = 0; i<this.tableSize;i++)
			if (this.hashTable[i].equals(null)) {
				this.loadFactor += 0.1;
			}
	}

	public String getLoadFactor() {
		double answer = this.currentSize;
		answer =  answer/this.tableSize;
		String ans = String.format("%.1f", answer);
		return ans;
	}

	public int getTableSize() {
		return this.tableSize;
	}


	public boolean isActive(HashEntry[] array, int pos) {
		return array[pos] != null && array[pos].isActive();
	}

	public int hashCode(String hashData) {
		int hash = 0;
		for(int i = 0; i< hashData.length();i++) {
			hash = 31*hash + hashData.charAt(i);
		}
		hash %= tableSize;
		if(hash<0) {
			hash += tableSize;}
		return hash;
		}

		public abstract void insert(HashEntry data) throws Exception;
		public abstract int find(String date) throws Exception;
		public abstract void printDateTime(String date) throws Exception;



}
