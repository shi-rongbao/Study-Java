/**
 * @author ShiRongbao
 * @create 2024/4/2 16:20
 * @description:
 */
public class BinarySearch {

    /**
     * 二分查找基础版本
     *
     * @param arr    要查找元素所在的升序数组
     * @param target 要查找的元素
     * @return 找到返回target的index，没找到返回-1
     */
    public static int binarySearchBasic(int[] arr, int target) {
        int i = 0, j = arr.length - 1;  // 设置指针和初值
        while (i <= j) {
            int m = (i + j) >>> 1;  // 计算中间索引
            if (target < arr[m]) {  // 如果目标值在中间索引值左边
                j = m - 1;
            } else if (arr[m] < target) {  // 如果目标值在中间索引值右边
                i = m + 1;
            } else return m;  // 如果中间索引值就是目标值
        }
        return -1;  // 如果没找但返回-1
    }
}
