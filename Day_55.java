/*790. Domino and Tromino Tiling */

/*You have two types of tiles: a 2 x 1 domino shape and a tromino shape. You may rotate these shapes.


Given an integer n, return the number of ways to tile an 2 x n board. Since the answer may be very large, return it modulo 109 + 7.

In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.

 

Example 1:


Input: n = 3
Output: 5
Explanation: The five different ways are show above.
Example 2:

Input: n = 1
Output: 1
  */

  class Solution {
    final long mod = 1000000007;

    public int numTilings(int n) {
        return (int)dominoes(0, n, false);
    }

    private long dominoes(int i, int n, boolean possible) {
        if (i == n) return possible ? 0 : 1;
        if (i > n) return 0;

        if (possible) {
            return (dominoes(i + 1, n, false) + dominoes(i + 1, n, true)) % mod;
        }

        return (dominoes(i + 1, n, false)
              + dominoes(i + 2, n, false)
              + 2 * dominoes(i + 2, n, true)) % mod;
    }
}