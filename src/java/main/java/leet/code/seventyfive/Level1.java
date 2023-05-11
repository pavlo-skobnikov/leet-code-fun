package leet.code.seventyfive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

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

  /* DAY 4 */

  /**
   * 876. Middle of the Linked List
   * https://leetcode.com/problems/middle-of-the-linked-list/?envType=study-plan&id=level-1
   *
   * <p>Given the head of a singly linked list, return the middle node of the linked list.
   *
   * <p>If there are two middle nodes, return the second middle node.
   */
  public ListNode middleNode(ListNode head) {
    // Node pointer for iteration.
    ListNode curr = head;

    // Variable to store the length of the list.
    int length = 0;

    // Find the length of the list.
    while (curr != null) {
      length++;

      curr = curr.next;
    }

    // Find the middle node according to the task specification.
    length = (length / 2) + 1;

    // Reset the pointer for iteration.
    curr = head;

    // Iterate through the list until we reach the middle node.
    for (int i = 1; i < length; i++) {
      curr = curr.next;
    }

    return curr;
  }

  /**
   * 142. Linked List Cycle II
   * https://leetcode.com/problems/linked-list-cycle-ii/?envType=study-plan&id=level-1
   *
   * <p>Given the head of a linked list, return the node where the cycle begins. If there is no
   * cycle, return null.
   *
   * <p>There is a cycle in a linked list if there is some node in the list that can be reached
   * again by continuously following the next pointer. Internally, pos is used to denote the index
   * of the node that tail's next pointer is connected to (0-indexed). It is -1 if there is no
   * cycle. Note that pos is not passed as a parameter.
   *
   * <p>Do not modify the linked list.
   */
  public ListNode detectCycle(ListNode head) {
    // Guard-clause to check if the list is null or has only one node.
    if (head == null || head.next == null) return null;

    // Node pointer for iteration.
    ListNode slowPtr = head;
    ListNode fastPtr = head;

    // Iterate through the list until the pointers meet.
    while (fastPtr != null && fastPtr.next != null) {
      // Move the slow pointer by one node.
      slowPtr = slowPtr.next;

      // Move the fast pointer by two nodes.
      fastPtr = fastPtr.next.next;

      // If the pointers meet, there is a cycle.
      if (slowPtr == fastPtr) {
        // Reset the slow pointer to the head.
        slowPtr = head;

        // Iterate through the list until the pointers meet again.
        // The node where the pointers meet is the node where the cycle begins.
        // This is guaranteed by Floyd's cycle-finding algorithm.
        while (slowPtr != fastPtr) {
          slowPtr = slowPtr.next;
          fastPtr = fastPtr.next;
        }

        // Return the node where the cycle begins.
        return slowPtr;
      }
    }

    // Fallback to return null if there is no cycle.
    return null;
  }

  /* DAY 5 */

  /**
   * 121. Best Time to Buy and Sell Stock
   * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/?envType=study-plan&id=level-1
   *
   * <p>You are given an array prices where prices[i] is the price of a given stock on the ith day.
   *
   * <p>You want to maximize your profit by choosing a single day to buy one stock and choosing a
   * different day in the future to sell that stock.
   *
   * <p>Return the maximum profit you can achieve from this transaction. If you cannot achieve any
   * profit, return 0.
   */
  public int maxProfit(int[] prices) {
    // Guard-clause to check if the array has only one element.
    if (prices.length <= 1) return 0;

    int lowestPriceSeenSoFar = prices[0];
    int currentMaxPossibleProfit = 0;

    for (int i = 1; i < prices.length; i++) {
      // If the current price is lower than the lowest price seen so far, update the
      // lowest price.
      if (prices[i] < lowestPriceSeenSoFar) {
        lowestPriceSeenSoFar = prices[i];
      }

      final int profitIfSoldToday = prices[i] - lowestPriceSeenSoFar;
      // If the current price minus the lowest price seen so far is greater than the
      // current max possible profit, update it.
      if (profitIfSoldToday > currentMaxPossibleProfit) {
        currentMaxPossibleProfit = profitIfSoldToday;
      }
    }

    return currentMaxPossibleProfit;
  }

  /**
   * 409. Longest Palindrome
   * https://leetcode.com/problems/longest-palindrome/?envType=study-plan&id=level-1
   *
   * <p>Given a string s which consists of lowercase or uppercase letters, return the length of the
   * longest palindrome that can be built with those letters.
   *
   * <p>Letters are case sensitive, for example, "Aa" is not considered a palindrome here.
   */
  public int longestPalindrome(String s) {
    // Guard-clause to check if the string is a single character long.
    if (s.length() == 1) return 1;

    // Map to store the frequency of each character in the string.
    final Map<Character, Integer> charFrequencyMap = new HashMap<>();

    // Iterate through the string and store the frequency of each character.
    for (int i = 0; i < s.length(); i++) {
      final char currentChar = s.charAt(i);

      int currentCharFrequency = charFrequencyMap.getOrDefault(currentChar, 0);

      charFrequencyMap.put(currentChar, ++currentCharFrequency);
    }

    int totalSumOfEvenFrequencies = 0;
    boolean isThereAnOddFrequency = false;

    // Iterate through the map and calculate the sum of even frequencies and the
    // biggest odd frequency.
    for (int frequency : charFrequencyMap.values()) {
      if (frequency % 2 == 0) {
        totalSumOfEvenFrequencies += frequency;
      } else {
        isThereAnOddFrequency = true;

        totalSumOfEvenFrequencies += frequency - 1;
      }
    }

    return totalSumOfEvenFrequencies + (isThereAnOddFrequency ? 1 : 0);
  }

  /* DAY 6 */

  class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
      val = _val;
    }

    public Node(int _val, List<Node> _children) {
      val = _val;
      children = _children;
    }
  }

  /**
   * 589. N-ary Tree Preorder Traversal
   * https://leetcode.com/problems/n-ary-tree-preorder-traversal/?envType=study-plan&id=level-1
   *
   * <p>Given the root of an n-ary tree, return the preorder traversal of its nodes' values.
   *
   * <p>Nary-Tree input serialization is represented in their level order traversal. Each group of
   * children is separated by the null value (See examples)
   */
  public List<Integer> preorder(Node root) {
    // Result list to store the preorder traversal.
    final List<Integer> preorderList = new ArrayList<>();
    // Guard-clause to check if the root is null.
    if (root == null) return preorderList;
    // Stack to store the nodes to visit.
    // We use a stack specifically because we want to visit the latest
    // encountered node's children first from left to right.
    final Stack<Node> nodesToVisit = new Stack<>();

    // Add the root node to the stack.
    nodesToVisit.push(root);

    while (!nodesToVisit.isEmpty()) {
      // Pop the top node from the stack.
      root = nodesToVisit.pop();

      // Add the current node's value to the result list.
      preorderList.add(root.val);

      // Put the children of the current node on the stack with the
      // leftmost child on top of the stack.
      this.putNodeChildrenOnStack(nodesToVisit, root);
    }

    return preorderList;
  }

  // Helper method to put the children of a node on the stack in reverse order.
  // This is done to ensure that the children are visited in the correct order,
  // i.e., from left to right.
  private void putNodeChildrenOnStack(final Stack<Node> nodesToVisit, final Node node) {
    for (int i = node.children.size() - 1; i >= 0; i--) {
      nodesToVisit.push(node.children.get(i));
    }
  }

  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  /**
   * 102. Binary Tree Level Order Traversal
   * https://leetcode.com/problems/binary-tree-level-order-traversal/?envType=study-plan&id=level-1
   *
   * <p>Given the root of a binary tree, return the level order traversal of its nodes' values.
   * (i.e., from left to right, level by level).
   */
  public List<List<Integer>> levelOrder(TreeNode root) {
    // Result list to store the level order traversal.
    final List<List<Integer>> levelOrderTraversal = new ArrayList<>();

    // Guard-clause to check if the root is null.
    if (root == null) return levelOrderTraversal;

    // Queue to store the nodes to visit on a given iteration.
    Queue<TreeNode> nodesToVisit = new LinkedList<>();

    // Add the root node to the list.
    nodesToVisit.add(root);

    while (!nodesToVisit.isEmpty()) {
      // List to store the values of the nodes on a given level.
      List<Integer> currentLevelValues = new ArrayList<>();

      final int numberOfNodesOnCurrentLevel = nodesToVisit.size();

      for (int count = 0; count < numberOfNodesOnCurrentLevel; count++) {
        final TreeNode currentNode = nodesToVisit.poll();

        // Add the current node's children to the next level's list.
        if (currentNode.left != null) nodesToVisit.add(currentNode.left);
        if (currentNode.right != null) nodesToVisit.add(currentNode.right);

        // Add the current node's value to the current level's list.
        currentLevelValues.add(currentNode.val);
      }

      // Add the current level's list to the result list.
      levelOrderTraversal.add(currentLevelValues);
    }

    return levelOrderTraversal;
  }
}
