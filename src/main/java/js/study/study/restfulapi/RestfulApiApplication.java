package js.study.study.restfulapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class RestfulApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestfulApiApplication.class, args);
//        ApplicationContext ac = SpringApplication.run(RestfulApiApplication.class, args);
//
//        String[] allBeanNames = ac.getBeanDefinitionNames();
//        for (String beanName : allBeanNames) {
//            System.out.println("beanName = " + beanName);
//        }
    }

    @Bean
    public SessionLocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);
        return localeResolver;
    }
}
