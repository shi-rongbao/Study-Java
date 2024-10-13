import java.util.*;

/**
 * @author ShiRongbao
 * @create 2024/10/4
 * @description:
 */
public class GroupAnagrams_00042_01 {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            // 标准化
            Arrays.sort(charArray);
            String key = new String(charArray);
            if (map.containsKey(key)) {
                map.get(key).add(str);
            } else {
                map.put(key, new ArrayList<>(Arrays.asList(str)));
            }
        }
        return map.values().stream().toList();
    }

}
