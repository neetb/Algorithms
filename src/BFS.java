import java.util.ArrayList;
import java.util.LinkedList;


public class BFS {

	private boolean[] marked;
	private LinkedList<Integer> queue;
	private int[] edgeTo;
	private int source;
	
	public BFS(Graph g, int source){
		this.marked = new boolean[g.V()];
		this.queue = new LinkedList<Integer>();
		this.edgeTo = new int[g.V()];
		this.source = source;
		BFS(g, source);
		
	}
	
	private void BFS(Graph g, int s){
		this.queue.add(s);
		while(!this.queue.isEmpty())	{
			int v = this.queue.remove();
			marked[v] = true;	
			for(int w : g.adj(v))	{
				if(!marked[w])	{
					this.queue.add(w);
					edgeTo[w] = v;
					marked[w] = true;
				}
			}
		}
	}
	
	public boolean marked(int v){
		return marked[v];
	}
	
	public Iterable<Integer> pathTo(int dest){
		if(!hasPathTo(dest))	{
			return new ArrayList<Integer>();
		}
		Stack<Integer> path = new Stack<Integer>();
		for (int i=dest; i!=source; i=edgeTo[i])	{
			path.push(i);
		}
		path.push(source);
		
		return path;
	}
	
	public boolean hasPathTo(int dest)	{
		return marked[dest];
	}
	
	
}
