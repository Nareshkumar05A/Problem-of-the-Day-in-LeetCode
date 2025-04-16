/*2537. Count the Number of Good Subarrays */

/*Given an integer array nums and an integer k, return the number of good subarrays of nums.

A subarray arr is good if there are at least k pairs of indices (i, j) such that i < j and arr[i] == arr[j].

A subarray is a contiguous non-empty sequence of elements within an array.

 

Example 1:

Input: nums = [1,1,1,1,1], k = 10
Output: 1
Explanation: The only good subarray is the array nums itself.
Example 2:

Input: nums = [3,1,4,3,2,2,4], k = 2
Output: 4
Explanation: There are 4 different good subarrays:
- [3,1,4,3,2,2] that has 2 pairs.
- [3,1,4,3,2,2,4] that has 3 pairs.
- [1,4,3,2,2,4] that has 2 pairs.
- [4,3,2,2,4] that has 2 pairs.
  */
class Solution {
    public long countGood(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        int left = 0;
        long pairCount = 0;
        long goodSubarrays = 0;

        for (int right = 0; right < nums.length; right++) {
            int count = freq.getOrDefault(nums[right], 0);
            pairCount += count;
            freq.put(nums[right], count + 1);

            while (pairCount >= k) {
                goodSubarrays += nums.length - right;
                int leftNum = nums[left++];
                int f = freq.get(leftNum);
                freq.put(leftNum, f - 1);
                pairCount -= (f - 1);
            }
        }

        return goodSubarrays;
    }
}