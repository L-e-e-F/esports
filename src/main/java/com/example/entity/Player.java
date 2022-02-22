package com.example.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Player implements Serializable {
    /**
     * 选手id
     */
    private Long playerId;

    /**
     * 选手姓名
     */
    private String playerName;

    /**
     * 选手位置
     */
    private Integer playerPosition;

    /**
     * 选手俱乐部id
     */
    private Long playerClub;

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
        Player other = (Player) that;
        return (this.getPlayerId() == null ? other.getPlayerId() == null : this.getPlayerId().equals(other.getPlayerId()))
            && (this.getPlayerName() == null ? other.getPlayerName() == null : this.getPlayerName().equals(other.getPlayerName()))
            && (this.getPlayerPosition() == null ? other.getPlayerPosition() == null : this.getPlayerPosition().equals(other.getPlayerPosition()))
            && (this.getPlayerClub() == null ? other.getPlayerClub() == null : this.getPlayerClub().equals(other.getPlayerClub()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPlayerId() == null) ? 0 : getPlayerId().hashCode());
        result = prime * result + ((getPlayerName() == null) ? 0 : getPlayerName().hashCode());
        result = prime * result + ((getPlayerPosition() == null) ? 0 : getPlayerPosition().hashCode());
        result = prime * result + ((getPlayerClub() == null) ? 0 : getPlayerClub().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", playerId=" + playerId +
                ", playerName=" + playerName +
                ", playerPosition=" + playerPosition +
                ", playerClub=" + playerClub +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}