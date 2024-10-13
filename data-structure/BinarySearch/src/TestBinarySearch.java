import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author ShiRongbao
 * @create 2024/4/2 16:27
 * @description:
 */
public class TestBinarySearch {
    @Test
    @DisplayName("找到的情况")
    public void test1() {
        int[] arr = {7, 13, 21, 30, 38, 44, 52, 53};
        Assertions.assertEquals(0, BinarySearch.binarySearchBasic(arr, 7));
        Assertions.assertEquals(1, BinarySearch.binarySearchBasic(arr, 13));
        Assertions.assertEquals(2, BinarySearch.binarySearchBasic(arr, 21));
        Assertions.assertEquals(3, BinarySearch.binarySearchBasic(arr, 30));
        Assertions.assertEquals(4, BinarySearch.binarySearchBasic(arr, 38));
        Assertions.assertEquals(5, BinarySearch.binarySearchBasic(arr, 44));
        Assertions.assertEquals(6, BinarySearch.binarySearchBasic(arr, 52));
        Assertions.assertEquals(7, BinarySearch.binarySearchBasic(arr, 53));
    }

    @Test
    @DisplayName("没找到的情况")
    public void testFailure() {
        int[] arr = {7, 13, 21, 30, 38, 44, 52, 53};
        Assertions.assertEquals(-1, BinarySearch.binarySearchBasic(arr, 1));
        Assertions.assertEquals(-1, BinarySearch.binarySearchBasic(arr, 15));
        Assertions.assertEquals(-1, BinarySearch.binarySearchBasic(arr, 60));
    }
}
