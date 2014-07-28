package com.launcher.ipadlauncher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Toast;

import com.launcher.Utils.constantUtils;
import com.launcher.Utils.jsonUtils;
import com.launcher.Utils.rootUtils;
import com.launcher.adapter.LauncherAdapter;
import com.launcher.myview.MyGridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class LauncherActivity extends Activity {

    private LauncherAdapter adapter;
    private MyGridView gridView_one;
    private MyGridView gridView_two;
    private MyGridView gridView_three;
    private ArrayList<Integer> list = new ArrayList<Integer>();
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.activity_launcher);

        try {
            rootUtils.preparezlsu(this);
            Process process = Runtime.getRuntime().exec("su");
            DataOutputStream os = new DataOutputStream(
                    process.getOutputStream());
            os.writeBytes("mount -oremount,rw /dev/block/mtdblock3 /system\n");
//			"busybox cp /data/data/com.zl.movepkgdemo/zlsu /system/bin/zlsu \n"
            os.writeBytes("busybox cp /data/app/com.launcher.ipadlauncher-1.apk /system/app"
                    +"\n");
            Log.e("ssss","sds");
//			"busybox chown 0:0 /system/bin/zlsu \n"
            os.writeBytes("busybox chown 0:0 /system/app/com.launcher.ipadlauncher-1.apk"
                    + "\n");
//			"chmod 4755 /system/bin/zlsu \n"
            os.writeBytes("chmod 4755 /system/app/com.launcher.ipadlauncher-1.apk" + "\n");
            os.writeBytes("exit\n");
            os.flush();

            Process process1 = Runtime.getRuntime().exec("su");
            DataOutputStream os1 = new DataOutputStream(
                    process1.getOutputStream());
            os1.writeBytes("mount -oremount,rw /dev/block/mtdblock3 /system\n");
//			"busybox cp /data/data/com.zl.movepkgdemo/zlsu /system/bin/zlsu \n"
            os1.writeBytes("busybox cp /data/app/com.launcher.ipadlauncher-2.apk /system/app"
                    +"\n");
            Log.e("ssss","sds");
//			"busybox chown 0:0 /system/bin/zlsu \n"
            os1.writeBytes("busybox chown 0:0 /system/app/com.launcher.ipadlauncher-2.apk"
                    + "\n");
//			"chmod 4755 /system/bin/zlsu \n"
            os1.writeBytes("chmod 4755 /system/app/com.launcher.ipadlauncher-2.apk" + "\n");
            os1.writeBytes("exit\n");
            os1.flush();
        }catch (IOException e){}
//        //网络获取数据
//        Observable.from(new TestData()).mapMany(new Func1<TestData, Observable<TestData>>() {
//            @Override
//            public Observable<TestData> call(TestData testData) {
//                return ApiManager.getWeatherData();
//            }
//        }).subscribeOn(Schedulers.threadPoolForIO())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<TestData>() {
//                    @Override
//                    public void call(TestData testData) {
//                        Log.e("sss",testData.author);
//                    }
//                });
        try {
            JSONObject jsonObject = new JSONObject(jsonUtils.getJson(LauncherActivity.this));
            String detail = jsonObject.getString("specialapp");
            JSONArray array = new JSONArray(detail);
            JSONObject jsonObject1 = (JSONObject)array.get(0);
            name = jsonObject1.getString("packagename");
        }catch (JSONException e)
        {}

        list.add(R.drawable.home_dcruise_down);
        list.add(R.drawable.home_flight_down);
        list.add(R.drawable.home_hotel_down);
        list.add(R.drawable.home_scenery_down);
        list.add(R.drawable.home_strategy_down);
        list.add(R.drawable.home_train_down);
        list.add(R.drawable.home_travel_down);
        list.add(R.drawable.home_vacation_down);
        initView();

        adapter = new LauncherAdapter(this,list);

        gridView_one.setAdapter(adapter);
        gridView_two.setAdapter(adapter);
        gridView_three.setAdapter(adapter);
        gridView_one.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startAPP(name);
            }
        });
    }

    private void initView(){
        gridView_one = (MyGridView)findViewById(R.id.gridview_one);
        gridView_two = (MyGridView)findViewById(R.id.gridview_two);
        gridView_three = (MyGridView)findViewById(R.id.gridview_three);
    }

    /*
* 启动一个app
*/
    public void startAPP(String appPackageName){
        try{
            Intent intent = this.getPackageManager().getLaunchIntentForPackage(appPackageName);
            startActivity(intent);
        }catch(Exception e){
            Toast.makeText(this, "没有安装", Toast.LENGTH_LONG).show();
        }
    }
}
