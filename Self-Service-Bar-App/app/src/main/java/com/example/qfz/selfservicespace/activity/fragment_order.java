package com.example.qfz.selfservicespace.activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;

import com.example.qfz.selfservicespace.R;
import com.example.qfz.selfservicespace.model.reservationAdapter;
import com.example.qfz.selfservicespace.object.Reservation;
import com.example.qfz.selfservicespace.object.ReservationList;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;

public class fragment_order extends Fragment {
    private RecyclerView recyclerView;
    private ReservationList reservationList = new ReservationList();
    private reservationAdapter adapter;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private ImageView ReservationImageView;
    private ListView orderListView;
    public void onCreateOptionsMenu(final Menu menu, MenuInflater inflater) {
    }

    private void SetView(ReservationList reservationList) {
        initListView(reservationList);
        main_page act = (main_page) getActivity();
        ArrayList<Reservation> temp = act.reservationList.reservations;;
        if (getActivity() == null)
            Log.d("onCreateView", "getActivity==null");
        adapter = new reservationAdapter(getContext(),reservationList.reservations);
        orderListView.setAdapter(adapter);
        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                // toggle clicked cell state
                ((FoldingCell) view).toggle(false);
                // register in adapter that state for selected cell is toggled
                adapter.registerToggle(pos);
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_order,container,false);
        orderListView = view.findViewById(R.id.mainListView);
        toolbar = view.findViewById(R.id.toolbar);
        collapsingToolbar = view.findViewById(R.id.collapsing_toolbar);
        ReservationImageView = view. findViewById(R.id.reservation_image_view);

        main_page act = (main_page) this.getActivity();
        SetView(act.reservationList);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //对TOOLBAT的设置
        ((AppCompatActivity)getActivity()).setSupportActionBar((Toolbar)view.findViewById(R.id.toolbar_search));
        setHasOptionsMenu(true);
        collapsingToolbar.setTitle("你的全部订单");
        Glide.with(getActivity()).load("https://cdn.dribbble.com/users/41854/screenshots/3761050/pride_twitter.gif").into(ReservationImageView);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //设置初始时间，结束时间，人数监听事件

    }

    private void initListView(ReservationList ReservationList) {
        reservationList = ReservationList;
    }
}
