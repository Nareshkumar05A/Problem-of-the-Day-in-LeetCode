
// 2929. Distribute Candies Among Children II
/*You are given two positive integers n and limit.

Return the total number of ways to distribute n candies among 3 children such that no child gets more than limit candies.

 

Example 1:

Input: n = 5, limit = 2
Output: 3
Explanation: There are 3 ways to distribute 5 candies such that no child gets more than 2 candies: (1, 2, 2), (2, 1, 2) and (2, 2, 1).
Example 2:

Input: n = 3, limit = 3
Output: 10
Explanation: There are 10 ways to distribute 3 candies such that no child gets more than 3 candies: (0, 0, 3), (0, 1, 2), (0, 2, 1), (0, 3, 0), (1, 0, 2), (1, 1, 1), (1, 2, 0), (2, 0, 1), (2, 1, 0) and (3, 0, 0).
 

Constraints:

1 <= n <= 106
1 <= limit <= 106
 */

 import java.util.*;

public class CandyDistribution {

    static final int MOD = (int)1e9 + 7;
    static final int MAX = 3000000;

    static long[] fact = new long[MAX + 1];
    static long[] invFact = new long[MAX + 1];

    static void precomputeFactorials() {
        fact[0] = invFact[0] = 1;
        for (int i = 1; i <= MAX; i++) {
            fact[i] = (fact[i - 1] * i) % MOD;
        }
        invFact[MAX] = modInverse(fact[MAX]);
        for (int i = MAX - 1; i >= 1; i--) {
            invFact[i] = (invFact[i + 1] * (i + 1)) % MOD;
        }
    }

    static long modPow(long a, long b) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) res = (res * a) % MOD;
            a = (a * a) % MOD;
            b >>= 1;
        }
        return res;
    }

    static long modInverse(long a) {
        return modPow(a, MOD - 2);
    }

    static long nCr(int n, int r) {
        if (n < 0 || r < 0 || r > n) return 0;
        return (((fact[n] * invFact[r]) % MOD) * invFact[n - r]) % MOD;
    }

    static long countWays(int n, int limit) {
        long total = nCr(n + 2, 2);
        long subtract = 0;

        subtract += 3 * nCr(n - (limit + 1) + 2, 2);
        subtract -= 3 * nCr(n - 2 * (limit + 1) + 2, 2);
        subtract += nCr(n - 3 * (limit + 1) + 2, 2);

        subtract = ((subtract % MOD) + MOD) % MOD;
        return ((total - subtract + MOD) % MOD);
    }

    public static void main(String[] args) {
        precomputeFactorials();
        System.out.println(countWays(5, 2));  // Output: 3
        System.out.println(countWays(3, 3));  // Output: 10
    }
}
