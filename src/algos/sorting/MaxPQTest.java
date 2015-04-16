package algos.sorting;

import java.util.Random;

public class MaxPQTest {

	public static void main(String[] args) {
		MaxPQ<Record> maxPQ = new MaxPQ<Record>(120);
		Record[] records = createInput(maxPQ);
		print(records);
		System.out.println();
		System.out.println("*max:" + maxPQ.delMax().toString());

	}

	private static Record[] createInput(MaxPQ<Record> maxPQ) {
		Record[] records = new Record[120];
		Random rand = new Random();
		for (int i = 0; i < records.length; i++) {
			records[i] = new Record(rand.nextInt(1200));
			maxPQ.insert(records[i]);
		}
		return records;
	}

	private static void print(Record[] records) {
		for (int i = 0; i < records.length; i++) {
			System.out.print(records[i] + " ");
		}
	}

}
 