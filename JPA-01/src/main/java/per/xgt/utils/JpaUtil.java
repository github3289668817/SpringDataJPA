package per.xgt.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @Author: Valentino
 * @QQ: 3289668817
 * @Email：gentao.xiong
 * @CreateTime: 2020-09-08 17:23:50
 * @Descirption: 解决实体管理器工厂浪费资源问题和耗时问题->静态代码块
 */
public class JpaUtil {

    private static EntityManagerFactory factory;

    static {
        factory = Persistence.createEntityManagerFactory("MyJpa");
    }

    /**
     * 获取管理实体对象
     */
    public static EntityManager getEntityManager(){
        EntityManager manager = factory.createEntityManager();
        return manager;
    }

}
