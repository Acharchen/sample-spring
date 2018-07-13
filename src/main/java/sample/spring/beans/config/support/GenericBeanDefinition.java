package sample.spring.beans.config.support;

import sample.spring.beans.config.BeanDefinition;

/**
 * @author lamian 2530378263@qq.com
 * @date 2018/6/19上午11:39
 */
public class GenericBeanDefinition implements BeanDefinition {

    private String beanName;
    private String beanClassName;

    public GenericBeanDefinition(String beanName, String beanClassName) {
        this.beanName = beanName;
        this.beanClassName = beanClassName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }
}
