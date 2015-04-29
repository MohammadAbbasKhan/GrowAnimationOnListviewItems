package mg.ma.com.timercontrol;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textview);

        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                textView.setText(millisUntilFinished / 1000+"");
            }

            public void onFinish() {

            }
        }.start();
    }
}
