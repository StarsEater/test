package com.example.qfz.selfservicespace.model;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.qfz.selfservicespace.R;
import com.example.qfz.selfservicespace.object.Reservation;
import com.ramotion.foldingcell.FoldingCell;

import net.wujingchao.android.view.SimpleTagImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class reservationAdapter extends ArrayAdapter<Reservation> {
    private static HashMap<String,String> status_map = new HashMap<String, String>(){
        {
            put("已结束","#CC0033");// red_finished
            put("未付款","#000000");//yellow_waiting_payed
            put("可使用","#009100");//green_using
            put("已取消","#CCCCCC");//silver_canceled
        }
    };
    private static HashMap<String,String> btn_map = new HashMap<String, String>(){
        {
            put("已结束","确定");// red_finished
            put("未付款","去付款");//yellow_waiting_for_pay
            put("可使用","生成二维码");//green_using
            put("已取消","确定");//silver_canceled
        }
    };
    private HashSet<Integer> unfoldedIndexes = new HashSet<>();

    public reservationAdapter(Context context, ArrayList<Reservation> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // get item for selected view
        final Reservation item = getItem(position);
        // if cell is exists - reuse it, if not - create the new one from resource
        FoldingCell cell = (FoldingCell) convertView;
        final ViewHolder viewHolder;
        if (cell == null) {
            viewHolder = new ViewHolder();
            LayoutInflater vi = LayoutInflater.from(getContext());
            cell = (FoldingCell) vi.inflate(R.layout.cell, parent, false);
            // binding view parts to view holder
            viewHolder.title_price = cell.findViewById(R.id.title_price);
            viewHolder.title_from_time = cell.findViewById(R.id.title_from_time);
            viewHolder.title_to_time = cell.findViewById(R.id.title_to_time);
            viewHolder.title_barname = cell.findViewById(R.id.title_barname);
            viewHolder.title_people_num = cell.findViewById(R.id.title_people_num);
            viewHolder.title_state = cell.findViewById(R.id.title_state);
            viewHolder.head_image_left_text = cell.findViewById(R.id.head_image_left_text);     //shopname
            viewHolder.head_image_right_text = cell.findViewById(R.id.head_image_right_text);    //小计
            viewHolder.head_image_center_text = cell.findViewById(R.id.head_image_center_text);   //人数
            viewHolder.content_from_time_1 = cell.findViewById(R.id.content_from_time_1);
            viewHolder.content_from_time_2 = cell.findViewById(R.id.content_from_time_2);
            viewHolder.content_to_time_1 = cell.findViewById(R.id.content_to_time_1);
            viewHolder.content_to_time_2 = cell.findViewById(R.id.content_to_time_2);
            viewHolder.content_chair_table = cell.findViewById(R.id.content_chair_table);
            viewHolder.content_pay_way = cell.findViewById(R.id.content_pay_way);
            viewHolder.content_pay_bail = cell.findViewById(R.id.content_pay_bail);      //保证金
            viewHolder.content_remark_content = cell.findViewById(R.id.content_remark_content);
            viewHolder.content_sure_btn = cell.findViewById(R.id.content_sure_btn);
            viewHolder.head_image  = cell.findViewById(R.id.head_image);
            viewHolder.reservation_state_sign  = cell.findViewById(R.id.reservation_state_sign);
            viewHolder.twoDcode  = cell.findViewById(R.id.twoDcode);
            cell.setTag(viewHolder);
        } else {
            // for existing cell set valid valid state(without animation)
            if (unfoldedIndexes.contains(position)) {
                cell.unfold(true);
            } else {
                cell.fold(true);
            }
            viewHolder = (ViewHolder) cell.getTag();
        }

        if (null == item)
            return cell;

        // bind data from selected element to view through view holder
        viewHolder.title_price.setText(item.getTotal_amount());
        viewHolder.title_from_time.setText(item.getScheduled_day() +"/"+ item.getBegin_hour() );
        viewHolder.title_to_time.setText(item.getScheduled_day() +"/"+item.getEnd_hour());
        viewHolder.title_barname.setText(item.getBar_name());
        viewHolder.title_people_num.setText(item.getPeople_num());
        viewHolder.title_state.setText(item.getStatus());
        viewHolder.content_reservation_id.setText(item.getOrder_no());
        viewHolder.head_image_left_text.setText(item.getBar_name());     //shopname
        viewHolder.head_image_right_text.setText(item.getTotal_amount());    //小计
        viewHolder.head_image_center_text.setText(item.getPeople_num());   //人数
        viewHolder.content_from_time_1.setText(item.getBegin_hour());
        viewHolder.content_from_time_2.setText(item.getScheduled_day());
        viewHolder.content_to_time_1.setText(item.getEnd_hour());
        viewHolder.content_to_time_2.setText(item.getScheduled_day());
        viewHolder.content_chair_table.setText(item.getSeat_ids());
        viewHolder.content_pay_way.setText(item.getPay_channel());
        viewHolder.content_pay_bail.setText(item.getBail_amount());      //保证金
        viewHolder.content_remark_content.setText("附加要求");
        viewHolder.content_sure_btn.setText(btn_map.get(item.getStatus()));
        viewHolder.content_sure_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"确定",Toast.LENGTH_SHORT).show();
            }
        });
        /**第一页标签颜色、字表示订单的状态       */
        viewHolder.reservation_state_sign.setTagBackgroundColor(
                Color.parseColor(status_map.get(item.getStatus()))
        );
        viewHolder.reservation_state_sign.setTagText(item.getStatus());
        /** 店铺图片      */
        Glide.with(getContext()).load(item.getBar_image_url()).into(viewHolder.head_image);
        return cell;
    }

    // simple methods for register cell state changes
    public void registerToggle(int position) {
        if (unfoldedIndexes.contains(position))
            registerFold(position);
        else
            registerUnfold(position);
    }

    public void registerFold(int position) {
        unfoldedIndexes.remove(position);
    }

    public void registerUnfold(int position) {
        unfoldedIndexes.add(position);
    }


    // View lookup cache
    private static class ViewHolder {
       TextView  title_price,
                 title_from_time,
                 title_to_time,
                 title_barname,
                 title_people_num,
                 title_state,

                 head_image_left_text,     //shopname
                 head_image_right_text,    //小计
                 head_image_center_text,   //人数
                 content_from_time_1,
                 content_from_time_2,
                 content_to_time_1,
                 content_to_time_2,
                 content_chair_table,
                 content_pay_way,
                 content_pay_bail,      //保证金
                 content_remark_content,
                 content_sure_btn,
                 content_reservation_id  ;
       SimpleTagImageView reservation_state_sign;
       ImageView head_image;
       ImageView twoDcode;

    }
}
