import java.util.Vector;
import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;
import java.io.*;

public class NBTrain {

	private static HashMap<String, Integer> classification;
	private static HashSet<String> vocabulary;

	public static void main (String[] args) {

		classification = new HashMap<String, Integer>();
		vocabulary = new HashSet<String>();

		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			String doc = scanner.nextLine();
			Vector<String> tokDoc = NBUtils.tokenizeDoc(doc);
			Vector<String> docCat = NBUtils.getCategories(tokDoc.get(0));
			classifyDoc(tokDoc, docCat);
		}

		classification.put("VOCABULARY_SIZE", vocabulary.size());

		for (String key : classification.keySet()) {
			int value = classification.get(key);
			System.out.println(key + " " + value);
		}
	}

	public static void incrementKey(String key, int num) {
		int numInstances = (classification.containsKey(key)) ? classification.get(key) : 0;
		numInstances += num;
		classification.put(key, numInstances);
	}

	public static void incLabel(String label) {
		String key = "Y=" + label;
		incrementKey(key, 1);
	}

	public static void incNumTraining() {
		String key = "TRAINING_INSTANCE_COUNT";
		incrementKey(key, 1);
	}

	public static void incTokenLabel(String label, String token) {
		String key = "Y=" + label + "^X=" + token;
		incrementKey(key, 1);
	}

	public static void incLabelCount(String label, int wordCount) {
		String key = "Y=" + label + "^" + "W";
		incrementKey(key, wordCount);
	}

	public static void classifyDoc(Vector<String> doc, Vector<String> labels) {
		if (doc.size() > 0) {
			doc.remove(0);
		}
		for (String label : labels) {
			incLabel(label);
			incNumTraining();
			for (String word : doc) {
				incTokenLabel(label, word);
				if (!vocabulary.contains(word)) {
					vocabulary.add(word);
				}
			}
			incLabelCount(label, doc.size());
		}
	}
}