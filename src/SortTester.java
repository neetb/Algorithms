

import java.util.Random;

import algos.sorting.InsertionSort;
   
import algos.sorting.Record;

public class SortTester {

	public static void main(String[] args) {
		
		Record[] records = createInput();
		System.out.println("Input");
		print(records);
		System.out.println();
		
		System.out.println(" Jar Insertion Sort");
		long start = System.currentTimeMillis();
		Insertion.sort(records);
		long end = System.currentTimeMillis();
		System.out.println("Time taken in ms:: " + (end - start));
		System.out.println("Output");
		print(records);
		
	/*	System.out.println("Jar Quick Sort");
		long start1 = System.currentTimeMillis();
		Quick.sort(records);
		long end1 = System.currentTimeMillis();
		System.out.println("Time taken in ms:: " + (end1 - start1));
		System.out.println("Output");
		//print(records);
		System.out.println("*********************");
	*/	testYours(records);
		
	}
	
	
	private static void testYours(Record[] records)	{

		//MergeSort<Record> sortAlgo = new MergeSort<Record>();
		QuickSort<Record> sortAlgoOne = new QuickSort<Record>();
		//SelectionSort<Record> sortAlgo = new SelectionSort<Record>();
		InsertionSort<Record> sortAlgo = new InsertionSort<Record>();
		//Record[] records = createInput();
		//System.out.println("Input");
		//print(records);
		System.out.println("My Insertion Sort");
		long start = System.currentTimeMillis();
		sortAlgo.sort(records);
		long end = System.currentTimeMillis();
		System.out.println("Time taken in ms:: " + (end - start));
		System.out.println("Output");
		print(records);
	/*	
		System.out.println("My Quick Sort");
		long start1 = System.currentTimeMillis();
		sortAlgoOne.sort(records);
		long end1 = System.currentTimeMillis();
		System.out.println("Time taken in ms:: " + (end1 - start1));
		System.out.println("Output");
		//print(records);
*/		
	
	}
	

	private static Record[] createInput() {
		Record[] records = new Record[10000];
		Random rand = new Random();
		for (int i = 0; i < records.length; i++) {
			records[i] = new Record(rand.nextInt(20000));
		}
		return records;
	}

	private static void print(Record[] records) {
		for (int i = 0; i < records.length; i++) {
			System.out.print(records[i] + " ");
		}
		System.out.println();
	}

}
