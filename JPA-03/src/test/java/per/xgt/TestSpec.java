package per.xgt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import per.xgt.dao.CustomerDao;
import per.xgt.pojo.Customer;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * @Author: Valentino
 * @QQ: 3289668817
 * @Email：gentao.xiong
 * @CreateTime: 2020-09-10 17:56:10
 * @Descirption:
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestSpec {

    @Autowired
    private CustomerDao customerDao;

    /**
     * 查询单个对象
     *  根据条件查询
     */
    @Test
    public void testSpec(){
        /**
         * 匿名内部类->自定义查询条件
         *  1.实现Specification接口（提供反省，查询的对象类型）
         *  2.实现toPredicate方法（构造查询条件）
         *  3.需要借助方法参数中的两个参数：
         *      1）root：获取需要查询的对象属性
         *      2）CriteriaBuilder：构造查询条件，内部封装了很多查询条件（模糊匹配，精准匹配）
         */
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //获取比较的属性-->get方法，参数为属性的名称
                Path<Object> custName = root.get("custName");
                //构造查询条件->equal进行精准匹配(比较的属性(Path对象)，属性的取值)
                Predicate predicate = criteriaBuilder.equal(custName, "大桥");
                return predicate;
            }
        };
        Customer customer = customerDao.findOne(spec);
        System.out.println(customer);
    }

    /**
     * 多条件拼接
     *  根据客户名称和客户所属行业查询
     */
    @Test
    public void testSpecs(){
        /**
         * root：获取属性
         * CriteriaBuilder：构造查询->
         *      1.构造客户的精准匹配
         *      2.构造所属行业的精准匹配
         *      3.将两个条件联系起来
         */
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path custName = root.get("custName");
                Path custIndustry = root.get("custIndustry");
                Predicate p1 = criteriaBuilder.equal(custName, "大桥");
                Predicate p2 = criteriaBuilder.equal(custIndustry, "演员");
                //将多个条件组合到一起->and就是与->多个查询条件
                Predicate predicate = criteriaBuilder.and(p1, p2);
                return predicate;
            }
        };
        Customer customer = customerDao.findOne(spec);
        System.out.println(customer);
    }

    /**
     * 根据客户名的模糊匹配，返回客户列表
     */
    @Test
    public void testSpec2(){
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> custName = root.get("custName");
                /**
                 * equal：直接得到path对象（属性）就可以比较
                 * gt、lt、ge、le、like：不能直接用path对象比较，需要得到path对象，根据path指定比较的参数类型，再取进行比较
                 * 指定类型方式：path.as(类型的字节码对象)
                 */
                Predicate predicate = criteriaBuilder.like(custName.as(String.class), "大%");
                return predicate;
            }
        };
        //创建排序对象,需要调用构造方法实例化对象(排序的顺序，排序的属性)
        Sort sort = new Sort(Sort.Direction.DESC, "custId");
        List<Customer> customers = customerDao.findAll(spec, sort);
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    /**
     * 分页查询
     *  参数（Specification查询条件，Pageable分页参数对象）
     *      PageRequest是Pagebble接口的实现类
     *  返回：Page：SpringDataJPA封装好的pageBean对象，数据列表，共条数
     */
    @Test
    public void testSpec3(){
        Specification spec = null;
        // 参数（当前查询的页数->0开始，每页查询的数量）
        PageRequest request = new PageRequest(0, 2);
        Page page = customerDao.findAll(spec, request);
        //getTotalElements 总条数
        System.out.println(page.getTotalElements());
        //getTotalPages 总页数
        System.out.println(page.getTotalPages());
        //getContent 结果列表
        List content = page.getContent();
        for (Object o : content) {
            System.out.println(o);
        }
    }

}
