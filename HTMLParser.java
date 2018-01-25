package gg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class HTMLParser {
	
	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(args[0])));
			PrintWriter writer = new PrintWriter(new File("C:\\Users\\Kyle\\Desktop\\tmp.txt"));
			String line = null;
			while((line = reader.readLine()) != null) {
				// remove !line.contains("td")) as I only used id's for that.
				if(line.contains("id=\"") && !line.contains("name=\"") && !line.contains("td")) {
					String[] spl = line.split(" ");
					for(int i = 0; i < spl.length; i++) {
						if(spl[i].contains("id=\"")) {
							// found id, now to add name
							String tmp = spl[i].substring(0, (spl[i].lastIndexOf('"') + 1));
							tmp = tmp.replace("id", "name");
							spl[i] = tmp + " " + spl[i];
							// now rebuild the line
							String newline = "";
							for(int j = 0; j < spl.length; j++) {
								if(j == 0)
									newline = spl[j];
								else
									newline = newline + " " + spl[j];
							}
							writer.println(newline);
						}
					}
				} else {
					writer.println(line);
				}
			}
			reader.close();
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
