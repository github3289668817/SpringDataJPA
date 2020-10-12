package per.xgt.pojo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: Valentino
 * @QQ: 3289668817
 * @Email：gentao.xiong
 * @CreateTime: 2020-09-14 14:20:05
 * @Descirption:
 */

@Entity
@Table(name = "sys_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "age")
    private Integer age;

    /**
     * 配置用户到角色的多对多关系
     *  @ManyToMany 多对多
     *      targetEntity = Role.class 对方的实体类字节码
     *  @JoinTable 配置中间表
     *      name  中间表名
     *      joinColumns 当前对象在中间表的外键
     *          name:外键字段名
     *          referencedColumnName：参照的主表的主键字段名
     *      inverseJoinColumns 对方对象在中间表的外键
     *          name:外键字段名
     *          referencedColumnName：参照的主表的主键字段名
     *
     */
    @ManyToMany(targetEntity = Role.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "sys_user_role",
            joinColumns = {@JoinColumn(name = "sys_user_id",referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "sys_role_id",referencedColumnName = "role_id")}
            )
    private Set<Role> roles = new HashSet<>();

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
