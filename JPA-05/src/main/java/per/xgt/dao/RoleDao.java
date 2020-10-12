package per.xgt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import per.xgt.pojo.Role;

/**
 * @Author: Valentino
 * @QQ: 3289668817
 * @Email：gentao.xiong
 * @CreateTime: 2020-09-14 14:25:12
 * @Descirption:
 */
public interface RoleDao extends JpaRepository<Role,Long>, JpaSpecificationExecutor<Role> {
}
