package java;

public class QuickUnion {
	private int[] parentIds;
	
	// Represent connected components as a tree, where elements belonging to the 
	// same component share the same root ID.
	public QuickUnion(int n) {
		parentIds = new int[n];

		for(int i = 0; i < n; i++) {
			parentIds[i] = i;
		}
	}
	
	private int root(int i) {
		while(i != parentIds[i]) {
			i = parentIds[i];
		}
		
		return i;
	}

	public boolean isConnected(int a, int b) {
		return root(a) == root(b);
	}
	
	// To merge connected components we only need to point one connected component
	// tree to another. This is still inefficient because we can end up building
	// very deep trees that still take ~O(N) time to traverse when calling root().
	public int connect(int a, int b) {
		int rootA = root(a);
		int rootB = root(b);
		parentIds[rootA] = rootB;
		
		return rootB;
	}
}
