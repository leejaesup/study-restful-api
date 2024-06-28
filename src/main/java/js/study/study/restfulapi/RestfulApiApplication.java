package js.study.study.restfulapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class RestfulApiApplication {

    public static void main(String[] args) {
        ApplicationContext ac = SpringApplication.run(RestfulApiApplication.class, args);

        String[] allBeanNames = ac.getBeanDefinitionNames();
        for (String beanName : allBeanNames) {
//            System.out.println("beanName = " + beanName);
        }
    }

}
