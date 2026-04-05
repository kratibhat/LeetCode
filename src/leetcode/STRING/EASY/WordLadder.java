package leetcode.STRING.EASY;

import java.util.*;

//A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
//
//Every adjacent pair of words differs by a single letter.
//Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
//sk == endWord
//Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
//
//Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
//Output: 5
//Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
public class WordLadder {
    public static void main(String[] args) {
        WordLadder solution = new WordLadder();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        int result = solution.ladderLength(beginWord, endWord, wordList);
        System.out.println("Length of shortest transformation sequence: " + result); // Output: 5
    }
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        if(!wordList.contains(endWord)) return 0;

        Set<String> beginSet=new HashSet<>();
        Set<String> endSet=new HashSet<>();
        Set<String> wordSet=new HashSet<>(wordList);
        beginSet.add(beginWord);
        endSet.add(endWord);
        return bfs(beginSet,endSet,wordSet,1);
    }

    int bfs(Set<String> beginSet,Set<String> endSet,Set<String> wordSet,int distance){
//We always expand the smaller frontier
//This is the key optimization of bidirectional BFS.

        if(beginSet.size()>endSet.size()){
            return bfs(endSet,beginSet,wordSet,distance);
        }

        Set<String> reachableWords=new HashSet<>();
        //Remove current words so we don’t revisit them
        wordSet.removeAll(beginSet);
        for(String word:beginSet){
            for(int pos=0;pos<word.length();pos++){
                char[] charArray=word.toCharArray();
                for(char c='a';c<='z';c++){
                    charArray[pos]=c;

                    String newWord=new String(charArray);
                    if(wordSet.contains(newWord)){
                        if(endSet.contains(newWord)){
                            return distance + 1;  //beginWord is also counted
                        }
                        reachableWords.add(newWord);
                        //wordSet.remove(newWord);
                    }
                }
            }
        }
        distance++;
        if(reachableWords.size()==0){
            return 0;
        }
        else{
            return bfs(reachableWords,endSet,wordSet,distance);
        }
    }
}
