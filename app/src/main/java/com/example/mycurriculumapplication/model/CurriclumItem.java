package com.example.mycurriculumapplication.model;

public class CurriclumItem {
    int id;
    String naem,teacher,className,color;
    public CurriclumItem(int id,String naem,String teacher,String className,String color){
        this.id=id;
        this.naem=naem;
        this.teacher=teacher;
        this.className=className;
        this.color=color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaem() {
        return naem;
    }

    public void setNaem(String naem) {
        this.naem = naem;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
