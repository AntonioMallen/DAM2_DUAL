package Ejemplos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SetsExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set<String> hash_Set2 = new LinkedHashSet<String>();



		// Adding elements to the Set
		// using add() method
		hash_Set2.add("Geeks");
		hash_Set2.add("For");
		hash_Set2.add("Geeks");
		hash_Set2.add("Example");
		hash_Set2.add("Set");
		Set<String> hash_Set1 = new TreeSet<String>(hash_Set2);
		
		Set<String> hash_Set = new HashSet<String>(hash_Set2);
		List<String> lista = new ArrayList<String>(hash_Set2);
		lista.addAll(hash_Set2);
		// Printing elements of HashSet object
		System.out.println(hash_Set2);
		System.out.println(lista);
		System.out.println(hash_Set);
		System.out.println(hash_Set1);

	}

}
