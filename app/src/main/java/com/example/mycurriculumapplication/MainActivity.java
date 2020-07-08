package com.example.mycurriculumapplication;

import android.os.Bundle;

import com.example.mycurriculumapplication.db.DBOperating;
import com.example.mycurriculumapplication.ui.CurriculumItemView;
import com.example.mycurriculumapplication.util.AlertDialogUtil;
import com.example.mycurriculumapplication.util.DateUtil;

import androidx.appcompat.app.AppCompatActivity;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends BaseActivity {
    private TextView monthDayText,weekText,mainTableNameText;
    private ImageView selectTableImv,inCourseImv,upCourseImv,otherUiImv;
    private RelativeLayout table_title_layout,table_layout;
    private CurriculumItemView[][] curriculumItemViewsTextArray;

    private String[] weekDays = {"周一", "周二", "周三", "周四", "周五", "週六", "周日"};
    private int tableTotalCol=8,tableTotalRow=8;//列與行
    private int weekDayNumber=tableTotalRow-1,dayCourseNumber=tableTotalCol-1;
    private int tableTotalWidth,tableTotalHeight,courseWidth,courseHeight,weekDayWidth,weekDayHeight,sessionWidth,sessionHeight,splitGridWidth,splitGridHeight;

    DBOperating dbOperating;
    int width,height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 列表布局文件
        table_title_layout = (RelativeLayout) this.findViewById(R.id.table_title);
        table_layout = (RelativeLayout) this.findViewById(R.id.course_table);

        dbOperating=new DBOperating(this);
        DateUtil a=new DateUtil();
        monthDayText=(TextView)findViewById(R.id.month_text);
        monthDayText.setText(a.getNowadaysMonthAndDate());;
        weekText=(TextView)findViewById(R.id.week_text);
        weekText.setText(a.getNowadaysWeek());

        mainTableNameText=(TextView)findViewById(R.id.main_table_name_text);
        mainTableNameText.setOnClickListener(onClickListener);
        selectTableImv=(ImageView)findViewById(R.id.select_table_imv);
        selectTableImv.setOnClickListener(onClickListener);
        inCourseImv=(ImageView)findViewById(R.id.in_course_imv);
        inCourseImv.setOnClickListener(onClickListener);
        upCourseImv=(ImageView)findViewById(R.id.up_course_imv);
        upCourseImv.setOnClickListener(onClickListener);
        otherUiImv=(ImageView)findViewById(R.id.other_ui_imv);
        otherUiImv.setOnClickListener(onClickListener);
        initTable();
    }
    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.select_table_imv:
                case R.id.main_table_name_text:
                    showOtherTableMessage();
                    break;
                case R.id.in_course_imv:
                    showInCourseView();
                    break;
                case R.id.up_course_imv:
                    showCourseMessage();
                    break;
                case R.id.other_ui_imv:
                    showOtherMessage();
                    break;
                 default:

                     break;
            }
        }
    };
    View.OnClickListener courseViewOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        }
    };
    private void showOtherTableMessage(){

    }
    private void showInCourseView(){
        nowRe();
    }
    View v ;
    void nowRe(){
        final LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        final RelativeLayout main_activity_layout=(RelativeLayout)findViewById(R.id.main_activity_layout);
        final View v = inflater.inflate(R.layout.c_d, null);
        final EditText inCourseEd=v.findViewById(R.id.in_course_ed);
        final EditText inTeacherEd=v.findViewById(R.id.in_teacher_ed);
        final EditText inClassEd=v.findViewById(R.id.in_class_ed);
        ImageView inColorIvm=v.findViewById(R.id.in_color_ivm);
        ImageView outIvm=v.findViewById(R.id.out_imv);

        outIvm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main_activity_layout.removeView(v);
            }
        });
        ImageView addIvm=v.findViewById(R.id.add_imv);
        addIvm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("showInCourseView","add");
                String courseName=inCourseEd.getText().toString();
                String teacherName=inCourseEd.getText().toString();
                String className=inCourseEd.getText().toString();
                String color="#0000000";
                dbOperating.addSubjectTableData(courseName,teacherName,className,color);
                main_activity_layout.removeView(v);
            }
        });
        int h=height/10;
        inCourseEd.getLayoutParams().height=h/3;
        inTeacherEd.getLayoutParams().height=h/3;
        inClassEd.getLayoutParams().height=h/3;
        RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(width, h);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP,R.id.dialog_view);
        main_activity_layout.addView(v,layoutParams);
    }
