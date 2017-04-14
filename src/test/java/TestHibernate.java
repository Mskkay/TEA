import com.hyetpang.netShopping.entity.User;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by hyetp on 2017/4/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
@Slf4j
public class TestHibernate {
    @Resource
    private HibernateTemplate hibernateTemplate;

    @Test
    @Transactional
    public void test() {
        User user = new User("你好", "nihao");
        hibernateTemplate.save(user);
        Assert.assertNotNull(user.getUserId());
        System.out.println(user);
        log.debug(user.toString());
        LocalDate localDate = LocalDate.now();
        System.out.println("=================日期===============" + localDate);
        LocalTime localTime = LocalTime.now();
        System.out.println("=================时间===============" + localTime);
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("=================日期时间===============" + localDateTime);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH时mm分ss秒SSS毫秒");
        System.out.println("===============格式化时间日期==============" + dateTimeFormatter.format(localTime));
    }

    @Test
    public void testValidator() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        User user = new User("asd", "asd");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        for (ConstraintViolation<User> constraintViolation : constraintViolations) {
            System.out.println("对象属性:" + constraintViolation.getPropertyPath());
            System.out.println("国际化的消息:" + constraintViolation.getMessageTemplate());
            System.out.println("错误消息:" + constraintViolation.getMessage());
        }
    }

}
