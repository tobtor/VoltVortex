package com.example.voltvortex.Models;

public class ZSComponentModel {

    private int componentID;
    private String component;
    private String classOfComponent;

    public ZSComponentModel(int componentID, String component, String classOfComponent) {
        this.componentID = componentID;
        this.component = component;
        this.classOfComponent = classOfComponent;
    }

    public int getComponentID() {
        return componentID;
    }

    public void setComponentID(int componentID) {
        this.componentID = componentID;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getClassOfComponent() {
        return classOfComponent;
    }

    public void setClassOfComponent(String classOfComponent) {
        this.classOfComponent = classOfComponent;
    }
}