void re(){
    LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
    final View v = inflater.inflate(R.layout.c_d, null);
    RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(width, height*15/100);
    layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,R.id.dialog_view);
    RelativeLayout main_activity_layout=(RelativeLayout)findViewById(R.id.main_activity_layout);
    main_activity_layout.addView(v,layoutParams);
    //
    LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0);
    lp.weight=7.5f;
    LinearLayout n=(LinearLayout)this.findViewById(R.id.xxx);
    n.setLayoutParams(lp);
    //
    TextView textView=(TextView)findViewById(R.id.white_view);
    textView.setVisibility(View.VISIBLE);
}



    void FrameLayoutTest(){
//        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
//        final View v = inflater.inflate(R.layout.c_d, null);
//        FrameLayout.LayoutParams fp = new FrameLayout.LayoutParams(width, height*15/100);
//        fp.setMargins(0,height*85/100,0,0);
//        main_activity_layout= (FrameLayout) this.findViewById(R.id.main_activity_layout);
//        main_activity_layout.addView(v,fp);
//        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0);
//        lp.weight=7.5f;
//        n=(LinearLayout)this.findViewById(R.id.xxx);
//        n.setLayoutParams(lp);
    }
    private void showCourseMessage(){

    }
    private void showOtherMessage(){

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //初始化课程表格
    private void initTable() {
        curriculumItemViewsTextArray=new CurriculumItemView[tableTotalCol][tableTotalRow];
        //螢幕像素長寬
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        width=dm.widthPixels;
        height=dm.heightPixels;
        Log.v("DisplayMetrics","dm.density:"+dm.density);
        Log.v("DisplayMetrics","dm.densityDpi:"+dm.densityDpi);
        Log.v("DisplayMetrics","dm.scaledDensity:"+dm.scaledDensity);
        Log.v("DisplayMetrics","dm.widthPixels:"+dm.widthPixels);
        Log.v("DisplayMetrics","dm.heightPixels:"+dm.heightPixels);
        Log.v("DisplayMetrics","dm.xdpi:"+dm.xdpi);
        Log.v("DisplayMetrics","dm.ydpi:"+dm.ydpi);
        //表格總寬高
        tableTotalWidth = width;
        tableTotalHeight = height*9/10;
        //表格內的格子寬高度
        splitGridWidth=tableTotalWidth*1/10;
        splitGridHeight=tableTotalHeight*5/100;
        weekDayWidth=tableTotalWidth*9/10/weekDayNumber;
        weekDayHeight=tableTotalHeight*5/100;

        sessionWidth=tableTotalWidth*1/10;
        sessionHeight=tableTotalHeight*95/100/8;//*0.95/8
        courseWidth=tableTotalWidth*9/10/weekDayNumber;
        courseHeight=tableTotalHeight*95/100/8;//*0.95/8

        //動態星期格子
        for (int i = 0; i < tableTotalRow; i++) {
            CurriculumItemView tx = new CurriculumItemView(this);
            int id=i + 1;
            tx.setId(id);
            System.out.println("id:"+id);
            tx.setGravity(Gravity.CENTER);
            tx.setTextAppearance(this, R.style.CourseTableText);
            tx.setBackgroundDrawable(getResources().getDrawable(R.drawable.table_first_colum));

            //相对布局参数
            RelativeLayout.LayoutParams rp = new RelativeLayout.LayoutParams(weekDayWidth, weekDayHeight);
            if (id == 1){
                rp.width = splitGridWidth;
                rp.addRule(RelativeLayout.ALIGN_PARENT_START);
            }else {
                rp.addRule(RelativeLayout.RIGHT_OF, id-1);
                rp.addRule(RelativeLayout.ALIGN_TOP, id-1);
                tx.setText(weekDays[id-2]);
            }
            tx.setLayoutParams(rp);
            table_title_layout.addView(tx);
        }
        //動態產生表格
        for (int i = 0; i < tableTotalCol; i++) {
            for (int j = 0; j < tableTotalRow; j++) {
                curriculumItemViewsTextArray[i][j] = new CurriculumItemView(this);
                int id=i* tableTotalRow + j + 1;
                System.out.println("id:"+id);
                curriculumItemViewsTextArray[i][j].setId(id);
                //相对布局参数
                RelativeLayout.LayoutParams rp = new RelativeLayout.LayoutParams(
                        courseWidth,
                        courseHeight);
                //文字对齐方式
                curriculumItemViewsTextArray[i][j].setGravity(Gravity.CENTER);
                //字体样式
                curriculumItemViewsTextArray[i][j].setTextAppearance(this, R.style.CourseTableText);
                //如果是第一行，需要设置课的序号（1 到 12)
                //設置表格相對位子
                if (j == 0) {
                    curriculumItemViewsTextArray[i][j].setBackgroundDrawable(getResources().getDrawable(R.drawable.table_first_colum));
                    curriculumItemViewsTextArray[i][j].setText(String.valueOf(i+1));
                    rp.width = sessionWidth;
                    if (i == 0){
                        rp.addRule(RelativeLayout.ALIGN_PARENT_START);
                    }else {
                        int reId=(i-1) * tableTotalRow +1;
                        rp.addRule(RelativeLayout.BELOW, reId);
                        System.out.println("id-a:"+reId);
                    }
                } else {
                    int reId=i * tableTotalRow + j;
                    rp.addRule(RelativeLayout.RIGHT_OF, reId);
                    rp.addRule(RelativeLayout.ALIGN_TOP, reId);
                    curriculumItemViewsTextArray[i][j].setText("");
                    curriculumItemViewsTextArray[i][j].setOnClickListener(onClickListener);
                    System.out.println("id-b:"+reId);
                }
                curriculumItemViewsTextArray[i][j].setLayoutParams(rp);
                table_layout.addView(curriculumItemViewsTextArray[i][j]);
            }
        }
    }
}
