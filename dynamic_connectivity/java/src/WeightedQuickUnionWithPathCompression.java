public class WeightedQuickUnionWithPathCompression {
	private int[] ids;
	private int[] sizes;

	WeightedQuickUnionWithPathCompression(int n) {
		ids = new int[n];
		sizes = new int[n];

		for (int i = 0; i < n; i++) {
			ids[i] = i;
			sizes[i] = 1;
		}
	}

	// This implementation is identical to WeightedQuickUnion except that we take
	// the opportunity, while looping through elements in root(), to update elements'
	// parents to be one layer higher up in the tree. This allows us to keep significantly
	// shorter trees. This is called "path compression".
	private int root(int i) {
		while(ids[i] != i) {
			// Path compression -> shorten trees every time we look up roots.
			ids[i] = ids[ids[i]];
			i = ids[i];
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
			ids[rootA] = rootB;
			sizes[rootB] = sizes[rootB] + sizes[rootA];

			return rootB;
		} else {
			ids[rootB] = rootA;
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
