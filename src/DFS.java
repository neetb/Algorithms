import java.util.ArrayList;
import java.util.Stack;

public class DFS {

	private boolean[] marked;
	private int[] edgeTo;
	private int source;
	private int count;

	public DFS(Graph g, int source) {
		this.source = source;
		marked = new boolean[g.V()];
		edgeTo = new int[g.V()];
		dfs(g, source);
	}

	private void dfs(Graph g,int source) {
		marked[source] = true;
		for (int v : g.adj(source)) {
			if (!marked(v)) {
				edgeTo[v] = source;
				count++;
				dfs(g, v);
			}
		}
	}

	public boolean marked(int v) {
		return marked[v];
	}

	public Iterable<Integer> path(int dest) {
		if(!hasPathTo(dest))	{
			return new ArrayList<Integer>();
		}
		
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = dest; i != source; i = edgeTo[dest]) {
			stack.push(i);
		}
		stack.push(source);
		return stack;
	}
	
	public boolean hasPathTo(int dest){
		return marked[dest];	
	}
	
	//how many vertices are connected to the source?
	public int count()	{
		return count;
	}

}
