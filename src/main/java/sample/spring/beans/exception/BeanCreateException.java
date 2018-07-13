package sample.spring.beans.exception;

/**
 * Created by lamian on 2018/7/1.
 */
public class BeanCreateException extends BeansException {

    private String beanName;

    public BeanCreateException(String message) {
        super(message);
    }

    public BeanCreateException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeanCreateException(String beanName, String msg){
        super("Error creating bean whit name '" + beanName + "': " + msg);
        this.beanName = beanName;
    }

}
