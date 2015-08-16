package bot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	private static final String TOKEN_FILE_PATH = "token.txt";
	
	public static void main(String[] args) {	
		String token = null;
		try (BufferedReader br = new BufferedReader(new FileReader(TOKEN_FILE_PATH))) {
			token = br.readLine();	
		} catch (IOException e) {
			System.err.println("Exception reading bot token!");
			e.printStackTrace();
			System.exit(1);
		}
		
		new TlkStatsBot(token);
	}
	
}
