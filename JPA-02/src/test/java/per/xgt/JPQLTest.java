package per.xgt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import per.xgt.dao.CustomerDao;
import per.xgt.pojo.Customer;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Valentino
 * @QQ: 3289668817
 * @Email：gentao.xiong
 * @CreateTime: 2020-09-10 10:08:25
 * @Descirption:
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JPQLTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testFindJPQLByCustName(){
        Customer customer = customerDao.findJPQLByCustName("大桥");
        System.out.println(customer);
    }

    @Test
    public void findJPQLByCustNameAndCustId(){
        Customer customer = customerDao.findJPQLByCustNameAndCustId("大桥", 1l);
        System.out.println(customer);
    }

    /**
     * SpringDataJPA中使用jpql完成更新/删除操作
     *  1.需要手动添加事务的支持->@Transactional
     *  2.默认会回滚事务->
     * @Rollback : 设置是否回滚，true：false
     */
    @Test
    @Transactional //添加事务注解
    @Rollback(value = false)
    public void updateCustAddressByCustId(){
        customerDao.updateCustAddressByCustId(2l, "日本");
    }

    @Test
    public void testfindAllBySQL(){
        List<Object[]> list = customerDao.findAllBySQL();
        for (Object[] obj : list) {
            System.out.println(Arrays.toString(obj));
        }
    }

    @Test
    public void testfindAllBySQLOFCustName(){
        List<Object[]> list = customerDao.findAllBySQLOFCustName("大%");
        for (Object[] obj : list) {
            System.out.println(Arrays.toString(obj));
        }
    }

    @Test
    public void testfindByCustName(){
        List<Customer> customers = customerDao.findByCustNameLike("大%");
        for (Customer customer : customers) {
            System.out.println(customer);
        }

    }

    @Test
    public void testfindByCustNameLike(){
        List customer = customerDao.findByCustNameLike("大%");
        System.out.println(customer);
    }

    @Test
    public void testfindByCustNameLikeAndCustIndustry(){
        List<Customer> customers = customerDao.findByCustNameLikeAndCustIndustry("大%", "演员");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

}
