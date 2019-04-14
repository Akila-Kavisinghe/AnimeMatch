package Graphs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import DataAlloc.city;

/**
 * The ShortPath class finds shortest path between nodes
 */
public class ShortPath {
	
	/**
	 * strip is used to strip string and replace " ", ".", "__" with "_"
	 * @param line - string that needs to be striped
	 * @return String - striped string
	 */
	public static String strip(String line) {
		line = line.replace(" ", "_");
		line = line.replace(".", "_");
		line = line.replace("__", "_");
		line = line.toLowerCase();
		return line;
	}
	
	/**
	 * buildG is used to build the graph
	 * @return Graph - returns the graph
	 * @throws IOException - Input/Output error
	 */
	public static Graph buildG() throws IOException{
		File cities = new File("Data/connectedCities.txt");
		BufferedReader buff = new BufferedReader(new FileReader(cities));
		String line = null;
		
		Graph graph = new Graph(33);
		
		//reads line from the connectedCities text file to make edges
		while((line = buff.readLine()) != null) {
			String[] pairs  = line.split(", ");
			pairs[0] = strip(pairs[0]);
			pairs[1] = strip(pairs[1]);
			city first = city.valueOf(pairs[0]);
			city sec = city.valueOf(pairs[1]);
			
			int index1 = first.ordinal();
			int index2 = sec.ordinal();
			
			graph.addEdge(index1, index2);
		}
		
		buff.close();
		return graph;
	}
	
	/**
	 * path is used to find shortest path between people
	 * @param start - source node
	 * @param end - destination node
	 * @return integer - length of the path
	 * @throws IOException - Input/Output error
	 */
	public static int path(int start, int end) throws IOException {
		Graph graph = buildG();
		BreadthFirstPaths bfs = new BreadthFirstPaths(graph, start);
        String bfsans = "";
        
        //if the path exists performs bfs to that path
        if (bfs.hasPathTo(end)) {
            for (int x : bfs.pathTo(end)) {
            	//making a string to have the bfs path
                if (x == start) 
                	bfsans += (city.values()[x]);
                else        
                	bfsans += " " + city.values()[x];
            }
            String[] fpath = bfsans.split(" ");
            return fpath.length; 
        }

        else {
            return 10;
        }
	}
}
