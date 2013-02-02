import java.util.Vector;
import java.util.Scanner;
import java.util.HashMap;
import java.io.*;

public class NBUtils {

	private static final String CCAT = "CCAT";
	private static final String ECAT = "ECAT";
	private static final String GCAT = "GCAT";
	private static final String MCAT = "MCAT";
	public static final String[] CAT = {CCAT, ECAT, GCAT, MCAT};
	
	public static Vector<String> getCategories(String description) {
		Vector<String> categories = new Vector<String>();
		for (String cat : CAT) {
			if (description.contains(cat)) {
				categories.add(cat);
			}
		}
		return categories;
	}


	public static Vector<String> tokenizeDoc(String cur_doc) {
		String[] words = cur_doc.split("\\s+");
		Vector<String> tokens = new Vector<String>();
		for (int i = 0; i < words.length; i++) {
			words[i] = words[i].replaceAll("\\W", "");
			if (words[i].length() > 0) {
				tokens.add(words[i]);
			}
		}
		return tokens;
	}
}