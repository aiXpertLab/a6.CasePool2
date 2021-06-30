package com.reapex.sv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.huawei.agconnect.cloud.database.AGConnectCloudDB;
import com.huawei.agconnect.cloud.database.CloudDBZone;
import com.huawei.agconnect.cloud.database.CloudDBZoneConfig;
import com.huawei.agconnect.cloud.database.exceptions.AGConnectCloudDBException;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.reapex.sv.model.BookInfo;
import com.reapex.sv.model.ObjectTypeInfoHelper;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "====================";

    CloudDBZone mCloudDBZone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db();
    }

    private void db(){
        AGConnectCloudDB.initialize(this);                          //1. 在应用中初始化AGConnectCloudDB。
        AGConnectCloudDB mCloudDB = AGConnectCloudDB.getInstance();         //2. 获取AGConnectCloudDB实例，并创建对象类型。
        try {
            mCloudDB.createObjectType(ObjectTypeInfoHelper.getObjectTypeInfo());
        } catch (AGConnectCloudDBException e) {            Log.w(TAG, "createObjectType: " + e.getMessage());        }

        //3. 创建Cloud DB zone配置对象，并打开该Cloud DB zone（以Cloud DB zone的同步属性为缓存模式、访问属性为公共存储区为例）。
        CloudDBZoneConfig mConfig = new CloudDBZoneConfig("QuickStartDemo",CloudDBZoneConfig.CloudDBZoneSyncProperty.CLOUDDBZONE_CLOUD_CACHE,CloudDBZoneConfig.CloudDBZoneAccessProperty.CLOUDDBZONE_PUBLIC);
        mConfig.setPersistenceEnabled(true);
        Task<CloudDBZone> openDBZoneTask = mCloudDB.openCloudDBZone2(mConfig, true);
        openDBZoneTask.addOnSuccessListener(new OnSuccessListener<CloudDBZone>() {
            @Override
            public void onSuccess(CloudDBZone cloudDBZone) {
                Log.w(TAG, "open clouddbzone success");
                mCloudDBZone = cloudDBZone;
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Log.w(TAG, "open clouddbzone failed for " + e.getMessage());
            }
        });
    }

    public void myClick(View view) {
        if (mCloudDBZone == null) {
            Log.w(TAG, "CloudDBZone is null, try re-open it");
            return;
        }

        BookInfo bookInfo = new BookInfo();
        bookInfo.setAuthor("lu xun");
        bookInfo.setBookName("kuang ren ri ji");
        bookInfo.setId(121);

        Task<Integer> upsertTask = mCloudDBZone.executeUpsert(bookInfo);
        upsertTask.addOnSuccessListener(new OnSuccessListener<Integer>() {
            @Override
            public void onSuccess(Integer cloudDBZoneResult) {
                Log.w(TAG, "upsert " + cloudDBZoneResult + " records");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Log.w(TAG, "Failed  records");
            }
        });
    }
}