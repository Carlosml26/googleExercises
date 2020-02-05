#The Challenge

Given a string S and a set of words D, find the longest word in D that is a subsequence of S.

Word W is a subsequence of S if some number of characters, possibly zero, can be deleted from S to form W, without reordering the remaining characters.
Note: D can appear in any format (list, hash table, prefix tree, etc.

For example, given the input of S = "abppplee" and D = {"able", "ale", "apple", "bale", "kangaroo"} the correct output would be "apple"
The words "able" and "ale" are both subsequences of S, but they are shorter than "apple".
The word "bale" is not a subsequence of S because even though S has all the right letters, they are not in the right order.
The word "kangaroo" is the longest word in D, but it isn't a subsequence of S.

#Learning objectives

This question gives you the chance to practice with algorithms and data structures. It’s also a good example of why careful analysis for Big-O performance is often worthwhile, as is careful exploration of common and worst-case input conditions.


#Solution

An optimal O(N + L) approach for any alphabet

Instead of processing the words one at a time, we will process all words simultaneously.

First, for each word w in D, create a 2-tuple containing w and the number 0 (i.e. (w, 0)). The number refers to the number of characters in w that have already been found in S; initially, no characters have been found. For a tuple t, we'll refer to the word as t.w, and to the number as t.i (since it is an index).

Group the words by t.w[t.i] (initially t.i is 0, so initially it is by first letter). For our example dictionary D = {"able", "ale", "apple", "bale", "kangaroo"}, we’ll have:
Java
´´´
a -> [("able", 0), ("ale", 0), ("apple", 0)]
b -> [("bale", 0)]
k -> [("kangaroo", 0)]
´´´

What this is doing is telling you what words you will make progress on finding for each possible letter that you might see next in S.

Scan S one character at a time. If the character you just scanned is c, for each tuple t in map[c], increment t.i by 1 and transfer t to map[t.w[t.i]]. The case where t.i == t.w.length is special - transfer these words to a "found" list. In the end, the output is the longest word in the found list.
