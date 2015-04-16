public class PercolationStats {

	private int N;
	private int T;

	private double[] openSitesFractionArr;
	private Percolation[] percolation;
	private double mean;
	private double stddev;

	// perform T independent experiments on an N-by-N grid
	public PercolationStats(int N, int T) {
		if ((N <= 0) || (T <= 0)) {
			throw new IllegalArgumentException();
		}
		this.N = N;
		this.T = T;

		openSitesFractionArr = new double[T];
		percolation = new Percolation[T];
		for(int i=0;i<T;i++)	{
			percolation[i] = new Percolation(N);
		}
	}

	// sample mean of percolation threshold
	public double mean() {
		double sum = 0;
		for (int i = 0; i < openSitesFractionArr.length; i++) {
			sum += openSitesFractionArr[i];
		}
		this.mean = sum / T;
		return this.mean;
	}

	// sample standard deviation of percolation threshold
	public double stddev() {
		double val = 0;
		for (int i = 0; i < openSitesFractionArr.length; i++) {
			double x = openSitesFractionArr[i];
			val += Math.pow((x - mean), 2);
		}

		this.stddev = Math.pow((val / (T - 1)), 0.5);
		return stddev;
	}

	// low endpoint of 95% confidence interval
	public double confidenceLo() {
		double confidenceLo = mean - ((1.96 * stddev) / (Math.pow(T, 0.5)));
		return confidenceLo;
	}

	// high endpoint of 95% confidence interval
	public double confidenceHi() {
		double confidenceHi = mean + ((1.96 * stddev) / (Math.pow(T, 0.5)));
		return confidenceHi;
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			throw new IllegalArgumentException(
					"Two arguments N, T are required.");
		}
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		
		PercolationStats stats = new PercolationStats(N, T);
		for (int i = 0; i < T; i++) {
			Stopwatch stopwatch = new Stopwatch();
			int openSitesCount = 0;
			do {
				// generate random numbers between 1 and N, open them
				int p = StdRandom.uniform(1, N + 1);
				int q = StdRandom.uniform(1, N + 1);

				if (!stats.percolation[i].isOpen(p, q)) {
					stats.percolation[i].open(p, q);
					openSitesCount++;
				}

			} while (!stats.percolation[i].percolates());
			System.out.println("time taken : " + stopwatch.elapsedTime());
			stats.openSitesFractionArr[i] = ((double) openSitesCount) / (N * N);
		}
		System.out.println("mean \t =" + stats.mean());
		System.out.println("stddev \t =" + stats.stddev());
		System.out.println("95% confidence interval \t ="
				+ stats.confidenceLo() + "," + stats.confidenceHi());

	}
	
}
