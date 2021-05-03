package com.example.foodscanner;

import java.util.HashMap;

public class Member {
    private String name;
    private String ParentId;

    private String A1;
    private String A2;
    private String A3;
    private String A4;
    private String A5;
    private String A6;
    private String A7;
    private String A8;
    private String A9;
    private String A10;
    private String cat1value;
    private String cat2value;
    private String cat3value;
    private String cat4value;
    private HashMap<String, Object> dates;



    public Member(){}

    public Member(String name, String parentId, String A1, String A2, String A3, String A4, String A5,String A6,String A7,String A8,String A9,String A10, String cat1value, String cat2value, String cat3value, String cat4value, HashMap dates) {
        this.name = name;
        ParentId = parentId;
        this.A1 = A1;
        this.A2 = A2;
        this.A3 = A3;
        this.A4 = A4;
        this.A5 = A5;
        this.A6 = A6;
        this.A7 = A7;
        this.A8 = A8;
        this.A9 = A9;
        this.A10 = A10;
        this.cat1value = cat1value;
        this.cat2value = cat2value;
        this.cat3value = cat3value;
        this.cat4value = cat4value;
        this.dates = dates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return ParentId;
    }

    public void setParentId(String parentId) {
        ParentId = parentId;
    }

    public String getA1() { return A1; }

    public void setA1(String A1) { this.A1 = A1; }

    public String getA2() { return A2; }

    public void setA2(String A2) { this.A2 = A2; }

    public String getA3() { return A3; }

    public void setA3(String A3) { this.A3 = A3; }

    public String getA4() { return A4; }

    public void setA4(String A4) { this.A4 = A4; }

    public String getA5() {
        return A5;
    }

    public void setA5(String A5) { this.A5 = A5; }

    public String getA6() {
        return A6;
    }

    public void setA6(String A6) { this.A6 = A6; }

    public String getA7() {
        return A7;
    }

    public void setA7(String A7) { this.A7 = A7; }

    public String getA8() {
        return A8;
    }

    public void setA8(String A8) { this.A8 = A8; }

    public String getA9() {
        return A9;
    }

    public void setA9(String A9) { this.A9 = A9; }

    public String getA10() {
        return A10;
    }

    public void setA10(String A10) { this.A10 = A10; }

    public String getCat1value() {
        return cat1value;
    }

    public void setCat1value(String cat1value) {
        this.cat1value = cat1value;
    }

    public String getCat2value() {
        return cat2value;
    }

    public void setCat2value(String cat2value) {
        this.cat2value = cat2value;
    }

    public String getCat3value() {
        return cat3value;
    }

    public void setCat3value(String cat3value) {
        this.cat3value = cat3value;
    }

    public String getCat4value() {
        return cat4value;
    }

    public void setCat4value(String cat4value) {
        this.cat4value = cat4value;
    }

    public void addDatesMap(){

    }

    public HashMap getDates(){
        return this.dates;
    }

    public HashMap<String, Object> toMap(){
        HashMap<String, Object> hash = new HashMap<>();

        if(getA1() != null && !getA1().isEmpty()){
            hash.put("A1", getA1());
        }

        if(getA2() != null && !getA2().isEmpty()){
            hash.put("A2", getA2());
        }

        if(getA3() != null && !getA3().isEmpty()){
            hash.put("A3", getA3());
        }

        if(getA4() != null && !getA4().isEmpty()){
            hash.put("A4", getA4());
        }

        if(getA5() != null && !getA5().isEmpty()){
            hash.put("A5", getA5());
        }
        if(getA6() != null && !getA6().isEmpty()){
            hash.put("A6", getA6());
        }
        if(getA7() != null && !getA7().isEmpty()){
            hash.put("A7", getA7());
        }
        if(getA8() != null && !getA8().isEmpty()){
            hash.put("A8", getA8());
        }
        if(getA9() != null && !getA9().isEmpty()){
            hash.put("A9", getA9());
        }
        if(getA10() != null && !getA10().isEmpty()){
            hash.put("A10", getA10());
        }

        if(getName() != null && !getName().isEmpty()){
            hash.put("name", getName());
        }

        return hash;
    }
}
