package per.xgt.dao;

/**
 * @Author: Valentino
 * @QQ: 3289668817
 * @Email：gentao.xiong
 * @CreateTime: 2020-09-09 15:38:03
 * @Descirption:
 */


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import per.xgt.pojo.Customer;

import java.util.List;

/**
 * 符合SpringDataJPA的dao层接口规范
 *  JpaRepository : 操作的实体类类型，实体类中主键属性的类型
 *      --封装了基本的CRUD操作
 *  JpaSpecificationExecutor : 操作的实体类类型
 *      --封装了复杂查询的操作
 */
public interface CustomerDao extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {

    //根据客户名称查询客户->使用jpql查询
    @Query(value = "from Customer where custName = ?")
    public Customer findJPQLByCustName(String custName);

    //根据客户名称和客户ID查询
    @Query(value = "from Customer where custName = ? and custId = ?")
    public Customer findJPQLByCustNameAndCustId(String name,Long id);

    //更新操作->根据id更新客户
    @Query(value = "update Customer set custAddress = ?2 where custId = ?1")
    @Modifying //声明此方法是用来进行更新操作的
    public void updateCustAddressByCustId(long id,String address);

    /**
     * value:sql语句
     * nativeQuery: true：false 查询方式是否是sql查询
     * @return
     */
    //使用SQL查询全部
    @Query(value = "select * from cst_customer",nativeQuery = true)
    public List<Object[]> findAllBySQL();

    //使用SQL根据名字模糊匹配查询
    @Query(value = "select * from cst_customer where cust_name like ?1",nativeQuery = true)
    public List<Object[]> findAllBySQLOFCustName(String name);

    /**
     * 接口方法命名查询:
     *  约定：findBy：查询
     *          对象属性名称（首字母大写）->查询条件
     *      默认情况：会使用等值查询
     *      特殊情况：模糊查询
     *  在SpringDataJPA运行阶段会根据方法名称进行解析
     */
    //根据客户名称查询
    public Customer findByCustName(String custName);

    /**
     * findBy + 属性名称 ->根据属性名称进行匹配查询
     * findBy + 属性名称 + 查询方式(like、isnull...) ->根据指定查询方式进行查询
     */
    public List<Customer> findByCustNameLike(String name);

    /**
     * findBy + 属性名 + 查询方式 + 多条件连接符(and、or...) + 属性名 + 查询方式......
     * 注意：参数要与方法条件查询顺序一致
     */
    //使用客户名称模糊匹配和客户所属行业精准匹配
    public List<Customer> findByCustNameLikeAndCustIndustry(String name,String industry);

}
