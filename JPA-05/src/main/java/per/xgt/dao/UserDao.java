package per.xgt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import per.xgt.pojo.User;

/**
 * @Author: Valentino
 * @QQ: 3289668817
 * @Email：gentao.xiong
 * @CreateTime: 2020-09-14 14:24:33
 * @Descirption:
 */
public interface UserDao extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {
}
