
public class CycleDetector {

	private boolean[] marked;
	private boolean cycle = false;
	
	public CycleDetector(Graph g){
		this.marked = new boolean[g.V()];
		for(int i=0; i<g.V(); i++)	{
			if(!marked[i])	{
					dfs(g, i, -1);	
			}
		}
	}
	
	private void dfs(Graph g, int s, int parent){
		marked[s] = true;
		for (int v : g.adj(s)) {
			if (!marked[v]) {
				dfs(g, v, s);
			}else	{
				if(v!=parent)
					cycle = true;
			}
		}
	}
	
	public boolean hasCycle()	{
		return cycle;
	}
	
	
}
