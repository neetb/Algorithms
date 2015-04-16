package algos.sorting;

public class MaxPQ<T extends Comparable> {

	private T[] items;
	private int N = 0;

	public MaxPQ(int count) {
		items = (T[]) new Comparable[count + 1];
	}

	public boolean isEmpty() {
		return (N == 0);
	}

	public int size() {
		return N;
	}

	public void insert(T item) {
		items[++N] = item;
		swim(N);
	}

	public T delMax() {
		exchange(items, 1, N);
		T toBeDeleted = items[N];
		items[N] = null;
		N = N - 1;
		sink(1);
		return toBeDeleted;
	}

	private void sink(int k) {
		while (2 * k < N) {
			int j = 2 * k;
			if (less(items[j], items[j + 1])) {
				j++;
			}

			if (less(items[k], items[j])) {
				exchange(items, k, j);
				k = j;
			} else {
				break;
			}
		}
	}

	private void swim(int k) {
		while (k > 1) {
			if (less(items[k / 2], items[k])) {
				exchange(items, k / 2, k);
				k = k / 2;
			}else	{
				break;
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
