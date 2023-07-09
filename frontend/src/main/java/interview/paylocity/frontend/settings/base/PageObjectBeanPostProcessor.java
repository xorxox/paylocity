package interview.paylocity.frontend.settings.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class PageObjectBeanPostProcessor implements BeanPostProcessor {

    @Inject
    private WebDriver driver;

    @Override
    public Object postProcessBeforeInitialization(final Object bean, final String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(PageObject.class)) {
            PageFactory.initElements(driver, bean);
        }
        return bean;
    }
}
