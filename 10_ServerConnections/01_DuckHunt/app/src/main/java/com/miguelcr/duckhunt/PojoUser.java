package com.miguelcr.duckhunt;

/**
 * Created by miguelcampos on 17/6/16.
 */
public class PojoUser {
    private String id;
    private String nickname;
    private String points;

    public PojoUser() {
    }

    public PojoUser(String id, String nickname, String points) {
        this.id = id;
        this.nickname = nickname;
        this.points = points;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
