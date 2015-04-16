public class Solver {

	private int moves;
	private boolean solvable = false;
	private SearchNode goal;
	private Board input;

	// find a solution to the initial board (using the A* algorithm)
	public Solver(Board initial) {
		if (initial == null) {
			throw new NullPointerException();
		}
		this.input = initial;
		Board twin = this.input.twin();
		if(runAStarSimultaneously(this.input, twin))	{
			this.solvable = true;
		}
	}

	private boolean runAStarSimultaneously(Board input, Board twin) {
		boolean first = false;
		boolean second = false;

		MinPQ<SearchNode> searchNodePQ = new MinPQ<SearchNode>();
		SearchNode initialNode = new SearchNode(input, -1, null);
		searchNodePQ.insert(initialNode);

		MinPQ<SearchNode> twinSearchNodePQ = new MinPQ<SearchNode>();
		SearchNode twinInitialNode = new SearchNode(twin, -1, null);
		twinSearchNodePQ.insert(twinInitialNode);

		while (true) {
			SearchNode node = searchNodePQ.delMin();
			SearchNode twinNode = twinSearchNodePQ.delMin();

			node.moves++;
			twinNode.moves++;

			if ((node.board.isGoal())) {
				first = true;
				this.moves = node.moves;
				this.goal = node;
				break;
			}

			if ((twinNode.board.isGoal())) {
				second = true;
				break;
			}

			Iterable<Board> neighbours = node.board.neighbors();
			Iterable<Board> twinNeighbours = twinNode.board.neighbors();

			for (Board b : neighbours) {
				if ((node.prevNode != null) && (b.equals(node.prevNode.board)))
					continue;
				searchNodePQ.insert(new SearchNode(b, node.moves, node));
			}

			for (Board b : twinNeighbours) {
				if ((twinNode.prevNode != null)
						&& (b.equals(twinNode.prevNode.board)))
					continue;
				twinSearchNodePQ.insert(new SearchNode(b, twinNode.moves,
						twinNode));
			}
		}

		if(first)
			return true;
		
		if(second)
			return false;
		
		return false;
	}

	// is the initial board solvable?
	public boolean isSolvable() {
		return this.solvable;
	}

	// min number of moves to solve initial board; -1 if unsolvable
	public int moves() {
		if(!(isSolvable()))	{
			return -1;
		}
		return this.moves;
	}

	// sequence of boards in a shortest solution; null if unsolvable
	public Iterable<Board> solution() {
		if(!(isSolvable()))	{
			return null;
		}
		
		Stack<Board> solution = new Stack<Board>();
		SearchNode node = this.goal;

		while (node != null) {
			solution.push(node.board);
			node = node.prevNode;
		}
		return solution;
	}

	public static void main(String[] args) {

		// create initial board from file
		In in = new In(args[0]);
		int N = in.readInt();
		if((N < 2) || (N >=128))	{
			System.out.println("Invalid input");
			return;
		}
		int[][] blocks = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				blocks[i][j] = in.readInt();

		Board initial = new Board(blocks);

		Solver solver = new Solver(initial);
		if (!solver.isSolvable())
			StdOut.println("No solution possible");
		else {
			Iterable<Board> solution = solver.solution();
			StdOut.println("Minimum number of moves = " + solver.moves());
			for (Board board : solution) {
				StdOut.println(board);
			}
		}		
	}


	private class SearchNode implements Comparable<SearchNode> {
		Board board;
		int moves;
		SearchNode prevNode;

		SearchNode(Board board, int moves, SearchNode node) {
			this.board = board;
			this.moves = moves;
			this.prevNode = node;
		}

		@Override
		public int compareTo(SearchNode o) {
			if ((this.board.manhattan() + this.moves) < (o.board.manhattan() + o.moves))
				return -1;
			else if ((this.board.manhattan() + this.moves) > (o.board
					.manhattan() + o.moves))
				return 1;
			else
				return 0;
		}

		public Board getBoard() {
			return this.board;
		}

	}
	
}