public class SlidingWindow {

    // Given an Array of N element, return the maximum subarray with sum of length = K
    // Eg: arr[] = {-3, 4, -2, 5, 3, -2, 8, 2, -1, 4}
    //     K = 5, So divied the array of K element
    // arr              sum     max
    // [-3,4,-2,5,3]    7       7
    // [4,-2,5,3,-2]    8       8
    // [-2,5,3,-2,8]    12      12
    // [5,3,-2,8,2]     16      16
    // [3,-2,8,2,-1]    10      16
    // [-2,8,2,-1,4]    11      16
    // So, the max of all element is 16

    // Approach 1 Brute Force :
    public static int BFslidingWindow(int[] nums, int k){
        int maxSum = nums[0];
        int size = nums.length;
        for (int i = 0; i < size - k + 1; i += 1) {
            int sumOfKEle = 0;
            for (int j = i ; j < k + i ; j += 1){
                sumOfKEle += nums[i];
            }
            maxSum = (sumOfKEle > maxSum)?sumOfKEle:maxSum;
        }
        return maxSum;
    }

    // Approach 2: Sliding Window
    // Iterate the array to K and find the sum of k ele in array
    // Then, Iterate the entire array from 1 to n-1,
    // NewSumSubArray = OldSubArraySum - array[start] + array[end]

    // K = end - start + 1
    // TC: O(N + K) SC: O(1)
    public static int maxSubSum(int[] nums, int k){
        int sumOfKEle = 0;
        for (int i = 0; i < k; i++) {
            sumOfKEle += nums[i];
        }
        int maxSum = sumOfKEle;
        for (int start = 1; start < nums.length - k + 1; start += 1) {
            int currSum = sumOfKEle - nums[start - 1] + nums[k + start - 1];
            maxSum = Math.max(currSum, maxSum);
            sumOfKEle = currSum;
        }
        return maxSum;
    }

    // Problem Description 1:
    // You are given an integer array C of size A. Now you need to find a subarray (contiguous elements) so that the sum of contiguous elements is maximum.
    // But the sum must not exceed B.
    // Input : A = 5, B = 12, C = [2, 1, 3, 4, 5]   Output : 12
    // Sliding Window tecnique
    public static int contiguousSumSubArray(int size, int greaterVal, int[] nums){
        int maxEle = 0;
        int left = 0;
        int currSum = 0;
        for (int right = 0 ; right < size ; right += 1){
            currSum += nums[right];
            while (currSum > greaterVal && left <= right){
                currSum -= nums[left];
                left += 1;
            }
            maxEle = (maxEle < currSum)?currSum:maxEle;
        }
        return maxEle;
    }

    // Problem Description 2:
    // Given an array of integers A and an integer B, find and return the minimum number of swaps required to bring all the numbers less than or equal to B together.
    // Note: It is possible to swap any two elements, not necessarily consecutive.
    // Input : A = [1, 12, 10, 3, 14, 10, 5], B = 8     Output : 2

    // Explanation:
    // A = [1, 12, 10, 3, 14, 10, 5]
    // After swapping  12 and 3, A => [1, 3, 10, 12, 14, 10, 5].
    // After swapping  the first occurence of 10 and 5, A => [1, 3, 5, 12, 14, 10, 10].
    // Now, all elements less than or equal to 8 are together.

    // Approach :
    // 1. Find the window size
    // 2. Add the count if arr of val <= k
    // 3. Iterate the arr from 1 to n - 1, Sub the count when arr[s-1] <= k and
    //    count += 1 when arr[windowSize+start-1]

    public static int minimumSwap(int[] nums, int greaterVal){
        int numsSize = nums.length;
        int windowSize = 0;
        for (int i = 0; i < nums.length; i += 1) {
            if(nums[i] <= greaterVal){
                windowSize += 1;
            }
        }
        int countOfDigit = 0;
        for(int i = 0 ; i < windowSize ; i += 1){
            if (nums[i] <= greaterVal){
                countOfDigit += 1;
            }
        }
        int minSwap = 1_000_000;
        for (int start = 1; start < numsSize - windowSize + 1; start++) {
            if (nums[start - 1] <= greaterVal){
                countOfDigit -= 1;
            }
            if (nums[windowSize + start - 1] <= greaterVal){
                countOfDigit += 1;
            }
            int swaps = windowSize - countOfDigit;
            minSwap = Math.min(minSwap,swaps);
        }
        if (minSwap == 1_000_000){
            return 0;
        }
        return minSwap;
    }

    public static void main(String[] args) {
//        int[] nums = {2,1,3,4,5};
//        int greaterVal = 12;
//        int size = 5;
//        System.out.println(contiguousSumSubArray(size,greaterVal,nums));

//        int[] nums = {-3, 4, -2, 5, 3, -2, 8, 2, -1, 4};
//        int k = 5;
//        System.out.println(maxSubSum(nums,k));
        int[] A = {1, 12, 10, 3, 14, 10, 5};
        int B = 8;
        System.out.println(minimumSwap(A,B));
    }
}
