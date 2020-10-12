import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import per.xgt.dao.RoleDao;
import per.xgt.dao.UserDao;
import per.xgt.pojo.Role;
import per.xgt.pojo.User;

import java.util.Set;

/**
 * @Author: Valentino
 * @QQ: 3289668817
 * @Email：gentao.xiong
 * @CreateTime: 2020-09-14 14:45:03
 * @Descirption:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class test {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    /**
     * 保存一个用户，保存一个角色
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void test1(){
        User user = new User();
        user.setUserName("XGT");

        Role role = new Role();
        role.setRoleName("JAVA");

        //配置用户到角色的关系,可以对中间表表中的数据进行维护
        user.getRoles().add(role);
        //配置角色到用户的关系,可以对中间表表中的数据进行维护
        role.getUsers().add(user);

        userDao.save(user);
        roleDao.save(role);

    }

    /**
     * 级联：保存一个用户
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void test2(){
        User user = new User();
        user.setUserName("XGT");

        Role role = new Role();
        role.setRoleName("JAVA");

        //配置用户到角色的关系,可以对中间表表中的数据进行维护
        user.getRoles().add(role);
        //配置角色到用户的关系,可以对中间表表中的数据进行维护
        role.getUsers().add(user);

        userDao.save(user);

    }

    /**
     * 级联：删除一个用户，同时删除他的关联对象
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void test3(){
        //查询
        User user = userDao.findOne(1l);
        //删除用户
        userDao.delete(user);

    }

    /**
     * 对象导航查询：
     *      通过此对象查询此对象所有的关联对象
     */
    @Test
    @Transactional//解决no session问题
    public void test4(){
        User user = userDao.getOne(1l);
        //对象导航查询
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            System.out.println(role);
        }
    }

}
