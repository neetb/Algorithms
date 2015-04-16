package algos.sorting;

public class InsertionSort<T extends Comparable> implements Sort<T> {
	public void sort(T[] items) {
		for (int i = 0; i < items.length - 1; i++) {
			for (int j = i + 1; j > 0; j--) {
				if (less(items[j], items[j - 1])) {
					exchange(items, j, j - 1);
				} else {
					break;
				}
			}
		}
	}

	private boolean less(T left, T right) {
		return (left.compareTo(right) < 0);
	}

	private void exchange(T[] items, int i, int j) {
		T temp = items[i];
		items[i] = items[j];
		items[j] = temp;
	}
}
