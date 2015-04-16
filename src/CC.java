public class CC {

	private boolean[] marked;
	private int count;
	private int[] component;

	public CC(Graph g) {
		this.marked = new boolean[g.V()];
		this.component = new int[g.V()];
		
		for (int i = 0; i < g.V(); i++) {
			if(!marked[i])	{
				dfs(g, i);
				count++;
			}
		}
		
	}

	private void dfs(Graph g, int s) {
		for (int v : g.adj(s)) {
			if (!marked(v)) {
				marked[v] = true;
				component[v] = count;
				dfs(g, v);
			}
		}
	}

	private boolean marked(int v) {
		return marked[v];
	}

	public int count() {
		return count;
	}

	public int id(int v) {
		return component[v];
	}
}
