package per.xgt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import per.xgt.pojo.Customer;

/**
 * @Author: Valentino
 * @QQ: 3289668817
 * @Email：gentao.xiong
 * @CreateTime: 2020-09-10 17:57:43
 * @Descirption:
 */
public interface CustomerDao extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {
}
