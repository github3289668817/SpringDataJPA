package per.xgt;

import org.junit.Test;
import per.xgt.pojo.Customer;
import per.xgt.utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * @Author: Valentino
 * @QQ: 3289668817
 * @Email：gentao.xiong
 * @CreateTime: 2020-09-09 10:04:26
 * @Descirption:
 */
public class JPQLTest {

    /**
     * 查询全部
     */
    @Test
    public void testFindAll(){
        //1.通过工具类获取EntityManager对象
        EntityManager em = JpaUtil.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.数据库操作
        String jpql = "from per.xgt.pojo.Customer";
            //创建query查询对象，这才是执行jpql的对象
        Query query = em.createQuery(jpql);
            //发送查询并封装结果集
        List list = query.getResultList();

        for (Object obj : list){
            System.out.println(obj);
        }

        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }

    /**
     * 排序查询--倒序
     */
    @Test
    public void testOrder(){
        //1.通过工具类获取EntityManager对象
        EntityManager em = JpaUtil.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.数据库操作
        String jpql = "from Customer order by custId desc ";
        //创建query查询对象，这才是执行jpql的对象
        Query query = em.createQuery(jpql);
        //发送查询并封装结果集
        List list = query.getResultList();

        for (Object obj : list){
            System.out.println(obj);
        }

        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }

    /**
     * 统计查询
     */
    @Test
    public void testCount(){
        //1.通过工具类获取EntityManager对象
        EntityManager em = JpaUtil.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.数据库操作
        String jpql = "select count(custId) from Customer";
        //创建query查询对象，这才是执行jpql的对象
        Query query = em.createQuery(jpql);
        //发送查询并封装结果集
        /**
         * getResultList：直接将查询结果封装为list集合
         * getSingleResult：得到唯一的结果
         */
        Object result = query.getSingleResult();
        System.out.println(result);
        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }

    /**
     * 分页查询
     */
    @Test
    public void testPage(){
        //1.通过工具类获取EntityManager对象
        EntityManager em = JpaUtil.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.数据库操作
        String jpql = "from Customer";
        //创建query查询对象，这才是执行jpql的对象
        Query query = em.createQuery(jpql);
        //起始索引
        query.setFirstResult(0);
        //每页查询条数
        query.setMaxResults(2);
        //发送查询并封装结果集
        List list = query.getResultList();
        for (Object obj : list){
            System.out.println(obj);
        }
        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }

    /**
     * 条件查询
     *  客户名称以大开头的客户
     */
    @Test
    public void testCondition(){
        //1.通过工具类获取EntityManager对象
        EntityManager em = JpaUtil.getEntityManager();
        //2.开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //3.数据库操作
        String jpql = "from Customer where custName like ?";
        //创建query查询对象，这才是执行jpql的对象
        Query query = em.createQuery(jpql);
        //给占位符参数赋值(第一个参数 是占位符索引位置，第二个参数 占位符取值)
        query.setParameter(1, "大%");
        //发送查询并封装结果集
        List list = query.getResultList();
        for (Object obj : list){
            System.out.println(obj);
        }
        //4.提交事务
        tx.commit();
        //5.释放资源
        em.close();
    }

}
