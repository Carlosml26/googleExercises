import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindLongestWordInString {

    private static class Tuple<T, U> {
        private  T _1;
        private  U _2;

        private Tuple(T arg1, U arg2) {
            super();
            this._1 = arg1;
            this._2 = arg2;
        }

        @Override
        public String toString() {
            return String.format("(%s, %s)", _1, _2);
        }
    }


    private static Map<Character,List<Tuple<String,Integer>>> initializeDictionary (String input, List<String> words){
        Map<Character, List<Tuple<String,Integer>>> dictionary = new HashMap<Character, List<Tuple<String, Integer>>>();

        //Initialize all element
        for (Character c : input.toCharArray())
            dictionary.put(c,new ArrayList<Tuple<String, Integer>>());

        //Add all the world to the dictionary lists
        for (String word: words){
            List<Tuple<String,Integer>> wordList = dictionary.get(word.charAt(0));
            if (wordList != null)
                wordList.add(new Tuple<String, Integer>(word,0));

        }

        return dictionary;
    }

    private static List findLongestString(String mainWord, List<String> words) {
        List<String> foundList = new ArrayList<String>();

        Map<Character, List<Tuple<String,Integer>>> dictionary = initializeDictionary(mainWord,words);

        for (Character letter: mainWord.toCharArray()){
            List<Tuple<String,Integer>> wordList = dictionary.get(letter);
            dictionary.put(letter,new ArrayList<Tuple<String, Integer>>());

            for (Tuple<String, Integer> tuple : wordList) {
                tuple._2++;
                if (tuple._1.length() == tuple._2) {
                    if (foundList.isEmpty() || foundList.get(0).length() == tuple._2)
                        foundList.add(tuple._1);
                    else if (foundList.get(0).length() < tuple._2) {
                        foundList.clear();
                        foundList.add(tuple._1);
                    }
                } else {
                    List<Tuple<String, Integer>> nextWordList = dictionary.get(tuple._1.charAt(tuple._2));
                    if (nextWordList != null)
                        nextWordList.add(tuple);
                }
            }
        }

        return foundList;
    }


    public static void main(String[] args) {
        List<String> dict = new ArrayList<String>();
        dict.add("2ale");
        dict.add("applee");
        dict.add("monkeyz");
        dict.add("plea");
        dict.add("aDgk");
        String input = "abpcpleeaDChsssgtrsssmonky";

        List<String> longestStrings = findLongestString(input, dict);

        for (String s : longestStrings)
            System.out.println(s);
    }
}

