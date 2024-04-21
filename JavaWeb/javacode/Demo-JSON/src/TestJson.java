import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestJson {
    @Test
    public void testWriteJson() throws JsonProcessingException {
        // 首先获取Person的实例化,然后转为JSON串
        Dog dog = new Dog("小黄", 6);
        Person person = new Person("张三", 20, dog);
        // 然后将Person对象转换为JSON字符串
        ObjectMapper om = new ObjectMapper();
        String personStr = om.writeValueAsString(person);
        System.out.println(personStr);
    }

    @Test
    public void testReadJson() throws JsonProcessingException {
        // 先有一个JSON串
        String personStr = "{\"name\":\"张三\",\"age\":20,\"dog\":{\"name\":\"小黄\",\"age\":6}}";
        // 获取ObjectMapper对象然后用readValue方法将字符串转为对象, 第一个参数是JSON串,第二个参数是要转成的对象的类的字节码
        ObjectMapper om = new ObjectMapper();
        Person person = om.readValue(personStr, Person.class);
        System.out.println(person);
    }

    // map与JSON串的互相转换
    @Test
    public void testMapToJson() throws JsonProcessingException {
        Map<String, String > map  = new HashMap<>();
        map.put("姓名","张三");
        map.put("年龄","23");
        map.put("性别","男");
        map.put("爱好","女");

        ObjectMapper om = new ObjectMapper();
        String mapString = om.writeValueAsString(map); // 顺序会被打乱
        System.out.println(mapString);
    }

    @Test
    public void testListArrayToJson() throws JsonProcessingException {
        List<String> list = new ArrayList<>();
        list.add("张三");
        list.add("赵四");
        list.add("王五");
        ObjectMapper om1 = new ObjectMapper();
        String listString = om1.writeValueAsString(list);
        System.out.println(listString);

        String[] name = {"张三", "赵四", "王五"};
        ObjectMapper om2 = new ObjectMapper();
        String nameString = om2.writeValueAsString(name);
        System.out.println(nameString);

        // 不难发现,List与Array转成JSON串的格式是相同的
    }
    @Test
    public void testListArrayToJson2() throws JsonProcessingException {
        List<Person> list = new ArrayList<>();
        Dog dog = new Dog("小黄", 6);
        Person person = new Person("张三", 20, dog);
        list.add(person);

        ObjectMapper om = new ObjectMapper();
        String listString = om.writeValueAsString(list);
        System.out.println(listString);

        // List里面包对象,JSON串的格式为中括号里包大括号,大括号用逗号分开
    }
}
