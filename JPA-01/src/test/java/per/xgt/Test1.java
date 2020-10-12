package per.xgt;

import org.junit.Test;
import per.xgt.pojo.Customer;
import per.xgt.utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * @Author: Valentino
 * @QQ: 3289668817
 * @Email：gentao.xiong
 * @CreateTime: 2020-09-08 15:39:08
 * @Descirption:
 */
public class Test1 {

    /**
     * 测试jpa的保存：保存一个客户到数据库中
     *
     * jpa的操作步骤
     *  1.加载配置文件创建工程（尸体管理类工厂）对象
     *      Persistence 静态方法（根据持久化单元名称创建实体管理类管理工厂）
     *          createEntityManagerFactory（持久化单元命长）
     *      作用：创建实体管理工厂
     *  2.通过实体管理类工厂获取实体管理器
     *      EntityManagerFactory:获取EntityManager对象
     *      方法：createEntityManager
     *      ----内部维护了数据库信息、缓存信息、所有的实体管理器对象、根据配置创建数据库表
     *      ----EntityManagerFactory的创建过程比较浪费资源
     *      ----特点：线程安全->多个线程访问同一个EntityManagerFactory不会有线程安全问题
     *  3.获取事务对象开启事务
     *      EntityManager对象：实体类管理器
     *          beginTransaction ： 创建事务对象
     *          persist : 保存
     *          merge ： 更新
     *          remove ： 删除
     *          find/getRefrence ： 根据id查询
     *      Transaction对象 : 事务
     *          begin ： 开启
     *          commit ： 提交
     *          rollback ： 回滚
     *  4.完成数据库操作
     *  5.提交事务或回滚事务
     *  6.释放资源
     *
     */
    @Test
    public void testSave(){
        
        /*//加载配置文件创建工程（尸体管理类工厂）对象
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("MyJpa");
        
        //通过实体管理类工厂获取实体管理器
        EntityManager em = factory.createEntityManager();

        //获取事务对象开启事务
            //1.获取事务对象
        EntityTransaction tx = em.getTransaction();
            //2.开启事务
        tx.begin();

        //完成数据库操作--保存一个客户
        Customer customer = new Customer();
        customer.setCustName("大桥");
        customer.setCustIndustry("演员");
            //保存操作
        em.persist(customer);

        //提交事务或回滚事务
        tx.commit();

        //释放资源
        em.close();
        factory.close();*/

        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Customer customer = new Customer();
        customer.setCustName("大桥");
        customer.setCustIndustry("演员");
        em.persist(customer);
        tx.commit();
        em.close();

    }

    /**
     * 根据id查询客户
     */
    @Test
    public void testFind(){
        //1.通过工具类获取对象
        EntityManager manager = JpaUtil.getEntityManager();
        //2.开启事务
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        //3.数据库操作
        /**
         * find ： 根据id查询数据
         *  class ： 查询数据的结果需要包装的实体类的字节码
         *  id ： 主键取值
         */
        Customer customer = manager.find(Customer.class, 2l);
        System.out.println(customer);
        //4.提交事务
        tx.commit();
        //5.释放资源
        manager.close();
    }

    /**
     * 使用find方法查询：立即加载
     * 	查询的对象就是当前客户对象本身
     * 	在调用find方法的时候，就会发送sql语句查询数据库
     * 使用getReference方法：延迟加载（懒加载）
     * 	获取的对象是一个动态代理对象
     * 	调用getReference方法不会立即发送sql语句查询数据库，当调用查询结果对象的时候，才会发送查询的sql语句，什么时候用，什么时候发送sql数据执行查询
     */
    @Test
    public void testGetReference(){
        //1.通过工具类获取对象
        EntityManager manager = JpaUtil.getEntityManager();
        //2.开启事务
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        //3.数据库操作
        /**
         * getReference ： 根据id查询数据
         *  class ： 查询数据的结果需要包装的实体类的字节码
         *  id ： 主键取值
         */
        Customer customer = manager.getReference(Customer.class, 2l);
        System.out.println(customer);
        //4.提交事务
        tx.commit();
        //5.释放资源
        manager.close();
    }

    /**
     * 删除客户
     */
    @Test
    public void testRemove(){
        //1.通过工具类获取对象
        EntityManager manager = JpaUtil.getEntityManager();
        //2.开启事务
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        //3.数据库操作
        /**
         * 参数是一个对象:
         *  1.根据id查询客户
         *  2.调用remove方法完成删除操作
         */
        Customer customer = manager.find(Customer.class, 2l);
        manager.remove(customer);
        //4.提交事务
        tx.commit();
        //5.释放资源
        manager.close();
    }

    /**
     * 更新客户
     */
    @Test
    public void testUpdate(){
        //1.通过工具类获取对象
        EntityManager manager = JpaUtil.getEntityManager();
        //2.开启事务
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        //3.数据库操作
        /**
         * 参数是一个对象:
         *  1.查询客户
         *  2.调用merge方法完成更新操作
         */
        Customer customer = manager.find(Customer.class, 1l);
        customer.setCustAddress("日本");
        manager.merge(customer);
        //4.提交事务
        tx.commit();
        //5.释放资源
        manager.close();
    }

}
