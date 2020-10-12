package per.xgt.pojo;

/**
 * @Author: Valentino
 * @QQ: 3289668817
 * @Email：gentao.xiong
 * @CreateTime: 2020-09-09 15:07:35
 * @Descirption:
 */

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 1.实体类与表的映射关系
 * @Entity
 * @Table
 * 2.类中属性与表中字段的映射关系
 * @Id
 * @GenerratedValue
 * @Column
 */
@Entity
@Table(name = "cst_customer")
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="cust_id")
    private Long custId;

    @Column(name="cust_name")
    private String custName;

    @Column(name="cust_source")
    private String custSource;

    @Column(name="cust_industry")
    private String custIndustry;

    @Column(name="cust_level")
    private String custLevel;

    @Column(name="cust_address")
    private String custAddress;

    @Column(name="cust_phone")
    private String custPhone;

    //配置客户和联系人之间得关系（一对多关系）
    /**
     * 使用注解的形式配置多表关系
     *  1.声明关系
     *      @OnetoMany : 字面意思->一对多
     *      targetEntiry : 对方对象的字节码对象
     *  2.配置外键（中间表）
     *      @JoinColumn(name:外键字段名，referencedColumnName：参照的主表的主键字段名称)
     *
     *  在客户实体类上（一）添加了外键配置，所以客户具备了维护外键的作用
     */
//    @OneToMany(targetEntity = LinkMan.class)
//    @JoinColumn(name = "lkm_cust_id",referencedColumnName = "cust_id")
    /**
     * 放弃外键维护权
     *      mappedBy ： 对方配置关系的属性名称
     */
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private Set<LinkMan> linkMans = new HashSet<>();

    public Set<LinkMan> getLinkMans() {
        return linkMans;
    }

    public void setLinkMans(Set<LinkMan> linkMans) {
        this.linkMans = linkMans;
    }

    public Customer() {
    }

    public Customer(String custName, String custSource, String custIndustry, String custLevel, String custAddress, String custPhone) {
        this.custName = custName;
        this.custSource = custSource;
        this.custIndustry = custIndustry;
        this.custLevel = custLevel;
        this.custAddress = custAddress;
        this.custPhone = custPhone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", custSource='" + custSource + '\'' +
                ", custIndustry='" + custIndustry + '\'' +
                ", custLevel='" + custLevel + '\'' +
                ", custAddress='" + custAddress + '\'' +
                ", custPhone='" + custPhone + '\'' +
                '}';
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }
}
