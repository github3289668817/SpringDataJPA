package per.xgt.pojo;

import javax.persistence.*;

/**
 * @Author: Valentino
 * @QQ: 3289668817
 * @Email：gentao.xiong
 * @CreateTime: 2020-09-08 15:24:20
 * @Descirption:
 */

/**
 * 客户实体类
 *  配置映射关系：
 *      1.实体类和表的映射关系
 *          @Entity 声明实体类
 *          @Table  配置实体类和表的映射关系
 *              name : 配置数据库表的名称
 *      2.实体类中属性和表中字段的映射关系
 *          @ID 声明主键的配置
 *          @GeneratedValue(strategy = GenerationType.IDENTITY) 生成策略，自增(数据库必须支持自增)
 *          @GeneratedValue(strategy = GenerationType.SEQUENCE) 生成策略，序列(数据库必须支持序列)
 *          @GeneratedValue(strategy = GenerationType.TABLE) jpa提供的一种机制，通过一张数据库表的形式帮助完成主键自增
 *          @GeneratedValue(strategy = GenerationType.AUTO) 由程序自动的帮助我们选择主键生成策略，根据环境配置和数据库自动选择
 *          @Column(name = "cust_id") 数据库表中字段的名称
 *
 */

@Entity
@Table(name = "cst_customer")
public class Customer {

    //声明表主键的配置
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Long custId;//客户的主键

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

    public Customer() {
    }

    public Customer(Long custId, String custName, String custSource, String custIndustry, String custLevel, String custAddress, String custPhone) {
        this.custId = custId;
        this.custName = custName;
        this.custSource = custSource;
        this.custIndustry = custIndustry;
        this.custLevel = custLevel;
        this.custAddress = custAddress;
        this.custPhone = custPhone;
    }
}
