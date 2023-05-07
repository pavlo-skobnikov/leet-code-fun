package leet.code.seventyfive;

public class Level1 {

  /* DAY 1 */

  /**
   * 1480. Running Sum of 1d Array
   * https://leetcode.com/problems/running-sum-of-1d-array/?envType=study-plan&id=level-1
   *
   * <p>Given an array nums. We define a running sum of an array as runningSum[i] =
   * sum(nums[0]…nums[i]).
   *
   * <p>Return the running sum of nums.
   */
  public int[] runningSum(int[] nums) {
    // Doesn't allocate any new memory by mutating the input array.
    for (int i = 1; i < nums.length; i++) {
      nums[i] = nums[i] + nums[i - 1];
    }

    return nums;
  }

  /**
   * 724. Find Pivot Index
   * https://leetcode.com/problems/find-pivot-index/?envType=study-plan&id=level-1
   *
   * <p>Given an array of integers nums, calculate the pivot index of this array.
   *
   * <p>The pivot index is the index where the sum of all the numbers strictly to the eft of the
   * index is equal to the sum of all the numbers strictly to the index's right.
   *
   * <p>If the index is on the left edge of the array, then the left sum is 0 because here are no
   * elements to the left. This also applies to the right edge of the array.
   *
   * <p>Return the leftmost pivot index. If no such index exists, return -1.
   */
  public int pivotIndex(int[] nums) {
    // Initialize result value.
    int leftmostPivotIndex = -1;

    // Initialize left and right sums.
    int leftSum = 0;
    int rightSum = 0;

    // Calculate right sum, excluding the first element.
    for (int i = 1; i < nums.length; i++) {
      rightSum += nums[i];
    }

    // Main implementation
    int iterationIndex = 0;

    do {
      // Base case to stop iteration. Whenever leftSum == rightSum, we have found the
      // pivot index. Will return the leftmost pivot index due to starting from the
      // beginning of the array.
      if (leftSum == rightSum) {
        leftmostPivotIndex = iterationIndex;

        break;
      }

      // Add the current element to the left sum and subtract the next element from
      // the right sum.
      leftSum += nums[iterationIndex];
      rightSum -= nums[iterationIndex + 1];

      // Don't forget to increment the iteration index ;)
      iterationIndex++;
    } while (iterationIndex < nums.length - 1);

    // Final case to check if the pivot index is the last element in the array.
    if (leftmostPivotIndex == -1 && leftSum == 0) {
      leftmostPivotIndex = nums.length - 1;
    }

    return leftmostPivotIndex;
  }
}
