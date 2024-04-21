package com.shirongbao.xml.test;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class ParseXMLByDom4jTest {
    @Test
    public void testParseSqlMapperXML() throws Exception{
        SAXReader reader = new SAXReader();
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("CarMapper.xml");
        Document document = reader.read(is);
        String xpath = "/mapper";
        Element mapper = (Element) document.selectSingleNode(xpath);
        String namespace = mapper.attributeValue("namespace");  // 拿到mapper的namespace属性值
        System.out.println(namespace);
        List<Element> elements = mapper.elements();
        elements.forEach(element -> {
            String id = element.attributeValue("id");
            System.out.println(id);
            String sql = element.getTextTrim();
            sql = sql.replaceAll("#\\{[0-9a-zA-Z_$]*}", "?");
            System.out.println(sql);
        });
    }
    @Test
    public void testParseMyBatisConfigXML() throws Exception {
        SAXReader reader = new SAXReader();
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("mybatis-config.xml");
        Document document = reader.read(is);
//        Element root = document.getRootElement();
        /**
         * 该行Java代码定义了一个XPath表达式字符串，用于在XML文档中定位特定节点。XPath是一种在XML文档中查找信息的语言。
         * 具体到这个表达式：
         * 它表示从XML文档的根节点开始，查找名为configuration的元素，
         * 并在其下寻找名为environments的子元素。在MyBatis的配置文件中，
         * 这通常用来定位 <environments> 标签，该标签包含了数据库环境的相关配置信息，如事务管理器和数据源等设置。
         */
        String xPath = "/configuration/environments";
        Element environments = (Element) document.selectSingleNode(xPath);
        String defaultEnvironment = environments.attributeValue("default");
        /**
         * 用于在XML文档中定位特定的environment元素，它必须满足以下条件：
         * 从XML文档的根节点开始搜索。
         * 寻找路径为/configuration/environments的元素，即查找名为configuration的根元素下的environments子元素。
         * 在找到的environments元素下进一步寻找名为environment的子元素。
         * 但不仅仅查找任何environment元素，而是指定查找具有属性id且其值为defaultEnvironment的environment元素。
         * 在MyBatis配置文件中，这样的表达式通常用来获取默认数据库环境（defaultEnvironment）的相关配置信息。
         */
        // 构造XPath表达式，查找id属性值为defaultEnvironment的environment元素
        xPath = "/configuration/environments/environment[@id='" + defaultEnvironment + "']";
        System.out.println(xPath); // 输出构造的XPath表达式

        // 使用XPath表达式从XML文档中选择并获取匹配的environment元素
        Element environment = (Element) document.selectSingleNode(xPath);

        // 获取environment元素下的transactionManager子元素
        Element transactionManager = environment.element("transactionManager");

        // 获取并打印transactionManager元素的type属性值
        String transactionManagerType = transactionManager.attributeValue("type");
        System.out.println(transactionManagerType);

        // 获取environment元素下的dataSource子元素
        Element dataSource = environment.element("dataSource");

        // 获取并打印dataSource元素的type属性值
        String dataSourceType = dataSource.attributeValue("type");
        System.out.println(dataSourceType);

        // 获取dataSource元素下的所有子元素（通常包含数据源相关配置属性）
        List<Element> properties = dataSource.elements();
        System.out.println(properties); // 输出所有子元素列表

        // 遍历并打印每个子元素的name和value属性
        properties.forEach(element ->
                System.out.println(element.attributeValue("name") + " = " + element.attributeValue("value"))
        );

        xPath = "//mapper";
        List<Node> mappers = document.selectNodes(xPath);
        mappers.forEach(mapper -> {
            Element mapperElt = (Element) mapper;
            String value = mapperElt.attributeValue("resource");
            System.out.println("resource = " + value);
        });
    }
}
