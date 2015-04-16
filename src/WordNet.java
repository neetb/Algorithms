import java.io.File;
import java.util.LinkedList;

public class WordNet {

	private ST<String, Integer> symbolTable;
	private String[] keys;
	private Digraph graph;

	// constructor takes the name of the two input files
	public WordNet(String synsets, String hypernyms) {
		In synsetsIn = new In(new File(synsets));
		int count = 0;
		while (synsetsIn.hasNextLine()) {
			String inputLine = synsetsIn.readLine();
			String[] inputValues = inputLine.split(",");
			symbolTable.put(inputValues[1], Integer.parseInt(inputValues[0]));
			count++;
		}

		keys = new String[count];
		for (String stKey : symbolTable.keys()) {
			keys[symbolTable.get(stKey)] = stKey;
		}

		Digraph g = new Digraph(count);  

		In hypernymsIn = new In(new File(hypernyms));
		while (hypernymsIn.hasNextLine()) {
			String inputLine = hypernymsIn.readLine();
			String[] inputValues = inputLine.split(",");
			for (int i = 1; i < inputValues.length; i++) {
				g.addEdge(Integer.parseInt(inputValues[0]),
						Integer.parseInt(inputValues[i]));
			}
		}
		
		this.graph = g;
	}

	// returns all WordNet nouns
	public Iterable<String> nouns() {
		return symbolTable.keys();
	}

	// is the word a WordNet noun?
	public boolean isNoun(String word) {
		return symbolTable.contains(word);
	}

	// distance between nounA and nounB (defined below)
	public int distance(String nounA, String nounB) {
		int index = symbolTable.get(nounA);
		LinkedList<Integer> queue = new LinkedList<Integer>();
		for(int adj : graph.adj(index))	{
			
		}
	}

	// a synset (second field of synsets.txt) that is the common ancestor of
	// nounA and nounB
	// in a shortest ancestral path (defined below)
	public String sap(String nounA, String nounB) {

	}

	// do unit testing of this class
	public static void main(String[] args) {

	}
}
