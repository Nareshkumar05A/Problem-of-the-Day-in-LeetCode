/*416. Partition Equal Subset Sum */

/*Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or false otherwise.

 

Example 1:

Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: nums = [1,2,3,5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.
  */

  import java.util.*;

class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0, n = nums.length;
        
        for (int i = 0; i < n; i++)
            sum += nums[i];
        
        if (sum % 2 != 0) 
            return false;

        boolean[] dp = new boolean[10001];
        dp[0] = true;
        for (int num : nums) {
            for (int j = 10000; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
            if (dp[sum / 2]) 
                return true;
        }
        return dp[sum / 2];
    }
}