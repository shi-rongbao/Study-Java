import java.util.HashSet;

/**
 * @author ShiRongbao
 * @create 2024/10/8
 * @description:
 */
public class LongestConsecutive_00128 {

    public int longestConsecutive_01(int[] nums) {
        // 将数组放入集合中
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int count = 0;
        // 遍历集合
        for (Integer num : set) {
            if (!set.contains(num - 1)) {
                // 如果不存在头部，那么这个数字就是头
                // 判断下一个数字还在不在集合中
                count = 1;
                int curr = num;
                while (set.contains(curr + 1)) {
                    count++;
                    curr++;
                }
            }
        }
        return count;
    }

}
