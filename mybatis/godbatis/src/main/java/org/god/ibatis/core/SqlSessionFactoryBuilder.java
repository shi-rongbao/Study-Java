package org.god.ibatis.core;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.god.ibatis.utils.Resources;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlSessionFactoryBuilder {
    public SqlSessionFactoryBuilder() {
    }  // 无参构造方法

    public SqlSessionFactory build(InputStream in) {
        SqlSessionFactory factory = null;
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(in);
            // 首先获取到environments标签
            Element environments = (Element) document.selectSingleNode("/configuration/environments");
            String defaultId = environments.attributeValue("default");
            // 接下来获取environment标签
            Element environment = (Element) document.selectSingleNode("/configuration/environments/environment[@id='" + defaultId + "']");
            // 获取事物管理器节点
            Element transactionElt = environment.element("transactionManager");
            // 获取DataSource 标签
            Element dataSourceElt = environment.element("dataSource");
            // 用于存储mapper中resource中对应的xml文件的path
            List<String> sqlMapperXMLPathList = new ArrayList<>();
            // 获取到所有的mapper标签
            List<Node> mappers = document.selectNodes("//mapper");
            mappers.forEach(node -> {
                Element mapper = (Element) node;
                String resource = mapper.attributeValue("resource");
                // 将所有的mapper中的resource放到sqlMapperXMLPathList集合中
                sqlMapperXMLPathList.add(resource);
            });
            DataSource dataSource = getDataSource(dataSourceElt);

            Transaction transaction = getTransaction(transactionElt, dataSource);

            Map<String, MappedStatement> mappedStatements = getMappedStatements(sqlMapperXMLPathList);

            factory = new SqlSessionFactory(transaction, mappedStatements);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return factory;
    }

    /**
     * 从指定的SQL映射文件路径列表中加载并解析所有<code>sqlMapper.xml</code>文件，
     * 构建一个Map对象，键为MappedStatement的ID（即namespace.id），值为MappedStatement对象。
     *
     * @param sqlMapperXMLPathList SQL映射文件资源路径列表
     * @return 包含所有MappedStatement的Map集合
     */
    private Map<String, MappedStatement> getMappedStatements(List<String> sqlMapperXMLPathList) {
        Map<String, MappedStatement> mappedStatements = new HashMap<>();

        // 循环遍历所有的sqlMapper.xml资源路径
        sqlMapperXMLPathList.forEach(sqlMapperXMLPath -> {
            try {
                // 使用SAXReader读取并解析XML文档
                SAXReader reader = new SAXReader();
                Document document = reader.read(Resources.getResourceAsStream(sqlMapperXMLPath));

                // 获取根元素"mapper"
                Element mapperElement = (Element) document.selectSingleNode("mapper");

                // 提取命名空间(namespace)
                String namespace = mapperElement.attributeValue("namespace");

                // 获取mapper下所有的SQL定义元素
                List<Element> sqlElements = mapperElement.elements();

                // 遍历每个SQL定义元素
                sqlElements.forEach(element -> {
                    // 提取SQL定义ID
                    String id = element.attributeValue("id");

                    // 构造完整的MappedStatement ID（namespace.id）
                    String sqlId = namespace + "." + id;

                    // 提取resultType属性，如果存在则获取，否则为null
                    String resultType = element.attributeValue("resultType");

                    // 获取去掉前后空格的SQL语句内容
                    String sql = element.getTextTrim();

                    // 创建一个新的MappedStatement对象，并将它放入map集合中
                    MappedStatement mappedStatement = new MappedStatement(resultType, sql);
                    mappedStatements.put(sqlId, mappedStatement);
                });
            } catch (Exception e) {
                // 处理在解析或处理XML文件时可能出现的任何异常
                e.printStackTrace();
                // 在实际项目中，可能需要更完善的错误处理机制，如记录日志、抛出自定义异常等
            }
        });

        // 返回包含所有MappedStatement的Map集合
        return mappedStatements;
    }


    /**
     * 获取事物管理器
     *
     * @param transactionElt 事务管理器标签
     * @param dataSource     数据源对象
     * @return 返回事物管理器
     */
    private Transaction getTransaction(Element transactionElt, DataSource dataSource) {
        Transaction transaction = null;
        String type = transactionElt.attributeValue("type").trim().toUpperCase();
        if (Const.JDBC_TRANSACTION.equals(type)) {
            // 默认是开启事务的,需要手动提交
            transaction = new JdbcTransaction(dataSource, false);
        }
        if (Const.MANAGED_TRANSACTION.equals(type)) {
            transaction = new ManagedTransaction();
        }
        return transaction;
    }

    /**
     * 获取数据源
     *
     * @param dataSourceElt 数据源标签
     * @return 返回数据源
     */
    private DataSource getDataSource(Element dataSourceElt) {
        // 创建数据源对象
        DataSource dataSource = null;
        // 获取到所有的property标签 (测试拿到了)
        List<Element> propertyEltList = dataSourceElt.elements("property");
        Map<String, String> map = new HashMap<>();
        propertyEltList.forEach(element -> {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            // 测试也成功都装入map集合了
            map.put(name, value);
        });
        String type = dataSourceElt.attributeValue("type").trim().toUpperCase();

        if (Const.UN_POOLED_DATASOURCE.equals(type)) {
            dataSource = new UnPooledDataSource(
                    map.get("driver"),
                    map.get("url"),
                    map.get("username"),
                    map.get("password")
            );
        }
        if (Const.POOPED_DATASOURCE.equals(type)) {
            dataSource = new PooledDataSource();
        }
        if (Const.JNDI_DATASOURCE.equals(type)) {
            dataSource = new JNDIDataSource();
        }
        return dataSource;
    }
}
