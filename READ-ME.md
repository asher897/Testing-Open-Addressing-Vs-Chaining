This programme tests the perfomance of HashTable collison adressing methods using open addressing (linear probing and quadratic probing) and Chaining. The performance is based on insertion and probing times.

The Main program makes use of the Chaining, Linear and Quadratic classes which are all inherited from the HashTable class and make use of the HashEntry class.
The HashEntry class is used for each entry into a HashTable. 
The HashTable class is an abstract class for the Chaining, Linear and Quadratic classes to inherit from.

The program makes use of a data file 'cleaned_data.csv'. This can be changed as long as the correct method to read the data is put in place. The data is on te 'resources' directory along with the results written on a csv file.

There are two options to run the program. The first is just getting results from one hash table collision method and the other compares all 3 against each other.
