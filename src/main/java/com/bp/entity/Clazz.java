package com.bp.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 班级
 * @author current_bp
 * @createTime 20170323
 */
public class Clazz implements Serializable{
    private static final long serialVersionUID = 6122352173556179900L;

    private String id ;
    private String name ;
    private List<Student> students;

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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Clazz{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", students=").append(students);
        sb.append('}');
        return sb.toString();
    }
}
