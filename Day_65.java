/*1931. Painting a Grid With Three Different Colors */

/*You are given two integers m and n. Consider an m x n grid where each cell is initially white. You can paint each cell red, green, or blue. All cells must be painted.

Return the number of ways to color the grid with no two adjacent cells having the same color. Since the answer can be very large, return it modulo 109 + 7.

 

Example 1:


Input: m = 1, n = 1
Output: 3
Explanation: The three possible colorings are shown in the image above.
Example 2:


Input: m = 1, n = 2
Output: 6
Explanation: The six possible colorings are shown in the image above.
Example 3:

Input: m = 5, n = 5
Output: 580986
  */

  public class Solution 
{
    private static final int MOD = 1_000_000_007;

    public int colorTheGrid(int m, int n) 
    {
        List<int[]> states = new ArrayList<>();
        generateStates(m, new int[m], 0, states);

        Map<String, List<String>> transitions = new HashMap<>();
        for (int[] a : states) 
        {
            String keyA = Arrays.toString(a);
            transitions.putIfAbsent(keyA, new ArrayList<>());
        
            for (int[] b : states) 
            {
                if (isCompatible(a, b)) 
                {
                    transitions.get(keyA).add(Arrays.toString(b));
                }
            }
        }

        Map<String, Integer> dp = new HashMap<>();
        for (int[] state : states) 
        {
            dp.put(Arrays.toString(state), 1);
        }

        for (int col = 1; col < n; col++) 
        {
            Map<String, Integer> newDp = new HashMap<>();
            for (String prev : dp.keySet()) 
            {
                int count = dp.get(prev);
                for (String next : transitions.get(prev)) 
                {
                    newDp.put(next, (newDp.getOrDefault(next, 0) + count) % MOD);
                }
            }

            dp = newDp;
        }

        int result = 0;
        for (int val : dp.values()) 
        {
            result = (result + val) % MOD;
        }
        
        return result;
    }

    private void generateStates(int m, int[] current, int row, List<int[]> states) 
    {
        if (row == m) 
        {
            states.add(Arrays.copyOf(current, m));
            return;
        }
        
        for (int color = 1; color <= 3; color++) 
        {
            if (row > 0 && current[row - 1] == color)
            {
                continue;  // avoid vertical conflict
            } 
            
            current[row] = color;
            generateStates(m, current, row + 1, states);
        }
    }
    private boolean isCompatible(int[] a, int[] b) 
    {
        for (int i = 0; i < a.length; i++) 
        {
            if (a[i] == b[i])
            {
                return false;
            } 
        }

        return true;
    }
}