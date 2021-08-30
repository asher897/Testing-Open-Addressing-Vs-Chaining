import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Main {
	public static void main(String[] args) throws Exception {
		Scanner inputs = new Scanner(System.in);
		System.out.println("Please choose which programme to run: ");
		System.out.println("1. Test results of specified HashTable.");
		System.out.println("2. Test the Linear, Quadratic and Chaining HashTable results against each other.");

		int choice = inputs.nextInt();

		System.out.println("Specify table size: ");
		int tableSize = Integer.parseInt(inputs.next());

		System.out.println("Specify the input data file followed by the file type (e.g. cleaned_data.csv): ");
		String file = "../resources/" + inputs.next();
		while (!(new File(file).exists())) {
			System.out.println("The specified file does not exist, please try again: ");
			file = inputs.next();
		}

		System.out.println("Specify the number of keys you wish to search for: ");
		int keys = Integer.parseInt(inputs.next());

		Scanner data = new Scanner(new File(file));
		data.nextLine();
		data.useDelimiter(",");
		String[] values = new String[3];
		String[] dates = new String[600];
		int d = 0;
		HashEntry entry;

		if (choice == 1) {

			System.out.println("Specify Hash type: ");
			String hashType = inputs.next();
			while (!(hashType.toLowerCase().equals("linear") || hashType.toLowerCase().equals("quadratic")
					|| hashType.toLowerCase().equals("chaining"))) {
				System.out.println("Invalid table, Please try again.");
				hashType = inputs.next();
			}

			HashTable table;
			if (hashType.toLowerCase().equals("linear")) {
				table = new Linear(tableSize);
			} else if (hashType.toLowerCase().equals("quadratic")) {
				table = new Quadratic(tableSize);
			} else {
				table = new Chaining(tableSize);
			}

			while (data.hasNextLine()) {
				values[0] = data.next();
				values[1] = data.next();
				data.next();
				values[2] = data.next();
				entry = new HashEntry(values);
				table.insert(entry);
				data.nextLine();
				dates[d] = values[0];
				d++;
			}

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

			while (searches < keys) {
				System.out.println(searchArr[searches]);
				table.printDateTime(searchArr[searches]);
				searches++;
			}

			System.out.println("The load factor is: " + table.getLoadFactor());
			System.out.println("The number of search probes is: " + table.probe);
			System.out.println("The number of insert probes is: " + table.inserts);

			data.close();

		}

		else {
			BufferedWriter results = new BufferedWriter(new FileWriter("../resources/Results.csv", true));

			Linear linear = new Linear(tableSize);

			Quadratic quad = new Quadratic(tableSize);

			Chaining chain = new Chaining(tableSize);

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

			while (searches < keys) {
				System.out.println(searchArr[searches]);
				System.out.print("Quadratic: ");
				quad.printDateTime(searchArr[searches]);
				System.out.print("Chaining: ");
				chain.printDateTime(searchArr[searches]);
				System.out.print("Linear: ");
				linear.printDateTime(searchArr[searches]);
				searches++;
			}

			double keysD = (double) keys;

			System.out.println("Results: ");
			System.out.println("Table Size: " + tableSize);
			System.out.println("Load factor: " + linear.getLoadFactor() + "\nInserts: " + "\nLinear: "
					+ linear.getInserts() + " Chaining: " + chain.getInserts() + " Quadratic: " + quad.getInserts()
					+ "\nProbes: \nLinear: " + linear.getProbe() + " Chaining: " + chain.getProbe() + " Quadratic: "
					+ quad.getProbe());

			results.append(tableSize + "," + linear.getLoadFactor() + "," + linear.getInserts() + ","
					+ quad.getInserts() + "," + chain.getInserts() + "," + linear.getProbe() + "," + quad.getProbe()
					+ "," + chain.getProbe() + "," + (linear.getProbe() / keysD) + "," + (quad.getProbe() / keysD) + ","
					+ (chain.getProbe() / keysD) + "," + linear.getLongProbe() + "," + quad.getLongProbe() + ","
					+ chain.getLongProbe() + "\n");

			data.close();
			results.close();
		}
		inputs.close();
	}

}
