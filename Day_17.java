/*2503. Maximum Number of Points From Grid Queries */

/*You are given an m x n integer matrix grid and an array queries of size k.

Find an array answer of size k such that for each integer queries[i] you start in the top left cell of the matrix and repeat the following process:

If queries[i] is strictly greater than the value of the current cell that you are in, then you get one point if it is your first time visiting this cell, and you can move to any adjacent cell in all 4 directions: up, down, left, and right.
Otherwise, you do not get any points, and you end this process.
After the process, answer[i] is the maximum number of points you can get. Note that for each query you are allowed to visit the same cell multiple times.

Return the resulting array answer.

 

Example 1:


Input: grid = [[1,2,3],[2,5,7],[3,5,1]], queries = [5,6,2]
Output: [5,8,1]
Explanation: The diagrams above show which cells we visit to get points for each query.
Example 2:


Input: grid = [[5,2,1],[1,1,2]], queries = [3]
Output: [0]
Explanation: We can not get any points because the value of the top left cell is already greater than or equal to 3.
  */

  import java.util.*;

class Solution {
    public int[] maxPoints(int[][] grid, int[] queries) {
        int m = grid.length, n = grid[0].length;
        int[] sortedQueries = queries.clone();
        Arrays.sort(sortedQueries);
        Map<Integer, Integer> queryResult = new HashMap<>();
        int[] answer = new int[queries.length];

        int[][] directions = {{0,1}, {0,-1}, {1,0}, {-1,0}};
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        Set<String> visited = new HashSet<>();

        minHeap.offer(new int[]{grid[0][0], 0, 0});
        visited.add("0,0");

        int points = 0;

        for (int query : sortedQueries) {
            while (!minHeap.isEmpty() && minHeap.peek()[0] < query) {
                int[] cell = minHeap.poll();
                int val = cell[0], r = cell[1], c = cell[2];

                points++;

                for (int[] dir : directions) {
                    int nr = r + dir[0], nc = c + dir[1];
                    String key = nr + "," + nc;

                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && !visited.contains(key)) {
                        visited.add(key);
                        minHeap.offer(new int[]{grid[nr][nc], nr, nc});
                    }
                }
            }
            queryResult.put(query, points);
        }

        for (int i = 0; i < queries.length; i++) {
            answer[i] = queryResult.get(queries[i]);
        }

        return answer;
    }
}