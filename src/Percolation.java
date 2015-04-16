public class Percolation {

	private int N;
	private boolean[][] grid;
	private WeightedQuickUnionUF weightedQuickUnionUF;
	private WeightedQuickUnionUF weightedQuickUnionUFPlus;
	
	// create N-by-N grid, with all sites blocked
	public Percolation(int N) {
		if (N <= 0) {
			throw new IllegalArgumentException();
		}
		
		this.N = N;
		
		// initialize all sites as blocked
		grid = new boolean[N + 1][N + 1];
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				grid[i][j] = false;
			}
		}

		weightedQuickUnionUFPlus = new WeightedQuickUnionUF((N * N) + 2);
		weightedQuickUnionUF = new WeightedQuickUnionUF((N * N) + 1);
	}

	// open site (row i, column j) if it is not open already
	public void open(int i, int j) {
		if (!isWithinBounds(i, j)) {
			throw new IndexOutOfBoundsException();
		}
		
		grid[i][j] = true;
		
		if(i == 1)	{
			weightedQuickUnionUFPlus.union(0, getUnionFindIndex(i,j));
			weightedQuickUnionUF.union(0, getUnionFindIndex(i,j));
		}  

		if(i == N)	{
			weightedQuickUnionUFPlus.union((N * N) + 1, getUnionFindIndex(i,j));
		}
		
		if ((isWithinBounds(i - 1, j)) && (grid[i - 1][j]))  {
			weightedQuickUnionUF.union(getUnionFindIndex(i - 1, j),
					getUnionFindIndex(i, j));
			weightedQuickUnionUFPlus.union(getUnionFindIndex(i - 1, j),
					getUnionFindIndex(i, j));
		}

		if ((isWithinBounds(i + 1, j)) && (grid[i + 1][j]))  {
			weightedQuickUnionUF.union(getUnionFindIndex(i + 1, j),
					getUnionFindIndex(i, j));
			weightedQuickUnionUFPlus.union(getUnionFindIndex(i + 1, j),
					getUnionFindIndex(i, j));
		}

		if ((isWithinBounds(i, j - 1)) && (grid[i][j - 1]))  {
			weightedQuickUnionUF.union(getUnionFindIndex(i, j - 1),
					getUnionFindIndex(i, j));
			weightedQuickUnionUFPlus.union(getUnionFindIndex(i, j - 1),
					getUnionFindIndex(i, j));
		}

		if ((isWithinBounds(i, j + 1)) && (grid[i][j + 1]))  {
			weightedQuickUnionUF.union(getUnionFindIndex(i, j + 1),
					getUnionFindIndex(i, j));
			weightedQuickUnionUFPlus.union(getUnionFindIndex(i, j + 1),
					getUnionFindIndex(i, j));
		}
	}

	// is site (row i, column j) open?
	public boolean isOpen(int i, int j) {
		if (!isWithinBounds(i, j)) {
			throw new IndexOutOfBoundsException();
		}

		return grid[i][j];
	}

	// is site (row i, column j) full?
	public boolean isFull(int i, int j) {
		if (!isWithinBounds(i, j)) {
			throw new IndexOutOfBoundsException();
		}
		
		if(!isOpen(i, j))	{
			return false;
		}
		return weightedQuickUnionUF.connected(0, getUnionFindIndex(i, j));
	}

	// does the system percolate?
	public boolean percolates() {
		return weightedQuickUnionUFPlus.connected(0, ((N * N) + 1));
	}
	
	private boolean isWithinBounds(int x, int y) {
		if ((x < 1) || (x > N) || (y < 1) || (y > N)) {
			return false;
		}
		return true;
	}

	private int getUnionFindIndex(int i, int j) {
		return ((i-1) * N) + j;
	}
	

}
