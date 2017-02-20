public class WeightedQuickUnion {
	private int[] parentIds;
	private int[] sizes;
	
	// As with un-weighted QuickFind we represent our connected components as trees,
	// however we now also keep track of the size of each connected component. This
	// allows us to identify which of two trees is smaller when merging connected 
	// components. To reduce the depth of our trees (and thus improve the performance
	// of isConnected()) we will always make the smaller of the two trees the child
	// of the larger tree.
	WeightedQuickUnion(int n) {
		parentIds = new int[n];
		sizes = new int[n];
		
		for (int i = 0; i < n; i++) {
			parentIds[i] = i;
			sizes[i] = 1;
		}
	}
	
	private int root(int i) {
		while(parentIds[i] != i) {
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
	
	public static void main(String[] args) {
		WeightedQuickUnionWithPathCompression elements = new WeightedQuickUnionWithPathCompression(1000);
		elements.connect(1, 5);
		elements.connect(1, 88);
		elements.connect(1, 607);
		elements.connect(33, 55);
		elements.connect(99, 55);
		elements.connect(3, 1);
		elements.connect(99, 3);


		System.out.println("Are 1 and 5 connected?");
		System.out.println(String.valueOf(elements.isConnected(1, 5)));
		
		System.out.println("Are 1 and 99 connected?");
		System.out.println(String.valueOf(elements.isConnected(1, 99)));
		
	 	System.out.println("Are 1 and 55 connected?");
		System.out.println(String.valueOf(elements.isConnected(1, 55)));
		
		System.out.println("Are 1 and 999 connected?");
		System.out.println(String.valueOf(elements.isConnected(1, 999)));
		
		System.out.println("Are 1 and 2 connected?");
		System.out.println(String.valueOf(elements.isConnected(1, 2)));
	}
}
