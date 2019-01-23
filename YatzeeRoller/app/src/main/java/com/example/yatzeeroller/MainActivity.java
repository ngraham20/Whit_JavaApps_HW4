package com.example.yatzeeroller;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;

import java.util.HashSet;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Dice[] dice = new Dice[5];

    HashSet<Thread> activeThreads = new HashSet<>();

    /**
     * Initialize the dice, threads, and the FAB
     * @param savedInstanceState the instanceState to use
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dice[0] = new Dice(this,R.id.dice1);
        dice[1] = new Dice(this,R.id.dice2);
        dice[2] = new Dice(this,R.id.dice3);
        dice[3] = new Dice(this,R.id.dice4);
        dice[4] = new Dice(this,R.id.dice5);

        for(Dice dice : this.dice)
        {
            activeThreads.add(new Thread(dice));
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollAll();
            }
        });
    }

    /**
     * Roll all dice by starting their threads
     */
    private void rollAll()
    {
        for(Thread thread: activeThreads)
        {
            thread.start();
        }
    }


    /**
     * Private Dice Object class
     */
    private class Dice implements Runnable
    {

        private final int[] imageDrawables = {
                R.drawable.dice_green_1,
                R.drawable.dice_green_2,
                R.drawable.dice_green_3,
                R.drawable.dice_green_4,
                R.drawable.dice_green_5,
                R.drawable.dice_green_6};

        private int side;
        ImageView imageView;
        Activity activity;

        /**
         * Constructor
         * @param activity the activity context to use
         * @param imageViewId the imageViewId to specify which imageView to modify
         */
        Dice(Activity activity, int imageViewId)
        {
            this.activity = activity;
            this.imageView = this.activity.findViewById(imageViewId);
        }

        /**
         * When run, roll the dice
         */
        @Override
        public void run() {
            roll();
        }

        /**
         * Roll the dice
         */
        private void roll()
        {
            Random rand = new Random();

            for(int i = 0; i < rand.nextInt(300) + 100; i++)
            {
                int side = rand.nextInt(6);
                this.side = imageDrawables[side];

                final int _side = imageDrawables[side];

                ((Activity) this.activity).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageResource(_side);
                    }
                });

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
