package can_log_analysis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeSet;

public class Analysis {
	
	private static TreeSet<CanMessage> canMessageTree = new TreeSet<CanMessage>();
	
	public static void analyzeLog(String input_path, String output_path) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(input_path));
		try {
		    String line = br.readLine();
		    int hashtag_pos = line.indexOf('#');

		    while (line != null) {
		        canMessageTree.add(new CanMessage(line.substring(hashtag_pos-3)));
		        line = br.readLine();
		    }
		} finally {
		    br.close();
		}
		
		PrintWriter writer = new PrintWriter(output_path, "UTF-8");
		
		for (CanMessage s : canMessageTree) {
		    writer.println("ID=" + s.getID() + "\t data=" + s.getData()[0] + " " 
					+ s.getData()[1] + " " + s.getData()[2] + " " + s.getData()[3] + " " 
					+ s.getData()[4] + " " + s.getData()[5] + " " + s.getData()[6] + " " 
					+ s.getData()[7] + "\t Frequency=" + s.getFrequency());
		}
		writer.close();
	}
	
	public static void main(String[] args) throws IOException {
//		CanMessage one = new CanMessage("420#89FFFF0000D0FF00");
//		CanMessage two = new CanMessage("420#89FFFF0200D0FF20");
//		CanMessage three = new CanMessage("420#89FFFF0200D0FF10");
//		CanMessage four = new CanMessage("310#89FFFF0200D0FF20");
		
//		test.add(one);
//		test.add(two);
//		test.add(three);
//		test.add(four);
		
		analyzeLog("/home/jt/Schreibtisch/canlog_3k.txt", "/home/jt/Schreibtisch/canlog_3k_analyzed.txt");
		

		
	}
}
