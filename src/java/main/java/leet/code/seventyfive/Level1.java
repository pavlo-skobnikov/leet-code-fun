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

  /* DAY 2 */

  /**
   * 205. Isomorphic Strings
   * https://leetcode.com/problems/isomorphic-strings/description/?envType=study-plan&id=level-1
   *
   * <p>Given two strings s and t, determine if they are isomorphic.
   *
   * <p>Two strings s and t are isomorphic if the characters in s can be replaced to get t.
   *
   * <p>All occurrences of a character must be replaced with another character while preserving the
   * order of characters. No two characters may map to the same character, but a character may map
   * to itself.
   */
  public boolean isIsomorphic(String s, String t) {
    // Character encountered maps:
    // - Index is the character's ASCII code.
    // - Value is the aggregated number of indexes when specific character was
    // encountered.
    final int[] sMap = new int[256];
    final int[] tMap = new int[256];

    // Traverse all the characters in the given strings.
    for (int idx = 0; idx < s.length(); idx++) {
      final char sChar = s.charAt(idx);
      final char tChar = t.charAt(idx);

      // If the number of encounters for each character is not equal, it means one of
      // the strings
      // contains at least two different cross-mappings for the character under the
      // index.
      if (sMap[sChar] != tMap[tChar]) return false;

      // Increment the encounter count for each character.
      // We add 1 to the index to avoid the iteration starting index value of 0.
      sMap[sChar] = idx + 1;
      tMap[tChar] = idx + 1;
    }

    // If we reach this point, it means that the strings are isomorphic.
    return true;
  }

  /**
   * 392. Is Subsequence
   * https://leetcode.com/problems/is-subsequence/?envType=study-plan&id=level-1&languageTags=java
   *
   * <p>Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
   *
   * <p>A subsequence of a string is a new string that is formed from the original string by
   * deleting some (can be none) of the characters without disturbing the relative positions of the
   * remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
   */
  public boolean isSubsequence(String s, String t) {
    // Guard-clauses
    if (s.length() == 0) return true;
    if (t.length() == 0) return false;
    if (s.length() > t.length()) return false;

    // Double pointer declaration, pointing to each respective string.
    int sIdx = 0, tIdx = 0;

    // Loop until we reach the end of either string.
    // If we reach the end of the candidate subsequence, then we have found the in
    // the main string. Otherwise, we haven't.
    while (sIdx < s.length() && tIdx < t.length()) {
      // If the characters match, increment the pointer for the searched subsequence.
      if (s.charAt(sIdx) == t.charAt(tIdx)) {
        sIdx++;
      }

      // Always increment the pointer for the main string.
      tIdx++;
    }

    // Due to the loop incrementing logic, if we reached the subsequences's end, the
    // following condition will be true.
    return sIdx == s.length();
  }

  /* DAY 3 */

  /**
   * 21. Merge Two Sorted Lists
   * https://leetcode.com/problems/merge-two-sorted-lists/?envType=study-plan&id=level-1&languageTags=java
   *
   * <p>You are given the heads of two sorted linked lists list1 and list2.
   *
   * <p>Merge the two lists in a one sorted list. The list should be made by splicing together the
   * nodes of the first two lists.
   *
   * <p>Return the head of the merged linked list.
   */

  /* Definition for singly-linked list. */
  public class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

  public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    // Guard-clause to check if either of the lists is null.
    if (list1 == null) return list2;
    if (list2 == null) return list1;

    // Initialize the head of the merged list.
    ListNode mergedListHead;

    // Initialize the pointer for the merged list.
    ListNode mergedListTail;

    // Initialize the pointers for the two lists.
    ListNode list1Ptr = list1;
    ListNode list2Ptr = list2;

    // Decide which list's head will be the merged list's head.
    if (list1.val <= list2.val) {
      mergedListHead = new ListNode(list1.val);
      list1Ptr = list1Ptr.next;
    } else {
      mergedListHead = new ListNode(list2.val);
      list2Ptr = list2Ptr.next;
    }

    // Set the pointer to the merged list's head.
    mergedListTail = mergedListHead;

    // Iteratively step through the lists until we reach the end of either one.
    while (list1Ptr != null && list2Ptr != null) {
      // Create a new node for the merged list.
      mergedListTail.next = new ListNode();

      // Assign the next node to the merged list's tail.
      if (list1Ptr.val <= list2Ptr.val) {
        mergedListTail.next = list1Ptr;
        list1Ptr = list1Ptr.next;
      } else {
        mergedListTail.next = list2Ptr;
        list2Ptr = list2Ptr.next;
      }

      mergedListTail = mergedListTail.next;
    }

    // If the first list has remaining nodes, add them to the merged list.
    if (list1Ptr != null) {
      mergedListTail.next = list1Ptr;
    }

    // If the second list has remaining nodes, add them to the merged list.
    if (list2Ptr != null) {
      mergedListTail.next = list2Ptr;
    }

    // Return the head of the merged list.
    return mergedListHead;
  }

  /**
   * 206. Reverse Linked List
   * https://leetcode.com/problems/reverse-linked-list/?envType=study-plan&id=level-1&languageTags=java
   *
   * <p>Given the head of a singly linked list, reverse the list, and return the reversed list.
   */
  public ListNode reverseList(ListNode head) {
    // Guard-clause to check if the list is null or has only one node.
    if (head == null || head.next == null) return head;

    ListNode prev = null;
    ListNode curr = head;

    // Iterate through the list until we reach the end.
    while (curr != null) {
      // Store the next node.
      ListNode next = curr.next;

      // Reverse the current node's pointer.
      curr.next = prev;

      // Move the pointers forward.
      prev = curr;
      curr = next;
    }

    return prev;
  }
}
