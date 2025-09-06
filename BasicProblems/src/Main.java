// Arrays

class Arrays{
    private int[] arr;
    Arrays(int[] arr){
        this.arr = arr;
    }

    // Problem Description 1
    // Given an array A of size N. You need to find the sum of Maximum and Minimum element in the given array.
    public int min_max(){
        int min_ele = arr[0];
        int max_ele = arr[0];

        for(int i : arr){
            if(i < min_ele)
                min_ele = i;
            if(i > max_ele)
                max_ele = i;
        }
        return (min_ele + max_ele);
    }

    // rotation :
    // [1,6,11,..] -> [2,7,12,..] -> [3,8,13,..] -> [4,9,14,..] -> [5,10,15,.]
    // [2,3,4,5,1] -> [3,4,5,1,2] -> [4,5,1,2,3] -> [5,1,2,3,4] -> [1,2,3,4,5]
    // [2,3,4,5,1] -> [3,4,5,1,2] -> [4,5,1,2,3] -> [5,1,2,3,4] -> [1,2,3,4,5]
    // [2,3,4,5,1] -> [3,4,5,1,2] -> [4,5,1,2,3] -> [5,1,2,3,4] -> [1,2,3,4,5]

    // Problem Description 2
    // Given an integer array A of size N and an integer B, you have to return the same array after rotating it B times towards the right.
    public void reverse(int start, int end){
        while(start < end){
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start += 1;
            end += 1;
        }
    }
    public int[] k_rotation(int rotation) // rotation = 2
    {
        if(rotation > arr.length)
            rotation = rotation % arr.length;
        reverse(0, arr.length - 1);  // [5,4,3,2,1]
        reverse(0, rotation - 1);    // [3,4,5,2,1]
        reverse(rotation,arr.length - 1); // [3,4,5,1,2]
        return arr;
    }

    // Problem Description 3
    // Given an integer array A of size N. In one second, you can increase the value of one element by 1.
    // Find the minimum time in seconds to make all elements of the array equal.
    // Input : A = [2, 4, 1, 3, 2], Output : 8
    public int timetoequality()
    {
        int sumToEqual = 0;
        if(arr.length == 0)
            return -1;
        // Find max ele in arr
        int max_ele = arr[0];
        for(int i : arr){
            if(max_ele < i)
                max_ele = i;
        }
        for (int i : arr){
            sumToEqual += max_ele - i;
        }
        return sumToEqual;
    }

    // Problem Description 3
    // Given an array, arr[] of size N, the task is to find the count of array indices such that removing an element
    // from these indices makes the sum of even-indexed and odd-indexed array elements equal.
    // Input : A = [2, 1, 6, 4], Output : 1

    // Approach : Sum of Even Index = Sum of Odd Index
    // iteration(i)     arr         SumOfEven       SumOfOdd
    // 1                [1,6,4]        5                6
    // 2                [2,6,4]        6                6  --> Output is 1st index
    // 3                [2,1,4]        6                1
    // 4                [2,1,6]        8                1

    // Findings:
    // After removing the ith index, Even index before i remains even & Odd index before i remains Odd, But
    //                               Even index after i change to Odd & Odd index after i changed to Even

    // Now, Sum of Even = Sum of Even index before i + Sum of Odd  index after i
    //      Sum of Odd  = Sum of Odd  index before i + Sum of Even index after i

    public int[] evenPrefixSum(){
        int[] evenPrefixArr = new int[arr.length];
        evenPrefixArr[0] = (arr[0] % 2 == 0) ? arr[0] : 0;
        for(int i = 1 ; i < evenPrefixArr.length ; i += 1){
            if(arr[i] % 2 == 0)
                evenPrefixArr[i] = evenPrefixArr[i - 1] + arr[i];
            else
                evenPrefixArr[i] = evenPrefixArr[i - 1];
        }
        return evenPrefixArr;
    }
    public int[] oddPrefixArr(){
        int[] oddPrefixArr = new int[arr.length];
        oddPrefixArr[0] = (arr[0] % 2 != 0) ? arr[0] : 0;
        for (int i = 1 ; i < oddPrefixArr.length ; i += 1){
            if (arr[i] % 2 != 0)
                oddPrefixArr[i] = oddPrefixArr[i - 1] + arr[i];
            else
                oddPrefixArr[i] = oddPrefixArr[i - 1];
        }
        return oddPrefixArr;
    }

    public int specialIndex() {
        int[] evenPrefixArr = evenPrefixSum();
        int[] oddPrefixArr = oddPrefixArr();

        int totalEven = evenPrefixArr[arr.length - 1];
        int totalOdd = oddPrefixArr[arr.length - 1];

        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            // prefix sums up to i-1
            int leftEven = (i > 0) ? evenPrefixArr[i - 1] : 0;
            int leftOdd  = (i > 0) ? oddPrefixArr[i - 1] : 0;

            // suffix sums after i (swap roles!)
            int rightEven = totalEven - evenPrefixArr[i];
            int rightOdd  = totalOdd - oddPrefixArr[i];

            int sumOfEven = leftEven + rightOdd;
            int sumOfOdd  = leftOdd + rightEven;

            if (sumOfEven == sumOfOdd)
                count++;
        }
        return count;
    }


    // Problem Description 4
    // You are given an integer array A of length N.
    // You are also given a 2D integer array B with dimensions M x 2, where each row denotes a [L, R] query.
    // For each query, you have to find the sum of all elements from L to R indices in A (0 - indexed).
    // More formally, find A[L] + A[L + 1] + A[L + 2] +... + A[R - 1] + A[R] for each query.

    public int[] prefixArray(){
        int[] prefix_array = new int[arr.length];
        prefix_array[0] = arr[0];
        for(int i = 1 ; i < arr.length; i += 1){
            prefix_array[1] = prefix_array[i - 1] + arr[i];
        }
        return prefix_array;
    }
    public int[] sumQuery(int[][] twoDArray){
        int[] prefixArray = prefixArray();
        int output[] = new int[2];
        int j = 0;
        for(int[] i : twoDArray){
            int start = i[0];
            int end = i[1];
            int sum = 0;
            if(start != 0) {
                sum = prefixArray[end] - prefixArray[start - 1];
            }
            output[j++] = sum;
        }
        return output;
    }

}
public class Main {
    public static void main(String[] args) {
        int[] arr = {2,1,6,4};
        Arrays arrays = new Arrays(arr);
        int returnOfSpecialIndex = arrays.specialIndex();
        System.out.println(returnOfSpecialIndex);


    }
}