package sample.spring.beans;

import org.junit.Test;
import sample.spring.beans.factory.BeanFactory;
import sample.spring.beans.factory.support.DefaultBeanFactory;
import sample.spring.beans.model.Person;

import static org.junit.Assert.*;

/**
 * @author lamian 2530378263@qq.com
 * @date 2018/6/18下午7:05
 */
public class BeanFactoryTest {

    @Test
    public void BeanFactoryTest(){
        BeanFactory beanFactory = new DefaultBeanFactory("bean_v1.xml");
        assertEquals("sample.spring.beans.model.Person", beanFactory.getBeanDefinition("perso").getBeanClassName());

        Person person = (Person) beanFactory.getBean("person");
        assertNotNull(person);
    }
}