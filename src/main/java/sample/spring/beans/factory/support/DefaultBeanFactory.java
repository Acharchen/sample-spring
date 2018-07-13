package sample.spring.beans.factory.support;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import sample.spring.beans.config.BeanDefinition;
import sample.spring.beans.config.support.GenericBeanDefinition;
import sample.spring.beans.exception.BeanCreateException;
import sample.spring.beans.exception.BeanDefinitionStoreException;
import sample.spring.beans.factory.BeanFactory;
import sample.spring.beans.utils.ClassUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lamian 2530378263@qq.com
 * @date 2018/6/19上午11:01
 */
public class DefaultBeanFactory implements BeanFactory {

    private static final String ID_ATTRIBUTE = "id";
    private static final String CLASS_ATTRIBUTE = "class";
    private Map<String, BeanDefinition> beanDefinitionRegister = new ConcurrentHashMap<String, BeanDefinition>();

    public DefaultBeanFactory(String beanFilePath) {
        loadBeanDefinition(beanFilePath);
    }

    private void loadBeanDefinition(String beanFilePath){
        if(null == beanFilePath || 0 >= beanFilePath.length()){
            return;
        }
        InputStream is = null;
        ClassLoader cl = ClassUtils.getDefaultClassLoader();
        is = cl.getResourceAsStream(beanFilePath);
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(is);
            Element root = document.getRootElement();
            Iterator<Element> it = root.elementIterator();
            while (it.hasNext()){
                Element element = it.next();
                String beanName = element.attributeValue(ID_ATTRIBUTE);
                String beanClassName = element.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition beanDefinition = new GenericBeanDefinition(beanName, beanClassName);
                beanDefinitionRegister.put(beanName, beanDefinition);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        if(null == beanName || 0 >= beanName.length()){
            throw new BeanCreateException("Create bean for '" + beanName + "' failed, cause beanName illegal, beanName = '" + beanName + "'.");
        }
        BeanDefinition beanDefinition = beanDefinitionRegister.get(beanName);
        if(null == beanDefinition){
            throw new BeanDefinitionStoreException("Bean Definition does not exist.");
        }
        return beanDefinition;
    }

    @Override
    public Object getBean(String beanName) {
        if(null == beanName || 0 >= beanName.length()){
            throw new BeanCreateException("Create bean for '" + beanName + "' failed, cause beanName illegal, beanName = '" + beanName + "'.");
        }
        BeanDefinition beanDefinition = beanDefinitionRegister.get(beanName);
        if(null == beanDefinition){
            throw new BeanDefinitionStoreException("Bean Definition does not exist.");
        }
        ClassLoader cl = ClassUtils.getDefaultClassLoader();
        try {
            Class clazz = cl.loadClass(beanDefinition.getBeanClassName());
            return clazz.newInstance();
        } catch (Exception e){
            throw new BeanCreateException("Create bean for '" + beanName + "' failed.", e);
        }
    }

}
