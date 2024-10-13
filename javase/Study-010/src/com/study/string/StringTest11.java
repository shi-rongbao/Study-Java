package com.study.string;

public class StringTest11 {
    public static void main(String[] args) {
        /*
            需求:
                给定两个字符串,A和B
                A的旋转操作就是将A最左边的字符移动到最右边.
                例如,A = "abcde" , 在移动一次后结果就是"bcdea".
                如果在若干次调整操作后,A能变成B,那么返回True
                如果不能匹配成功,则返回false
         */
        String A = "ABCDE";
        String B = "DEABC";
        //System.out.println(method1(A, B));
        System.out.println(method2(A, B));
    }

    /**
     * 判断字符串A是否可以通过旋转变成字符串B
     *
     * @param A 字符串A
     * @param B 字符串B
     * @return 如果可以通过旋转变成字符串B，则返回true；否则返回false
     */
    public static boolean method1(String A, String B) {
        for (int i = 0; i < A.length(); i++) {
            // 获取A的第一个字符
            char c = A.charAt(0);
            // 将A的第一个字符移除
            A = A.substring(1);
            // 将A的第一个字符追加到末尾
            A = A + c;
            // 检查A是否与B相等
            if (A.equals(B)) {
                return true;
            }
        }
        // 循环结束后未找到匹配，返回false
        return false;
    }

    public static boolean method2(String A, String B) {
        // 将字符串A转换为字符数组
        char[] chars = A.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            // 获取字符数组中的第一个字符
            char first = chars[0];
            // 将字符数组中的每个字符往前移动一位
            for (int j = 1; j < chars.length; j++) {
                chars[j - 1] = chars[j];
            }
            // 将原来的第一个字符放到字符数组的末尾
            chars[chars.length - 1] = first;
            // 将字符数组转换为字符串
            String str = new String(chars);
            // 检查旋转后的字符串是否与字符串B相等
            if (str.equals(B)){
                return true;
            }
        }
        // 循环结束后未找到匹配，返回false
        return false;
    }
}
