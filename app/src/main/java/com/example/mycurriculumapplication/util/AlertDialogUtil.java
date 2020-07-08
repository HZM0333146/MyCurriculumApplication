package com.example.mycurriculumapplication.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.mycurriculumapplication.R;

public class AlertDialogUtil {
    void showBaseDialog(final Context context){
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("基本訊息對話按鈕");
        dialog.setMessage("基本訊息對話功能介紹");
        dialog.setNegativeButton("NO",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "我還尚未了解",Toast.LENGTH_SHORT).show();
            }

        });
        dialog.setPositiveButton("YES",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "我了解了",Toast.LENGTH_SHORT).show();
            }

        });
        dialog.setNeutralButton("取消",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "取消",Toast.LENGTH_SHORT).show();
            }

        });
        dialog.show();
    }
    void showListDialog(final Context context){
        final String[] dinner = {"腿庫","雞蛋糕","沙威瑪","澳美客","麵線","麵疙瘩"};

        AlertDialog.Builder dialog_list = new AlertDialog.Builder(context);
        dialog_list.setTitle("利用List呈現");
        dialog_list.setItems(dinner, new DialogInterface.OnClickListener(){
            @Override

            //只要你在onClick處理事件內，使用which參數，就可以知道按下陣列裡的哪一個了
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "你選的是" + dinner[which], Toast.LENGTH_SHORT).show();
            }
        });
        dialog_list.show();
    }
    public static void showViewDialog(final Context context, int layoutId){
        LayoutInflater inflater = LayoutInflater.from(context);
        final View v = inflater.inflate(layoutId, null);
        AlertDialog.Builder adb=new AlertDialog.Builder(context);
        adb.setTitle("請輸入你的id")
        .setView(v)
        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "你的id是", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog=adb.create();
        //获取当前Activity所在的窗体
        Window dialogWindow = alertDialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity( Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.windowAnimations= R.style.ActionSheetDialogStyle;
        lp.y = 20;//设置Dialog距离底部的距离
        // 将属性设置给窗体
        dialogWindow.setAttributes(lp);
        alertDialog.show();
    }

}
