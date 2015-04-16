import java.util.ArrayList;
import java.util.List;

//package ds;

public class MyGraph {

	private List<Integer>[] adjList;
	private final int V;
	private int E;
	private int count;

	// create an empty graph with V vertices
	public MyGraph(int V) {
		this.V = V;
		adjList = (List<Integer>[])new List[V];
		for (int i = 0; i < V; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
	}

	// create a graph from input stream
	public MyGraph(In in) {
		this(in.readInt());
		this.E = in.readInt();
		while(!(in.isEmpty()))	{
			/*String input = in.readString();
			if(input.indexOf(" ") != -1)	{
				String[] inputStr = input.split(" ");
				addEdge(Integer.parseInt(inputStr[0]),Integer.parseInt(inputStr[1]));
			}*/
			
			addEdge(in.readInt(), in.readInt());
		}
	}

	// add an edge v-w
	public void addEdge(int v, int w) {
		adjList[v].add(w);
		adjList[w].add(v);
		count++;
	}

	// vertices adjacent to v
	public Iterable<Integer> adj(int v) {
		return adjList[v];
	}

	// number of vertices
	public int V() {
		return this.V;
	}

	// number of edges
	public int E() {
		return this.E;
	}

	// string representation
	public String toString() {
		StringBuilder stringRep = new StringBuilder();
		for(int i = 0 ; i< adjList.length; i++){
			List<Integer> edges = adjList[i];
			for(Integer v : edges)	{
				stringRep.append(i + " -> ");
				stringRep.append(v);
				stringRep.append("/r/n");
				
			}
		}
		return stringRep.toString();
	}
}
