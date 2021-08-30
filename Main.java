import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.nio.file.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedWriter results = new BufferedWriter(new FileWriter("Results.csv", true));

		HashTable hash = null;
		Scanner inputs = new Scanner(System.in);
		System.out.println("Specify table size: ");
		int tableSize = Integer.parseInt(inputs.next());
		System.out.println("Specify Hash type: ");
		String hashType = inputs.next();
		while (!(hashType.toLowerCase().equals("linear") || hashType.toLowerCase().equals("quadratic")
				|| hashType.toLowerCase().equals("chaining"))) {
			System.out.println("Invalid table, Please try again.");
			hashType = inputs.next();
		}
		System.out.println("Specify the input data file followed by the file type (e.g. cleaned_data.csv): ");
		String file = inputs.next();
		while (!(new File(file).exists())) {
			System.out.println("The specified file does not exist, please try again: ");
			file = inputs.next();
		}
		System.out.println("Specify the number of keys you wish to search for: ");
		int keys = Integer.parseInt(inputs.next());

		Linear linear = new Linear(tableSize);

		Quadratic quad = new Quadratic(tableSize);

		Chaining chain = new Chaining(tableSize);

		Scanner data = new Scanner(new File(file));
		data.nextLine();
		data.useDelimiter(",");
		String[] values = new String[3];
		String[] dates = new String[600];
		int d = 0;
		HashEntry entry;

		while (data.hasNextLine()) {
			values[0] = data.next();
			values[1] = data.next();
			data.next();
			values[2] = data.next();
			entry = new HashEntry(values);
			linear.insert(entry);
			quad.insert(entry);
			chain.insert(entry);
			data.nextLine();
			dates[d] = values[0];
			d++;
		}

		String[] search = new String[keys];

		int searches = keys - 1;
		List<String> dateList = Arrays.asList(dates);
		Collections.shuffle(dateList);
		String[] shuffled = dateList.toArray(dates);
		String[] searchArr = new String[keys];
		int k = 0;

		while (!(searches < 0)) {
			if (shuffled[k] != null) {
				searchArr[searches] = shuffled[k];
				searches--;
			}
			k++;
		}
		searches = 0;

		if (hashType.toLowerCase().equals("linear")) {
			hash = linear;
			while (searches < keys) {
				System.out.println(searchArr[searches]);
				quad.find(searchArr[searches]);
				chain.find(searchArr[searches]);
				hash.printDateTime(searchArr[searches]);
				searches++;
			}
		}
		if (hashType.toLowerCase().equals("quadratic")) {
			hash = quad;
			while (searches < keys) {
				System.out.println(searchArr[searches]);
				linear.find(searchArr[searches]);
				chain.find(searchArr[searches]);
				hash.printDateTime(searchArr[searches]);
				searches++;
			}
		}
		if (hashType.toLowerCase().equals("chaining")) {
			hash = chain;
			while (searches < keys) {
				System.out.println(searchArr[searches]);
				quad.find(searchArr[searches]);
				linear.find(searchArr[searches]);
				hash.printDateTime(searchArr[searches]);
				searches++;
			}
		}

		System.out.println("The load factor is: " + hash.getLoadFactor());
		System.out.println("The number of search probes is: " + hash.probe);
		System.out.println("The number of insert probes is: " + hash.inserts);

		double keysD = (double) keys;

		results.append(tableSize + "," + linear.getLoadFactor() + "," + linear.inserts + "," + quad.inserts + ","
				+ chain.inserts + "," + linear.probe + "," + quad.probe + "," + chain.probe + ","
				+ (linear.probe / keysD) + "," + (quad.probe / keysD) + "," + (chain.probe / keysD) + ","
				+ linear.longProbe + "," + quad.longProbe + "," + chain.longProbe + "\n");

		data.close();
		inputs.close();
		results.close();
	}

}
