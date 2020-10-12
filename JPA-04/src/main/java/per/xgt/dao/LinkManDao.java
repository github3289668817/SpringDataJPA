package per.xgt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import per.xgt.pojo.LinkMan;

/**
 * @Author: Valentino
 * @QQ: 3289668817
 * @Email：gentao.xiong
 * @CreateTime: 2020-09-11 17:04:09
 * @Descirption:
 */
public interface LinkManDao extends JpaRepository<LinkMan,Long>, JpaSpecificationExecutor<LinkMan> {
}
