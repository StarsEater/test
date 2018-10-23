package com.example.qfz.selfservicespace.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qfz.selfservicespace.R;
import com.example.qfz.selfservicespace.object.Connection;
import com.example.qfz.selfservicespace.object.ConnectionRes;
import com.example.qfz.selfservicespace.object.Reservation;
import com.example.qfz.selfservicespace.object.ReservationInfo;
import com.example.qfz.selfservicespace.object.ReservationList;
import com.example.qfz.selfservicespace.object.StoreList;
import com.example.qfz.selfservicespace.object.User;
import com.google.gson.Gson;

import net.wujingchao.android.view.SimpleTagImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ReservationInfoActivity extends AppCompatActivity {

    ImageView head_image;
    ImageView twoDcode;



    @InjectView(R.id.content_reservation_id)
    EditText reservationID;
    @InjectView(R.id.head_image_left_text)
    EditText shopName;
    @InjectView(R.id.content_from_time_1)
    EditText from_time_1;
    @InjectView(R.id.content_from_time_2)
    EditText from_time_2;
    @InjectView(R.id.content_to_time_1)
    EditText to_time_1;
    @InjectView(R.id.content_to_time_2)
    EditText to_time_2;
    @InjectView(R.id.content_chair_table)
    EditText tables;
    @InjectView(R.id.content_pay_bail)
    EditText bail;
    @InjectView(R.id.head_image_right_text)
    EditText total;
    @InjectView(R.id.content_remark_content)
    EditText remark;
    @InjectView(R.id.content_sure_btn)
    TextView confirmBtn;
    @InjectView(R.id.head_image_center_text)
    TextView people_num;
    @InjectView(R.id.content_pay_way)
    TextView payway;
    @InjectView(R.id.head_image)
    ImageView form_head_image;
    @InjectView(R.id.twoDcode)
    ImageView form_twoDcode;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        ButterKnife.inject(this);

        final Intent intent = getIntent();
        final ReservationInfo reservation = (ReservationInfo) intent.getSerializableExtra("Reservation");

        reservationID.setText(reservation.reservationID);
        shopName.setText(reservation.storeName);
        from_time_1.setText(reservation.time);
        tables.setText(reservation.tables.toString());
        bail.setText(reservation.bail + "");
        total.setText(reservation.total + "");

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendRequest(reservation.reservationID);
            }
        });
    }

    private void SendRequest(final String requestMsg) {
        final Handler myhandler = new Handler() {
            public void handleMessage(Message msg) {
                //isNetError = msg.getData().getBoolean("isNetError");
                if(msg.what==0x123)
                {
                    //更新界面
                    Log.d("Pay for reservation", msg.getData().getString("msg"));
                    String message = msg.getData().getString("msg");

                    Gson gson = new Gson();
                    ConnectionRes connectionRes = gson.fromJson(message, ConnectionRes.class);

                    if(connectionRes.state == 0) {
                        User user = gson.fromJson(message, User.class);
                        StoreList storeList = gson.fromJson(message, StoreList.class);
                        ReservationList reservationList = gson.fromJson(message, ReservationList.class);

                        if(user == null)
                        {
                            Log.d("Pay for reservation", "User non-exist");
                        }
                        else if(storeList == null)
                        {
                            Log.d("Pay for reservation", "StoreList non-exist");
                        }
                        else if(reservationList == null)
                        {
                            Log.d("Pay for reservation", "ReservationList non-exist");
                        }
                        else
                        {
                            Intent intent = new Intent(ReservationInfoActivity.this, main_page.class);
                            intent.putExtra("user", user);
                            intent.putExtra("stores", storeList);
                            intent.putExtra("reservations", reservationList);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(ReservationInfoActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };

        new Thread(){
            public void run(){
                try {
                    //请求服务器返回用户数据
                    Connection connection = new Connection();
                    String msg = connection.connectServer(requestMsg, "/getInfo.json");

                    //如果成功返回
                    //将子线程中获得的用户信息传递到主线程 myhandler 中
                    Bundle bundle = new Bundle();
                    Message message = new Message();

                    bundle.putString("msg", msg);

                    message.setData(bundle);
                    message.what=0x123;
                    myhandler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}