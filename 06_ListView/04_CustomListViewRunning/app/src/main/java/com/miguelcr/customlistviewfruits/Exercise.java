package com.miguelcr.customlistviewfruits;

/**
 * Created by miguelcampos on 6/6/16.
 */
public class Exercise {
    int icon;
    String name;
    int duration;
    int distance;

    public Exercise(int icon, String name, int duration, int distance) {
        this.icon = icon;
        this.name = name;
        this.duration = duration;
        this.distance = distance;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
