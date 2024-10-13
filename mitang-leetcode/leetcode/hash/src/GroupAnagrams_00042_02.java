import java.util.*;

/**
 * @author ShiRongbao
 * @create 2024/10/4
 * @description:
 */
public class GroupAnagrams_00042_02 {

    public List<List<String>> groupAnagrams(String[] strs) {
        // new一个map集合，key是标准化的字符串，value是包含该字符串的字符串集合
        Map<Word, List<String>> map = new HashMap<>();
        for (String str : strs) {
           Word word = new Word(str);
           if (map.containsKey(word)) {
               map.get(word).add(str);
           } else {
               List<String> list = new ArrayList<>();
               list.add(str);
               map.put(word, list);
           }
        }
        return map.values().stream().toList();
    }
}

class Word {
    int[] counts;

    Word(String s) {
        char[] charArray = s.toCharArray();
        counts = new int[26];
        for (char c : charArray) {
            int index = c - 'a';
            counts[index]++;
        }
    }

    @Override
    public boolean equals(Object o) {
        return Arrays.equals(counts, ((Word) o).counts);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(counts);
    }
}
