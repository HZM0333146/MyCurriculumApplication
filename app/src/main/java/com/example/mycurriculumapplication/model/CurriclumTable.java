package com.example.mycurriculumapplication.model;

import com.example.mycurriculumapplication.util.ConstantUtil;

public class CurriclumTable {
   private int tableId;
   private String name;
   CurriclumItem[][] curriclumItemsArray;
   public CurriclumTable(int tableId,String name,CurriclumItem[][] curriclumItemsArray){
      this.tableId=tableId;
      this.name=name;
      this.curriclumItemsArray=curriclumItemsArray;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setTableId(int tableId) {
      this.tableId = tableId;
   }

   public void setCurriclumItemsArray(CurriclumItem[][] curriclumItemsArray) {
      this.curriclumItemsArray = curriclumItemsArray;
   }

   public CurriclumItem[][] getCurriclumItemsArray() {
      return curriclumItemsArray;
   }

   public int getTableId() {
      return tableId;
   }

   public String getName() {
      return name;
   }
}
