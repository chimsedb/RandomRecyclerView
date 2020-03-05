package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rc_main);
        final LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
//        final LinearLayoutManager manager = new SpeedyLinearLayoutManager(getApplicationContext(), SpeedyLinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(manager);
        final List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            stringList.add("possiton " + i);
        }
        recyclerView.setAdapter(new RCAdapter(stringList));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int firstItemVisible = manager.findFirstVisibleItemPosition();
                if (firstItemVisible != 0 && firstItemVisible % stringList.size() == 0) {
                    recyclerView.getLayoutManager().scrollToPosition(0);
                }
            }
        });

        Button button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autoScroll();
            }
        });

    }

    public void autoScroll() {
        final int[] x = {150};
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                new CountDownTimer(6000, 30) {
                    public void onTick(long millisUntilFinished) {
                        Log.d("123123", x[0] + "");
                        if (x[0] == 0) return;
                        recyclerView.scrollBy(x[0], 0);
                        x[0]--;
                    }

                    public void onFinish() {
                        //you can add code for restarting timer here
                    }
                }.start();
            }
        });
//        final Handler handler = new Handler();
//        final Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                Log.d("123123", "23");
//                for (int i = 100; i > 0; i--) {
//                    recyclerView.scrollBy(i, 0);
//                    handler.postDelayed(this, 0);
//                }
//
//            }
//        };
//        handler.postDelayed(runnable, 100);
    }
}
