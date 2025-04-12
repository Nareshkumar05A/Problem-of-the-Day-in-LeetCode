/*3272. Find the Count of Good Integers */

/*You are given two positive integers n and k.

An integer x is called k-palindromic if:

x is a palindrome.
x is divisible by k.
An integer is called good if its digits can be rearranged to form a k-palindromic integer. For example, for k = 2, 2020 can be rearranged to form the k-palindromic integer 2002, whereas 1010 cannot be rearranged to form a k-palindromic integer.

Return the count of good integers containing n digits.

Note that any integer must not have leading zeros, neither before nor after rearrangement. For example, 1010 cannot be rearranged to form 101.

 

Example 1:

Input: n = 3, k = 5

Output: 27

Explanation:

Some of the good integers are:

551 because it can be rearranged to form 515.
525 because it is already k-palindromic.
Example 2:

Input: n = 1, k = 4

Output: 2

Explanation:

The two good integers are 4 and 8.

Example 3:

Input: n = 5, k = 6

Output: 2468

 

 */

 class Solution {
    long res = 0;
    Set<String> visited = new HashSet<>();

    long vectorToNumber(int[] digits) {
        long num = 0;
        for (int d : digits) num = num * 10 + d;
        return num;
    }

    long fact(int n) {
        long res = 1;
        for (int i = 2; i <= n; i++) res *= i;
        return res;
    }

    long totalPermutations(Map<Integer, Integer> freq, int total) {
        long res = fact(total);
        for (int count : freq.values()) res /= fact(count);
        return res;
    }

    long permsWithZero(Map<Integer, Integer> freq, int total) {
        if (!freq.containsKey(0) || freq.get(0) == 0) return 0;
        freq.put(0, freq.get(0) - 1);
        long res = fact(total - 1);
        for (int count : freq.values()) res /= fact(count);
        return res;
    }

    void genPal(int[] palin, int left, int right, int divisor, int total) {
        if (left > right) {
            long val = vectorToNumber(palin);
            if (val % divisor == 0) {
                Map<Integer, Integer> freq = new HashMap<>();
                for (int d : palin) freq.put(d, freq.getOrDefault(d, 0) + 1);
                String key = freq.toString();
                if (!visited.contains(key)) {
                    res += totalPermutations(freq, total) - permsWithZero(new HashMap<>(freq), total);
                    visited.add(key);
                }
            }
            return;
        }

        for (int d = (left == 0 ? 1 : 0); d <= 9; d++) {
            palin[left] = palin[right] = d;
            genPal(palin, left + 1, right - 1, divisor, total);
        }
    }

    public long countGoodIntegers(int total, int divisor) {
        res = 0;
        visited.clear();
        genPal(new int[total], 0, total - 1, divisor, total);
        return res;
    }
}