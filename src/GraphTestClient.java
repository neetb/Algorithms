import java.io.File;


public class GraphTestClient {

	public static void main(String[] args) {
		String fileName = args[0];
		testDFS(fileName);
		testBFS(fileName);
		testCC(fileName);
		testCycleDetector(fileName);
	}
	
	private static void testCycleDetector(String fileName){
		Graph g = new Graph(new In(new File(fileName)));
		CycleDetector cd = new CycleDetector(g);
		System.out.println("Has cycle : " + cd.hasCycle());
	}
	private static void testCC(String fileName)	{
		Graph g = new Graph(new In(new File(fileName)));
		CC cc = new CC(g);
		
		System.out.println("Connected components count : " + cc.count());
		System.out.println(" component id : " + cc.id(6));
		System.out.println(" component id : " + cc.id(2));
	}
	
	private static void testDFS(String fileName)	{
		Graph g = new Graph(new In(new File(fileName)));
		
		DFS dfs = new DFS(g, 9);
		System.out.println("The connected vertices are - ");
		
		for(int v=0; v<g.V(); v++)	{
 			if(dfs.marked(v))	{
				System.out.println(v);
			}
		}
		
		System.out.println("The path to 5 - ");
		for(int v : dfs.path(5))	{
			System.out.println(v);
		}
	}
	
	private static void testBFS(String fileName)	{
		Graph g = new Graph(new In(new File(fileName)));
		
		BFS bfs = new BFS(g, 9);
		System.out.println("The connected vertices are - ");
		
		for(int v=0; v<g.V(); v++)	{
 			if(bfs.marked(v))	{
				System.out.println(v);
			}
		}
		
		System.out.println("The path to 5 - ");
		for(int v : bfs.pathTo(5))	{
			System.out.println(v);
		}
	}
}
