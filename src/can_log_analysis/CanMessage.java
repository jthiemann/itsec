package can_log_analysis;

public class CanMessage implements Comparable<CanMessage>{
	
	private int ID;
	private int data_field[] = new int[8];
	private int frequency = 1;
	
	public int getID() {return this.ID;}
	public int[] getData() {return this.data_field;}
	public int getFrequency() {return this.frequency;}
	
	public CanMessage(String string) {
		this.ID = idFromString(string);
		this.data_field = dataFromString(string);
	}
	
	private int idFromString(String string) {
		return Integer.valueOf(Utils.hex2decimal(string.substring(0, 3)));
	}
	
	private int[] dataFromString(String string) {
		String substring = string.substring(4);
		int result[] = new int[8];
		int data_field_pos = 0;
		for (int i = 0; i < substring.length(); i = i + 2) {
			result[data_field_pos] = Integer.valueOf(Utils.hex2decimal(substring.substring(i, i+2)));
			data_field_pos++;
		}		
		return result;
	}
	
	public int compareTo(CanMessage otherMessage) {
		if (this.ID - otherMessage.ID != 0) {
			return this.ID - otherMessage.ID;
		}
		for (int i = 0; i < this.data_field.length; i++) {
			if (this.data_field[i] - otherMessage.data_field[i] != 0) {
				return this.data_field[i] - otherMessage.data_field[i];
			}
		}
		otherMessage.frequency++;
		return 0;
	}

	public void printData() {
		System.out.println("ID=" + this.ID + ", data=" + this.data_field[0] + " " 
				+ this.data_field[1] + " " + this.data_field[2] + " " + this.data_field[3] + " " 
				+ this.data_field[4] + " " + this.data_field[5] + " " + this.data_field[6] + " " 
				+ this.data_field[7] + ", Frequency=" + this.frequency);
	}
}
