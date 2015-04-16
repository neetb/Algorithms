package algos.sorting;

public class MergeSort<T extends Comparable>  implements Sort<T> {
	public void sort(T[] items) {
		if((items != null) && (items.length > 0))	{
			sort(items, 0, items.length-1);	
		}
	}

	private void sort(T[] items, int lo, int hi) {
		if (lo >= hi)
			return;

		int mid = (lo + hi) / 2;

		sort(items, lo, mid);
		sort(items, mid + 1, hi);
		merge(items, lo, mid, hi);
	}

	private void merge(T[] items, int lo, int mid, int hi) {
		T[] aux = (T[]) new Comparable[items.length];

		for (int i = 0; i < items.length; i++) {
			aux[i] = items[i];
		}

		int i = lo;
		int j = mid+1;

		if(less(items[j-1], items[j]))
			return;
		
		for (int k = lo; k <= hi; k++) {
			if( i > mid)	{
				items[k] = aux[j++];
			}else if(j > hi)	{
				items[k] = aux[i++];
			}else if (less(aux[i],aux[j]))	{
				items[k] = aux[i++];
			}else
				items[k] = aux[j++];
		}
	}

	private boolean less(T left, T right) {
		return (left.compareTo(right) < 0);
	}

}
