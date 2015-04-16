package algos.sorting;

public class QuickSort_3Way<T extends Comparable> {
	public void sort(T[] items) {
		if ((items != null) && (items.length > 0)) {
			sort(items, 0, items.length - 1);
		}
	}

	private void sort(T[] items, int lo, int hi) {
		if (lo >= hi)
			return;
		int lt = lo;
		int gt = hi;
		int i = lo;
		T pivot = items[lo];

		while (i <= gt) {
			if (items[i].compareTo(pivot) < 0) {
				exchange(items, i++, lt++);
			} else if (items[i].compareTo(pivot) > 0) {
				exchange(items, i, gt--);
			} else {
				i++;
			}
		}
		sort(items, lo, lt - 1);
		sort(items, gt + 1, hi);
	}

	private int partition(T[] items, int lo, int hi) {
		T pivot = items[lo];
		int i = lo + 1;
		int j = hi;

		while (true) {
			while ((i <= hi) && lessOrEqual(items[i], pivot)) {
				i++;
			}

			while (less(pivot, items[j])) {
				j--;
			}

			if (i > j) {
				exchange(items, j, lo);
				break;
			}
			exchange(items, i, j);

		}
		return j;
	}

	private boolean lessOrEqual(T left, T right) {
		return ((left.compareTo(right) < 0) || (left.compareTo(right) == 0));
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
