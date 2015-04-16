package algos.sorting;

public class Record implements Comparable<Record> {
	private int id;

	public Record(int id){
		this.id = id;
	}
	
	@Override
	public int compareTo(Record o) {
		if (this.id < o.id)
			return -1;
		else if (this.id > o.id)
			return 1;
		else
			return 0;
	}
	
	public String toString()	{
		return this.id + "";
	}

}
