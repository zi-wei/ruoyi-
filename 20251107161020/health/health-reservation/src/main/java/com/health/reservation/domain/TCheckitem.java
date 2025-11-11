package com.health.reservation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.health.common.annotation.Excel;
import com.health.common.core.domain.BaseEntity;

/**
 * 检查项信息对象 t_checkitem
 * 
 * @author ruoyi
 * @date 2025-11-07
 */
public class TCheckitem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 检查项ID */
    private Long id;

    /** 编码 */
    @Excel(name = "编码")
    private String code;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 性别（0男 1女 2不限） */
    @Excel(name = "性别", readConverterExp = "0=男,1=女,2=不限")
    private String sex;

    /** 年龄范围 */
    @Excel(name = "年龄范围")
    private String age;

    /** 价格 */
    @Excel(name = "价格")
    private Long price;

    /** 类型（1检查 2检验） */
    @Excel(name = "类型", readConverterExp = "1=检查,2=检验")
    private String type;

    /** 注意事项 */
    @Excel(name = "注意事项")
    private String attention;

    /** 查询关键字：用于编码或名称的模糊搜索 */
    private String keyword;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }

    public void setAge(String age) 
    {
        this.age = age;
    }

    public String getAge() 
    {
        return age;
    }

    public void setPrice(Long price) 
    {
        this.price = price;
    }

    public Long getPrice() 
    {
        return price;
    }

    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }

    public void setAttention(String attention) 
    {
        this.attention = attention;
    }

    public String getAttention() 
    {
        return attention;
    }

    public void setKeyword(String keyword)
    {
        this.keyword = keyword;
    }

    public String getKeyword()
    {
        return keyword;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("name", getName())
            .append("sex", getSex())
            .append("age", getAge())
            .append("price", getPrice())
            .append("type", getType())
            .append("attention", getAttention())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
