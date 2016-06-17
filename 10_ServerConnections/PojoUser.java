package com.example.jorge.pato;

/**
 * Created by jorge on 04/02/2016.
 */
public class PojoUser {
    private String id;

    private String nickname;

    private String points;

    public PojoUsuario() {
    }

    public PojoUser(String id, String nickname, String points) {
        this.id = id;
        this.nickname = nickname;
        this.points = points;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getNickname ()
    {
        return nickname;
    }

    public void setNickname (String nickname)
    {
        this.nickname = nickname;
    }

    public String getPoints ()
    {
        return points;
    }

    public void setPoints (String points)
    {
        this.points = points;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", nickname = "+nickname+", points = "+points+"]";
    }
}
