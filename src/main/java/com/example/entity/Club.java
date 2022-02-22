package com.example.entity;

import lombok.Data;

import java.io.Serializable;


@Data
public class Club implements Serializable {
    /**
     * 俱乐部id
     */
    private Long clubId;

    /**
     * 俱乐部名
     */
    private String clubName;

    /**
     * 俱乐部队标
     */
    private String clubImg;

    /**
     * 俱乐部教练名
     */
    private String clubCoach;

    /**
     * 俱乐部组别
     */
    private String clubGroup;

    /**
     * 俱乐部积分
     */
    private Integer clubPoints;

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
        Club other = (Club) that;
        return (this.getClubId() == null ? other.getClubId() == null : this.getClubId().equals(other.getClubId()))
            && (this.getClubName() == null ? other.getClubName() == null : this.getClubName().equals(other.getClubName()))
            && (this.getClubImg() == null ? other.getClubImg() == null : this.getClubImg().equals(other.getClubImg()))
            && (this.getClubCoach() == null ? other.getClubCoach() == null : this.getClubCoach().equals(other.getClubCoach()))
            && (this.getClubGroup() == null ? other.getClubGroup() == null : this.getClubGroup().equals(other.getClubGroup()))
            && (this.getClubPoints() == null ? other.getClubPoints() == null : this.getClubPoints().equals(other.getClubPoints()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getClubId() == null) ? 0 : getClubId().hashCode());
        result = prime * result + ((getClubName() == null) ? 0 : getClubName().hashCode());
        result = prime * result + ((getClubImg() == null) ? 0 : getClubImg().hashCode());
        result = prime * result + ((getClubCoach() == null) ? 0 : getClubCoach().hashCode());
        result = prime * result + ((getClubGroup() == null) ? 0 : getClubGroup().hashCode());
        result = prime * result + ((getClubPoints() == null) ? 0 : getClubPoints().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", clubId=" + clubId +
                ", clubName=" + clubName +
                ", clubImg=" + clubImg +
                ", clubCoach=" + clubCoach +
                ", clubGroup=" + clubGroup +
                ", clubPoints=" + clubPoints +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}