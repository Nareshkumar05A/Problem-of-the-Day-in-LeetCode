/*2206. Divide Array Into Equal Pairs */

/* You are given an integer array nums consisting of 2 * n integers.

You need to divide nums into n pairs such that:

Each element belongs to exactly one pair.
The elements present in a pair are equal.
Return true if nums can be divided into n pairs, otherwise return false.

 

Example 1:

Input: nums = [3,2,3,2,2,2]
Output: true
Explanation: 
There are 6 elements in nums, so they should be divided into 6 / 2 = 3 pairs.
If nums is divided into the pairs (2, 2), (3, 3), and (2, 2), it will satisfy all the conditions.
Example 2:

Input: nums = [1,2,3,4]
Output: false
Explanation: 
There is no way to divide nums into 4 / 2 = 2 pairs such that the pairs satisfy every condition.
 

*/


import java.util.*;
class Pattern
{
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        System.out.println("Enter the array size:");
        int n=scan.nextInt();
        System.out.printf("Enter the array values:");
        int[] nums=new int[n];
        for(int i=0;i<n;i++)
        {
            nums[i]=scan.nextInt();
        }
        Arrays.sort(nums);
        for(int i=0;i<n-1;i+=2)
        {
            if(nums[i]!=nums[i+1])
            {
                System.out.println("false");
                break;
            }
            else{
                System.out.println("true");
            }
        }

    }
}