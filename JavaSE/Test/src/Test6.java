import java.lang.reflect.Method;
import java.util.Arrays;

public class Test6 {
    public static void main(String[] args) {
        int[] arr = {3,2,5,6,1,4};

        int temp;

        for(int i = 0; i < arr.length - 1; i++){
            for(int j = 0; j < arr.length - 1 - i; j ++){

                if(arr[j] > arr[j + 1]){
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        System.out.println(Arrays.toString(arr));
    }

    public static boolean contains(Student[] arr, int id){
        for (int i = 0; i < arr.length; i++) {
            Student stu = arr[i];
            int sid = stu.getId();
            if (sid == id){
                return true;
            }
        }
        return false;
    }

}
