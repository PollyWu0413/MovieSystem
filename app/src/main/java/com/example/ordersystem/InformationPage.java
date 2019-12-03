package com.example.ordersystem;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class InformationPage extends AppCompatActivity {
    ImageView p2_img;
    TextView p2_name,p2_date,p2_info;
    Button btn_back,btn_buy;
    Intent intent;
    Bundle get_bag;
    String idReserve = "reserve_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_page);

        p2_name = findViewById(R.id.txtName);
        p2_date = findViewById(R.id.txtDate);
        p2_img = findViewById(R.id.pg2Img);
        p2_info = findViewById(R.id.txtInfo);
        btn_back = findViewById(R.id.btnBack);
        btn_buy = findViewById(R.id.btnBuy);
        intent = new Intent();

        //Bundle接收資料
        get_bag = getIntent().getExtras();
        String chooseName = get_bag.getString("chooseName");
        String chooseDate = get_bag.getString("chooseDate");
        String chooseInfo = get_bag.getString("chooseInfo");
        int chooseImg = get_bag.getInt("chooseImg");
        p2_name.setText(chooseName);
        p2_date.setText(chooseDate);
        p2_info.setText(chooseInfo);
        p2_img.setImageResource(chooseImg);

        //跳回MainActivity
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(InformationPage.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //訂購完成後跳出訂購成功Notification
        btn_buy.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                //設定Notification
                NotificationManager notificationManager = (NotificationManager) getSystemService(v.getContext().NOTIFICATION_SERVICE);
                //通知1設定
                NotificationChannel channel = new NotificationChannel(
                        idReserve,"Channel Reserve",
                        NotificationManager.IMPORTANCE_HIGH);
                        channel.setDescription("已訂票成功!!");
                        channel.enableLights(true);
                        channel.enableVibration(true);
                        //依設定建立通知
                notificationManager.createNotificationChannel(channel);
                //第一個訊息
                NotificationCompat.Builder builder = new NotificationCompat.Builder(v.getContext(),idReserve)
                        .setSmallIcon(R.mipmap.movie)
                        .setContentTitle("已成功訂票!!")
                        .setContentText("祝您觀影愉快~~")
                        .setAutoCancel(true);
                //啟動通知
                notificationManager.notify(0,builder.build());
            }
        });

    }
}
