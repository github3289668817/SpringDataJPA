package per.xgt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import per.xgt.dao.CustomerDao;
import per.xgt.pojo.Customer;

import java.util.List;

/**
 * @Author: Valentino
 * @QQ: 3289668817
 * @Email：gentao.xiong
 * @CreateTime: 2020-09-09 15:44:38
 * @Descirption:
 */
@RunWith(SpringJUnit4ClassRunner.class)//声明Spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml")//指定Spring容器的配置信息
public class CustomerTest {

    @Autowired
    private CustomerDao customerDao;

    /**
     * 根据ID查询
     */
    @Test
    public void testFindOne(){
        Customer customer = customerDao.findOne(1l);
        System.out.println(customer);
    }

    /**
     * 保存或更新
     *  根据传递的对象是否存在主键ID
     *      --如果没有，保存
     *      --如果存在，根据ID查询，然后更新数据
     */
    @Test
    public void testSave(){
        Customer customer = new Customer();
        customer.setCustName("佐伯");
        customer.setCustLevel("骑兵");
        customer.setCustIndustry("演员");
        customerDao.save(customer);
    }

    @Test
    public void testUpdate(){
        Customer customer = new Customer();
        customer.setCustId(2l);
        customer.setCustName("结衣");
        customer.setCustLevel("步兵");
        customer.setCustIndustry("演员");
        customerDao.save(customer);
    }

    @Test
    public void testDelete(){
        customerDao.delete(4l);
    }

    @Test
    public void testFindAll(){
        List<Customer> list = customerDao.findAll();
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }

    /**
     * 统计查询
     */
    @Test
    public void testCount(){
        long count = customerDao.count();//查询全部的客户数量
        System.out.println(count);
    }
    
    @Test
    public void testExists(){
        boolean exists = customerDao.exists(1l);
        System.out.println(exists);
    }

    /**
     * @Transactional 保证getOne正常运行
     */
    @Test
    @Transactional
    public void testGetOne(){
        Customer exists = customerDao.getOne(1l);
        System.out.println(exists);
    }



}
