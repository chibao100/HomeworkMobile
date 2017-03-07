package com.example.thachchibao.homework3;

import android.os.ParcelUuid;

/**
 * Created by ThachChiBao on 2/24/2017.
 */

public class Recipe {
    public int id;
    public String name;
    public String introduction;
    public String time;
    public String ingredient;
    public String direction;
    public String image;
    public String image2;
    // Contructor


    public Recipe(int id, String name, String introduction, String time, String ingredient, String direction, String image, String image2) {
        this.id = id;
        this.name = name;
        this.introduction = introduction;
        this.time = time;
        this.ingredient = ingredient;
        this.direction = direction;
        this.image = image;
        this.image2 = image2;
    }
    // setter and getter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }
}
