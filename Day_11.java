/*2685. Count the Number of Complete Components */

/*You are given an integer n. There is an undirected graph with n vertices, numbered from 0 to n - 1. You are given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting vertices ai and bi.

Return the number of complete connected components of the graph.

A connected component is a subgraph of a graph in which there exists a path between any two vertices, and no vertex of the subgraph shares an edge with a vertex outside of the subgraph.

A connected component is said to be complete if there exists an edge between every pair of its vertices.

 

Example 1:



Input: n = 6, edges = [[0,1],[0,2],[1,2],[3,4]]
Output: 3
Explanation: From the picture above, one can see that all of the components of this graph are complete.
Example 2:



Input: n = 6, edges = [[0,1],[0,2],[1,2],[3,4],[3,5]]
Output: 1
Explanation: The component containing vertices 0, 1, and 2 is complete since there is an edge between every pair of two vertices. On the other hand, the component containing vertices 3, 4, and 5 is not complete since there is no edge between vertices 4 and 5. Thus, the number of complete components in this graph is 1.
 

 */

 class Solution {
    int[] parent;
    int[] rank;

    public int countCompleteComponents(int n, int[][] edges) {
        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int[] edge : edges) {
            union(edge[0], edge[1]);
        }

        Map<Integer, Set<Integer>> componentVertices = new HashMap<>();
        Map<Integer, Integer> componentEdges = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int root = find(i);
            componentVertices.computeIfAbsent(root, k -> new HashSet<>()).add(i);
        }

        for (int[] edge : edges) {
            int root = find(edge[0]);
            componentEdges.put(root, componentEdges.getOrDefault(root, 0) + 1);
        }

        int completeCount = 0;
        for (int root : componentVertices.keySet()) {
            int numVertices = componentVertices.get(root).size();
            int expectedEdges = numVertices * (numVertices - 1) / 2;

            if (componentEdges.getOrDefault(root, 0) == expectedEdges) {
                completeCount++;
            }
        }

        return completeCount;
    }

    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) return;

        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }
}