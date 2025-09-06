public class ContributionTechnique {
    // Sum of all element sub array
    // Given an array of integer. Print all sum of possible subarray in an array
    // array = [6,15,4]

    // start        end         array       sum
    // 0            0           [6]         6
    // 0            1           [6,15]      21
    // 0            2           [6,15,4]    25
    // 1            1           [15]        15
    // 1            2           [15,4]      19
    // 2            2           [4]         4
    //                                   --------
    //                                      90
    //                                   --------

    // Approach 1 Brute force
    // TC : O(N ^ 3),  SC : O(1)
    public static void printAllSubArray(int[] array){
        for (int start = 0 ; start < array.length ; start += 1){
            for (int end = start ; end < array.length ; end += 1){
                for (int iterate = start ; iterate < end + 1 ; iterate += 1){
                    System.out.print(array[iterate]+ ' ');
                }
            }
        }
    }

    // Approach 2 Prefix Sum
    // TC : O(N ^ 2), SC : O(N) --> Prefix Array
    public int[] prefixSum(int[] array){
        int[] prefixArray = new int[array.length];
        prefixArray[0] = array[0];
        for(int i = 0 ; i < prefixArray.length ; i += 1){
            prefixArray[i] = prefixArray[i - 1] + array[i];
        }
        int sumOfAllSubArr = 0;
        for (int start = 0 ; start < prefixArray.length ; start += 1){
            for (int end =  start; end < prefixArray.length ; end += 1){
                int curr_sum = 0;
                if (start != 0)
                    curr_sum = prefixArray[end] - prefixArray[start - 1];
                else
                    curr_sum = prefixArray[end];
                sumOfAllSubArr += curr_sum;
            }
        }
        return prefixArray;
    }

    // Approach 3 Carry Forward
    // TC : O(O ^ 2), SC : O(1) --> Space Complexity is 1 by using Carry-Forward
    public int carryforward(int[] array){
        int sumOfAllSubArr = 0;
        for (int start = 0 ; start < array.length ; start += 1){
            int carryForward = 0;
            for (int end = start ; end < array.length ; end += 1){
                carryForward += array[end];
                sumOfAllSubArr += carryForward;
            }
        }
        return sumOfAllSubArr;
    }

    // Approach 4 Optimized approach
    // Possible Subarray
    // start        end         array       sum
    // 0            0           [6]         6
    // 0            1           [6,15]      21
    // 0            2           [6,15,4]    25
    // 1            1           [15]        15
    // 1            2           [15,4]      19
    // 2            2           [4]         4
    // 6  is the part of 3 subarray, So the 6  alone contribute 3 x 6  = 18
    // 15 is the part of 4 subarray, So the 15 alone contribute 4 X 15 = 60
    // 4  is the part of 4 subarray, So the 4  alone contribute 2 x 4  = 16
    // Total sum of contribution of 6, 15, 4 is                          90

    // So, 6 x (Occurrence of 6) + 15 x (Occurrence of 15) + 4 x (Occurence of 4)
    // Need to find Occurence of the element, Lets take 15 Occurence of 15 is 4 So (i + 1) x (N - 1)
    // TC = O(N) SC = O(1)
    public int sumOfSubarray(int[] nums){
        int sumOfSubArray = 0;
        int numsSize = nums.length;
        for(int i = 0 ; i < numsSize ; i += 1){
            int occurence = (i + 1) * (numsSize - 1);
            sumOfSubArray += nums[i] * occurence;
        }
        return sumOfSubArray;
    }
}
