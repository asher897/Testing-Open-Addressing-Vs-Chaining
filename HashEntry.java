
public class HashEntry {
	private String[] data = new String[3];
	private boolean active;

	public HashEntry(String[] data){
		this.data[0] = data[0];
		this.data[1] = data[1];
		this.data[2] = data[2];
		this.active = true;
	}

	public boolean isActive() {
		return(this.active);
	}

	public String getDate() {
		return this.data[0];
	}

	public String getGlobal() {
		return this.data[1];
	}

	public String getVoltage() {
		return this.data[2];
	}

	public void delete() {
		this.active = false;
	}

	}
