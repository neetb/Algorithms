import java.util.HashMap;
import java.util.Map;

public class SAP {

	private Digraph g;
	
   // constructor takes a digraph (not necessarily a DAG)
   public SAP(Digraph G)	{
	   this.g = G;	   
   }

   // length of shortest ancestral path between v and w; -1 if no such path
   public int length(int v, int w)	{
	   int a = ancestor(v, w);
	   BreadthFirstDirectedPaths paths = new BreadthFirstDirectedPaths(g, a);
	   return paths.distTo(v) + paths.distTo(w);
   }

   // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
   public int ancestor(int v, int w)	{
	   int ancestor = -1;
	   BreadthFirstDirectedPaths pathsV = new BreadthFirstDirectedPaths(g, v);
	   Iterable<Integer> pathVList = pathsV.pathTo(0);
	  
	   BreadthFirstDirectedPaths pathW = new BreadthFirstDirectedPaths(g, w);
	   Iterable<Integer> pathWList = pathW.pathTo(0);
	  
	   if((pathVList == null) || (pathWList == null))	{
		   return -1;
	   }
	   
	   Map<Integer,Integer> map = addToMap(pathVList);
	  for(Integer keyW : pathWList)	{
		  if(map.containsKey(keyW))	{
			  ancestor = map.get(keyW);
		  }
	  }
	  return ancestor;	   
   }
   
   private  Map<Integer,Integer> addToMap(Iterable<Integer> list){
	   Map<Integer,Integer> map = new HashMap<Integer,Integer>();
	   int count = 0;
	   for(Integer key : list)	{
		   map.put(key, count++);   
	   }
	   return map;
   }

   // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
   public int length(Iterable<Integer> v, Iterable<Integer> w)	{
	   
   }

   // a common ancestor that participates in shortest ancestral path; -1 if no such path
   public int ancestor(Iterable<Integer> v, Iterable<Integer> w)	{
	   
   }

   // do unit testing of this class
   public static void main(String[] args)	{
	   
   }
}