package mg.ma.com.multiplayergame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends Activity {

    ListView listView;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);

        myAdapter = new MyAdapter(MainActivity.this);
        listView.setAdapter(myAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        listView.setVisibility(View.VISIBLE);
        myAdapter.notifyDataSetChanged();

    }

    class MyAdapter extends BaseAdapter {
        private Context context;
        private Button button;
        private Long systime;
        Handler handler = new Handler();
        Boolean firstLoad = true;

        MyAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.one_item_adapter_text, null);
                button = (Button) convertView.findViewById(R.id.button);
                convertView.setTag(button);
            } else
                button = (Button) convertView.getTag();

            if (position == 0)
                systime = AnimationUtils.currentAnimationTimeMillis();
            else
                systime += 100;

            button.setText("item" + position);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    firstLoad = false;
                    notifyDataSetChanged();
                }
            });


            Animation anim;

            if (firstLoad) {
                anim = AnimationUtils.loadAnimation(
                        MainActivity.this, R.anim.grow_large);

                anim.setAnimationListener(/*new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        button.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                }*/null);
            } else {
                anim = AnimationUtils.loadAnimation(
                        MainActivity.this, R.anim.grow_small);

                anim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        if (position == 0)
                            listView.setVisibility(View.INVISIBLE);

                        firstLoad = true;
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });


            }

            anim.setStartTime(systime);
            anim.setDuration(500);
            anim.setRepeatCount(1);
            button.setAnimation(anim);

            /* handler.postDelayed(new Runnable() {
                 @Override
                 public void run() {
                     button.clearAnimation();
                 }
             }, 800);*/


            return convertView;
        }
    }

}
