package com.yxz.wulibibiji.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName uvCount
 */
@TableName(value ="uvCount")
public class Uvcount implements Serializable {
    /**
     * 主键，关注主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 当日访问量
     */
    private Integer count;

    /**
     * 每日更新时间
     */
    private Date day;

    /**
     * 备注
     */
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键，关注主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键，关注主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 当日访问量
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 当日访问量
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 每日更新时间
     */
    public Date getDay() {
        return day;
    }

    /**
     * 每日更新时间
     */
    public void setDay(Date day) {
        this.day = day;
    }

    /**
     * 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Uvcount() {
    }

    public Uvcount(Integer id, Integer count, Date day, String remark) {
        this.id = id;
        this.count = count;
        this.day = day;
        this.remark = remark;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Uvcount other = (Uvcount) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCount() == null ? other.getCount() == null : this.getCount().equals(other.getCount()))
            && (this.getDay() == null ? other.getDay() == null : this.getDay().equals(other.getDay()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCount() == null) ? 0 : getCount().hashCode());
        result = prime * result + ((getDay() == null) ? 0 : getDay().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", count=").append(count);
        sb.append(", day=").append(day);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}