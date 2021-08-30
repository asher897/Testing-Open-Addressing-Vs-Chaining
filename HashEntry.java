
public class HashEntry {
	private String[] data = new String[3];
	private boolean active;

	public HashEntry(String[] data){
		this.data[0] = data[0];
		this.data[1] = data[1];
		this.data[2] = data[2];
		this.active = true;
	}


	/**
	*Determines if the current HashEntry is active or not (i.e marked deleted or not)
	*@return boolean value if true then not deleted.
	*/
	public boolean isActive() {
		return(this.active);
	}

	/**
	*This method retrieves one of the data values held in the HashEntry, specifically the date.
	*@return the date value
	*/
	public String getDate() {
		return this.data[0];
	}


	/**
	*This method retrieves one of the data values held in the HashEntry, specifically the global value.
	*@return the global value
	*/
	public String getGlobal() {
		return this.data[1];
	}

	/**
	*This method retrieves one of the data values held in the HashEntry, specifically the voltage.
	*@return the voltage value
	*/
	public String getVoltage() {
		return this.data[2];
	}

	/**
	*void method that deletes the current HashEntry by marking it deleted (i.e changing the boolean value of the instance variable active to false)
	*/
	public void delete() {
		this.active = false;
	}

	}
