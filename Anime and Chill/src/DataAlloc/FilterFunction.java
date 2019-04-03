package DataAlloc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FilterFunction {
	
	private int count;
	private String current;
	private BufferedReader buff;
	private File file;
	
	public FilterFunction()throws IOException{
		this.count = 0;
		this.current = "";
		this.file = new File("Data/UserAnimeList.csv");
		this.buff = new BufferedReader(new FileReader(this.file));
	}
	
	public String filter()throws IOException{
		
		String line = null;
		
		int pos = 0;
		
		String username = "";
		
		while((line = this.buff.readLine()) != null) {
			String[] store = line.split(",", -1);
			
			if(store.length != 11)
				continue ;
			
			username = PopulateData.strip(store[pos]);
			
			if(this.count >= 2) {
				if(username.equals(this.current))
					continue;
			}
			
			if(!username.equals(this.current)) {
				this.count = 0;
				this.current = username;
				return line;
			}
			
			else if (username.equals(this.current)) {
				this.count++;
				return line;
			}
		}
		return line;
	}
	
	public static void main(String[] args)throws IOException {
		FilterFunction f = new FilterFunction();
		String line = null;
		while((line = f.filter()) != null) {
			System.out.println(line);
		}
	}
}
