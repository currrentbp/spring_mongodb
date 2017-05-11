package com.bp.common;

import java.util.List;

/**
 * @author current_bp
 * @createTime 20170323
 */
public class StudentCondition extends BaseCondition{
    private String id;
    private String name;
    private String address;
    private List<String> hobbies;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }
}
