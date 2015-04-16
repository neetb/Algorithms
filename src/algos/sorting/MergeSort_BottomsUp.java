package algos.sorting;

public class MergeSort_BottomsUp<T extends Comparable> implements Sort<T> {

	public void sort(T[] items) {
		if ((items == null) || (items.length == 0)) {
			return;
		}
		int N = items.length;

		for (int sz = 1; sz < N; sz = sz + sz) {
			for (int i = 0; i < N - sz; i += sz + sz) {
				merge(items, i, i + sz - 1, Math.min(i + sz + sz - 1, N - 1));
			}
		}

	/*	for (int sz = 1; sz < N; sz = sz + sz) {
			for (int lo = 0; lo < N - sz; lo += sz + sz) {
				merge(items, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
			}
		}*/
	}

	private void merge(T[] items, int lo, int mid, int hi) {
		T[] aux = (T[]) new Comparable[items.length];

		for (int i = 0; i < items.length; i++) {
			aux[i] = items[i];
		}

		int i = lo;
		int j = mid + 1;

		if (less(items[j - 1], items[j]))
			return;

		for (int k = lo; k <= hi; k++) {
			if (i > mid) {
				items[k] = aux[j++];
			} else if (j > hi) {
				items[k] = aux[i++];
			} else if (less(aux[i], aux[j])) {
				items[k] = aux[i++];
			} else
				items[k] = aux[j++];
		}
	}

	private boolean less(T left, T right) {
		return (left.compareTo(right) < 0);
	}

}
