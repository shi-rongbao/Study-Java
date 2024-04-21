import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.List;

public class TestDom4J {
    @Test
    public void testRead() throws DocumentException {
        // 首先读取xml配置文件,获得document对象
        SAXReader saxReader = new SAXReader();
        // 然后从document对象上获取配置文件中的信息

        // 该方法的参数要求是指向xml文件的一个输入流
        // 也可以传入通过类加载器获得指向字节码根路径下的指定文件的输入流
        //saxReader.read();
        InputStream resourceAsStream = TestDom4J.class.getClassLoader().getResourceAsStream("jdbc.xml");
        //通过输入流获得配置文件,解析成一个dom对象
        Document document = saxReader.read(resourceAsStream);
        Element rootElement = document.getRootElement();
        System.out.println(rootElement.getName());
        List<Element> elements = rootElement.elements();
        for (Element element : elements) {
            System.out.print("\t" + element.getName());
            Attribute id = element.attribute("id");
            System.out.println("\t" + "\t" + id.getValue());
        }
    }
}
