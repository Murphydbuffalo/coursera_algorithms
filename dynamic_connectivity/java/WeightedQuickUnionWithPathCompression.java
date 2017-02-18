package java;

public class WeightedQuickUnionWithPathCompression {
	private int[] parentIds;
	private int[] sizes;
	
	WeightedQuickUnionWithPathCompression(int n) {
		parentIds = new int[n];
		sizes = new int[n];
		
		for (int i = 0; i < n; i++) {
			parentIds[i] = i;
			sizes[i] = 1;
		}
	}
	
	// This implementation is identical to WeightedQuickUnion except that we take
	// the opportunity, while looping through elements in root(), to update elements'
	// parents to be one layer higher up in the tree. This allows us to keep significantly
	// shorter trees.
	private int root(int i) {
		while(parentIds[i] != i) {
			parentIds[i] = parentIds[parentIds[i]];
			i = parentIds[i];
		}
		
		return i;
	}

	public boolean isConnected(int a, int b) {
		return root(a) == root(b);
	}
	
	public int connect(int a, int b) {
		int rootA = root(a);
		int rootB = root(b);
	
		if (rootA == rootB) {
			return rootB;
		} else if (sizes[rootA] < sizes[rootB]) {
			parentIds[rootA] = rootB;
			sizes[rootB] = sizes[rootB] + sizes[rootA];
			
			return rootB;
		} else {
			parentIds[rootB] = rootA;
			sizes[rootA] = sizes[rootA] + sizes[rootB];
			
			return rootA;
		}
	}
}