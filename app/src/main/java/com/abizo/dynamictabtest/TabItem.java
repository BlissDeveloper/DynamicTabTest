package com.abizo.dynamictabtest;

public class TabItem {
    String body, name;

    public TabItem() {

    }

    public TabItem(String body, String name) {
        this.body = body;
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
