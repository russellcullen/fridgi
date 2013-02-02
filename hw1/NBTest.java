import java.io.*;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Vector;
import java.lang.Math;

public class NBTest {

	private static HashMap<String, Integer> classification;

	public static void main (String[] args) {

		classification = new HashMap<String, Integer>();

		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			String keyVal = scanner.nextLine();
			insertLineIntoHashMap(keyVal);
		}

		BufferedReader br;

		try {
			String doc;
			br = new BufferedReader(new FileReader(args[1]));
			int totalNumDocs = 0;
			int numCorrect = 0;

			while ((doc = br.readLine()) != null) {	
				Vector<String> tokDoc = NBUtils.tokenizeDoc(doc);
				String docCat = doc.substring(0, doc.indexOf("\t"));
				docCat = docCat.replaceAll(",", ", ");
				String prob = classify(tokDoc);
				System.out.println("[" + docCat + "]" + "\t" + prob);

				if (isCorrect(docCat, prob)) {
					numCorrect++;
				}
				totalNumDocs++;
			}
			double accuracy = (double)numCorrect/(double)totalNumDocs * 100;
			System.out.println("Percent correct : " + numCorrect + "/" + totalNumDocs + "=" + accuracy + "%");
			br.close();
		} catch (IOException e) {
			return;
		}
	}

	private static boolean isCorrect(String categories, String classified) {
		String bestLabel = classified.split("\t")[0];
		return categories.contains(bestLabel);
	}

	private static String classify(Vector<String> tokDoc) {
		if (tokDoc.size() > 0)
			tokDoc.remove(0);

		double bestProb = Integer.MIN_VALUE;
		String bestLabel = "";

		for (String label : NBUtils.CAT) {
			double currentProb = 0;
			for (String word : tokDoc) {
				String key = "Y=" + label + "^X=" + word;
				int qj = classification.get("VOCABULARY_SIZE");
				int wordCount = (classification.containsKey(key)) ? classification.get(key) : 0;
				int currentLabel = classification.get("Y=" + label);
				double prob = (double)(wordCount + 1)/(double)(currentLabel + qj);
				currentProb += prob;
			}

			double qy = 1.0/(double)classification.get("TRAINING_INSTANCE_COUNT");
			double probY = (double)classification.get("Y=" + label) / (double)classification.get("TRAINING_INSTANCE_COUNT");
			double logCurrentProb = Math.log(currentProb) + Math.log(probY);
			if (logCurrentProb > bestProb) {
				bestProb = logCurrentProb;
				bestLabel = label;
			}
		}

		return bestLabel + "\t" + bestProb;	
	}

	private static void insertLineIntoHashMap(String line) {
		String[] lineArr = line.split("\\s+");
		String key = lineArr[0];
		int value = Integer.parseInt(lineArr[1]);
		classification.put(key, value);
	}
}