package algos.sorting;

public class SelectionSort<T extends Comparable> {

	public void sort(T[] items) {
		for (int i = 0; i < items.length; i++) {
			int minIndex = i;
			for (int j = i + 1; j < items.length; j++) {
				if (less(items[j], items[minIndex])) {
					minIndex = j;
				}
			}
			exchange(items, i, minIndex);
		}

	}

	private boolean less(T left, T right){
		return (left.compareTo(right) < 0);			
	}
	
	private void exchange(T[] items, int i, int j)	{
		T temp = items[i];
		items[i] = items[j];
		items[j] = temp;
	}
}
