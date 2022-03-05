package com.example.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class Event implements Serializable {
    private Long id;

    /**
     * 赛事名
     */
    private String name;

    /**
     * 赛事开始时间
     */
    private Date time;

    /**
     * 赛事级别
     */
    private String level;

    /**
     * 赛事俱乐部数量
     */
    private Integer clubum;

    /**
     * 赛事状态 1.已开赛 2.等待开赛 3.可参加 4.开赛失败
     */
    private String status;

    private static final long serialVersionUID = 1L;

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
        Event other = (Event) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getClubum() == null ? other.getClubum() == null : this.getClubum().equals(other.getClubum()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getClubum() == null) ? 0 : getClubum().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", name=" + name +
                ", time=" + time +
                ", level=" + level +
                ", clubum=" + clubum +
                ", status=" + status +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}