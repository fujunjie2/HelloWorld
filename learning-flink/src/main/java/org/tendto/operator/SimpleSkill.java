package org.tendto.operator;



public class SimpleSkill {

    private String name;

    public SimpleSkill(String skillName) {
        this.name = skillName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name + "_!";
    }
}
