import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import per.xgt.dao.CustomerDao;
import per.xgt.dao.LinkManDao;
import per.xgt.pojo.Customer;
import per.xgt.pojo.LinkMan;


/**
 * @Author: Valentino
 * @QQ: 3289668817
 * @Email：gentao.xiong
 * @CreateTime: 2020-09-11 17:29:44
 * @Descirption:
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class test {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private LinkManDao linkManDao;

    /**
     * 保存一个联系人的操作
     */
    @Test
    @Transactional
    @Rollback(value = false)//设置不自动回滚
    public void test1(){
        //创建一个客户，创建一个联系人
        Customer customer = new Customer();
        customer.setCustName("客户1");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("联系人1");

        customer.getLinkMans().add(linkMan);

        customerDao.save(customer);
        linkManDao.save(linkMan);
    }

    @Test
    @Transactional
    @Rollback(value = false)//设置不自动回滚
    public void test2(){
        //创建一个客户，创建一个联系人
        Customer customer = new Customer();
        customer.setCustName("客户1");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("联系人1");

        linkMan.setCustomer(customer);
        customer.getLinkMans().add(linkMan);

        customerDao.save(customer);
        linkManDao.save(linkMan);
    }

    /**
     * 级联添加
     */
    @Test
    @Transactional
    @Rollback(value = false)//设置不自动回滚
    public void testCascadeAdd(){
        Customer customer = new Customer();
        customer.setCustName("客户2");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("联系人2");

        linkMan.setCustomer(customer);
        customer.getLinkMans().add(linkMan);

        customerDao.save(customer);


    }

    @Test
    @Transactional
    @Rollback(value = false)//设置不自动回滚
    public void testCascadeRemove(){
        //查询客户
        Customer customer = customerDao.findOne(1l);
        //删除客户
        customerDao.delete(customer);
    }



}
