import java.util.ArrayList;
import java.util.List;

public class Board {

	private int[][] blocks;

	private int N;

	// Construct a board from an N-by-N array of blocks (where blocks[i][j] =
	// block in row i, column j)
	public Board(int[][] input) {
		if(input == null){
			throw new NullPointerException();
		}
		
		this.N = input.length;
		this.blocks = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				this.blocks[i][j] = input[i][j];
			}
		}
		
	}

	// board dimension N
	public int dimension() {
		return this.N;
	}

	// number of blocks out of place
	public int hamming() {
		int hamming = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int val = (i * N) + j + 1;
				if ((blocks[i][j] != val) && (blocks[i][j] != 0)) {
					hamming++;
				}

				/*if ((blocks[i][j] == 0) && (i != N - 1) && (j != N - 1)) {
					hamming++;
				}*/
			}
		}
		return hamming;
	}

	// sum of Manhattan distances between blocks and goal
	public int manhattan() {
		int manhattan = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int val = blocks[i][j];

				int i1 = (val - 1) / N;
				int j1 = (val - 1) % N;

				if (val != 0) {
					int moves = Math.abs(i - i1) + Math.abs(j - j1);
					manhattan += moves;
				}				
					}
		}
		return manhattan;
	}

	// is this board the goal board?
	public boolean isGoal() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int val = (i * N) + j + 1;

				if ((i == N - 1) && (j == N - 1)) {
					return (blocks[i][j] == 0);
				}

				if (blocks[i][j] != val) {
					return false;
				}
			}
		}
		return true;
	}

	// a board that is obtained by exchanging two adjacent blocks in the same
	// row
	public Board twin() {
		int[][] modifiedBlocks = new int[N][N];		
		int blank_i = -1;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				modifiedBlocks[i][j] = blocks[i][j];
				if(blocks[i][j] == 0)	{
					blank_i = i;
				}
			}
		}
		
		if(isValid(blank_i-1, 0))	{
			int temp = modifiedBlocks[blank_i-1][0];
			modifiedBlocks[blank_i-1][0] = modifiedBlocks[blank_i-1][1];
			modifiedBlocks[blank_i-1][1] = temp;	
		}else	{
			int temp = modifiedBlocks[blank_i+1][0];
			modifiedBlocks[blank_i+1][0] = modifiedBlocks[blank_i+1][1];
			modifiedBlocks[blank_i+1][1] = temp;	
		}

		return new Board(modifiedBlocks);
	}

	// does this board equal y?
	public boolean equals(Object y) {
		if (y == null)
			return false;

		if (!(y instanceof Board))
			return false;

		if (this == y) {
			return true;
		}

		Board arg = (Board) y;

		if (arg.dimension() != this.dimension())
			return false;

		if (this.toString().equals(arg.toString()))
			return true;

		return false;
	}

	// all neighboring boards
	public Iterable<Board> neighbors() {
		List<Board> neighbours = new ArrayList<Board>();
		int blank_i = 0;
		int blank_j = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (blocks[i][j] == 0) {
					blank_i = i;
					blank_j = j;
				}
			}
		}

		if (isValid(blank_i + 1, blank_j)) {
			neighbours.add(swap(blank_i, blank_j, blank_i + 1, blank_j));
		}

		if (isValid(blank_i, blank_j + 1))
			neighbours.add(swap(blank_i, blank_j, blank_i, blank_j + 1));

		if (isValid(blank_i - 1, blank_j))
			neighbours.add(swap(blank_i, blank_j, blank_i - 1, blank_j));

		if (isValid(blank_i, blank_j - 1))
			neighbours.add(swap(blank_i, blank_j, blank_i, blank_j - 1));

		return neighbours;
	}

	private Board swap(int blankI, int blankJ, int i, int j) {
		int[][] copy = copy(blocks);
		copy[blankI][blankJ] = copy[i][j];
		copy[i][j] = 0;
		return new Board(copy);
	}

	private int[][] copy(int[][] blocks) {
		int[][] copy = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copy[i][j] = blocks[i][j];
			}
		}
		return copy;
	}

	private boolean isValid(int i, int j) {
		return (((i >= 0) && (i < N)) && ((j >= 0) && (j < N)));

	}

	// string representation of this board (in the output format specified
	// below)
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append(N);
		string.append("\r\n");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				string.append(blocks[i][j]);
				string.append(" ");
			}
			string.append("\r\n");
		}
		return string.toString();
	}

	// unit tests (not graded)
	public static void main(String[] args) {
		int[][] test1 = new int[3][3];
		test1[0][0] = 4;
		test1[0][1] = 3;
		test1[0][2] = 2;
		test1[1][0] = 0;
		test1[1][1] = 1;
		test1[1][2] = 6;
		test1[2][0] = 7;
		test1[2][1] = 5;
		test1[2][2] = 8;

		Board board1 = new Board(test1);
		System.out.println(board1.twin().toString());
/*
		System.out.println(board1.dimension());

		System.out.println(board1.hamming());
		System.out.println(board1.manhattan());
		Iterable<Board> ns = board1.neighbors();

		for (Board b : ns) {
			System.out.println(b);
		}
*/
	}
}