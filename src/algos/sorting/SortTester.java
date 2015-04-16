package algos.sorting;

import java.util.Arrays;
import java.util.Random;

public class SortTester {

	public static void main(String[] args) {
		QuickSort_3Way<Record> sortAlgoOne = new QuickSort_3Way<Record>();
		Record[] records = createInput();
		Record[] copy = Arrays.copyOf(records, records.length);
		System.out.println("Quick Sort 3 way");
		long start1 = System.nanoTime();
		sortAlgoOne.sort(records);
		long end1 = System.nanoTime();
		System.out.println("Time taken in ms:: " + (end1 - start1));
		System.out.println("Output");
		print(records);
		test(copy);
		
	}
	
	private static void test(Record[] records)	{

		QuickSort<Record> sortAlgoOne = new QuickSort<Record>();
		System.out.println("Quick Sort");
		long start1 = System.nanoTime();
		sortAlgoOne.sort(records);
		long end1 = System.nanoTime();
		System.out.println("Time taken in ms:: " + (end1 - start1));
		System.out.println("Output");
		print(records);
		
	
	}

	private static Record[] createInput() {
		Record[] records = new Record[200];
		Random rand = new Random();
		for (int i = 0; i < records.length; i++) {
			records[i] = new Record(rand.nextInt(2));
		}
		return records;
	}

	private static void print(Record[] records) {
		for (int i = 0; i < records.length; i++) {
			System.out.print(records[i] + " ");
		}
	}

}
