package com.example.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Matches implements Serializable {
    private Long id;

    private Date time;

    private String homeTeam;

    private String visitingTeam;

    private String result;

    private String name;

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
        Matches other = (Matches) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()))
            && (this.getHomeTeam() == null ? other.getHomeTeam() == null : this.getHomeTeam().equals(other.getHomeTeam()))
            && (this.getVisitingTeam() == null ? other.getVisitingTeam() == null : this.getVisitingTeam().equals(other.getVisitingTeam()))
            && (this.getResult() == null ? other.getResult() == null : this.getResult().equals(other.getResult()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        result = prime * result + ((getHomeTeam() == null) ? 0 : getHomeTeam().hashCode());
        result = prime * result + ((getVisitingTeam() == null) ? 0 : getVisitingTeam().hashCode());
        result = prime * result + ((getResult() == null) ? 0 : getResult().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", time=" + time +
                ", homeTeam=" + homeTeam +
                ", visitingTeam=" + visitingTeam +
                ", result=" + result +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}