import java.util.HashMap;
import java.util.Map;

/**
 * @author ShiRongbao
 * @create 2024/10/4
 * @description:
 */
public class TwoSum_00001 {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();;
        for (int i = 0; i < nums.length; i++) {
            if (i != 0 && map.containsKey(target - nums[i])) {
                return new int[] {map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

}
