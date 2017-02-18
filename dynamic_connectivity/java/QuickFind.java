package java;

public class QuickFind {
	// Represent connected components as groups of elements in the array with equal integer IDs. 
	// Initially there are N connected components, each containing a single element and having
	// an ID equal to the component's index in the array.
	//
	// This is a flat data structure, easy to compare (isConnected())
	// but difficult to update/write to because you need to update the
	// connected component ID of potentially many elements in the array.
	private int[] ids;
	
	public QuickFind(int n) {
		ids = new int[n];

		for(int i = 0; i < n; i++) {
			ids[i] = i;
		}
	}
	
	public boolean isConnected(int a, int b) {
		return ids[a] == ids[b];
	}
	
	// Merge connected components by overwriting existing IDs
	// of all elements in component A with the ID of component B.
	public int connect(int a, int b) {
		int idA = a;
		int idB = b;
		
		for (int i = 0; i < ids.length; i++) {
			if (ids[i] == idA) {
				ids[i] = idB;
			}
		}
		
		return idB;
	}

}
