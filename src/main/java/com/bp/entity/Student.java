package com.bp.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 学生实体
 * @author current_bp
 * @createTime 20170323
 */
public class Student implements Serializable{
    private static final long serialVersionUID = -899638831466051308L;

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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Student{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", hobbies=").append(hobbies);
        sb.append('}');
        return sb.toString();
    }
}
