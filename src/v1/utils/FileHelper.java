package v1.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileHelper {
	
	private final String FILE_COUNT = "/home/pranav/Desktop/cardgame/count.txt";
	private final String FILE_GAME = "/home/pranav/Desktop/cardgame/game.txt";
	
	private static FileHelper instance;

	
	public FileHelper() {
		// TODO Auto-generated constructor stub

	}
	
	public static FileHelper getInstance() {
		if(instance == null) {
			instance = new FileHelper();
		}
		return instance;
	}
	
	public void appendStringToCountFile(String s) {
		  PrintWriter writer;
		  try{
			 writer = new PrintWriter(new BufferedWriter(new FileWriter(FILE_COUNT, true)));
			 writer.append(s);
			 writer.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void appendStringToGameFile(String s) {
//		BufferedWriter bw = null;
//		try {
//			// APPEND MODE SET HERE
//			bw = new BufferedWriter(new FileWriter(FILE_GAME, true));
//			bw.write(s);
//			bw.newLine();
//			bw.flush();
//			bw.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		printToTerminal(s);
	}
	
	public void printToTerminal(String s) {
		System.out.println(s);
	}

}
