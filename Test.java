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

public class Test {
	public static void main(String[] args) throws Exception {
		HashTable hash = null;
		Scanner inputs = new Scanner(System.in);
		System.out.println("Specify table size: ");
		int tableSize = Integer.parseInt(inputs.next());
		System.out.println("Specify Hash type: ");
		String hashType = inputs.next();
		while(!(hashType.toLowerCase().equals("linear") || hashType.toLowerCase().equals("quadratic") || hashType.toLowerCase().equals("chaining"))){
			System.out.println("Invalid table, Please try again.");
			hashType = inputs.next();
		}
		System.out.println("Specify the input data file followed by the file type (e.g. cleaned_data.csv): ");
		String file = inputs.next();
		System.out.println("Specify the number of keys you wish to search for: ");
		int keys = Integer.parseInt(inputs.next());
		if(hashType.toLowerCase().equals("linear")) {
			hash = new Linear(tableSize);
		}
		if(hashType.toLowerCase().equals("quadratic")) {
			hash = new Quadratic(tableSize);
		}
		if(hashType.toLowerCase().equals("chaining")) {
			hash = new Chaining(tableSize);
		}

		Scanner data = new Scanner(new File(file));
		data.nextLine();
		data.useDelimiter(",");
		String[] values = new String[3];
		String[] dates = new String[600];
		int d = 0;
		HashEntry entry;

		while(data.hasNextLine()) {
			values[0] = data.next();
			values[1] = data.next();
			data.next();
			values[2] = data.next();
			entry = new HashEntry(values);
			//System.out.println(values[1]);
			hash.insert(entry);
			data.nextLine();
			dates[d] = values[0];
			d++;
		}
		System.out.println(hash.tableSize);
		System.out.println("The load factor is: "+ hash.getLoadFactor());
		System.out.println("The number of probes is: "+hash.probe);

		String[] search = new String[keys];

		int searches = keys-1;
		List<String> dateList = Arrays.asList(dates);
		Collections.shuffle(dateList);
		String[] shuffled = dateList.toArray(dates);
		String[] searchArr = new String[keys];
		int k = 0;
		//System.out.println(dates.size());
		//System.out.println(dates[searches]);

		while(!(searches<0)) {
			if(shuffled[k] != null){
				searchArr[searches] = shuffled[k];
 			 	System.out.println(searchArr[searches]);
 			 	searches--;
			}
			 k++;
		}
		searches = 0;

		//System.out.println(searches);
		//System.out.println(searchArr[searches]);

		while(searches<keys) {
			System.out.println(searchArr[searches]);
			//System.out.println(hash.find(searchArr[searches]));
			hash.printDateTime(searchArr[searches]);
			searches++;
			//System.out.println(searches);
		}

		data.close();
		inputs.close();
	}



}
