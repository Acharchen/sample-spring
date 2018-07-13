package sample.spring.beans.factory;

import sample.spring.beans.config.BeanDefinition;

/**
 * @author lamian 2530378263@qq.com
 * @date 2018/6/18下午7:08
 */
public interface BeanFactory {

    BeanDefinition getBeanDefinition(String beanName);

    Object getBean(String beanName);

}
