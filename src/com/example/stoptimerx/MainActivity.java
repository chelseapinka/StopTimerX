package com.example.stoptimerx;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends Activity implements OnClickListener
	{
		private static final String tag = "MainActivity";
		private MyCountDownTimer countDownTimer;
		private long timeElapsed;
		private boolean timerHasStarted = false;
		private Button stopB;
		private TextView text;
		private TextView timeElapsedView;
		private NumberPicker np1, np2, np3, np4;
		private boolean Code = false;
		private final long startTime = 15000;
		private final long interval = 1000;
		private int npv1, npv2, npv3, npv4;

		/** Called when the activity is first created. */
		@TargetApi(Build.VERSION_CODES.HONEYCOMB)
		@SuppressLint("NewApi")
		@Override
		public void onCreate(Bundle savedInstanceState)
			{
				super.onCreate(savedInstanceState);
				setContentView(R.layout.activity_main);
				stopB = (Button) this.findViewById(R.id.stopButton);
				stopB.setOnClickListener(this);

				text = (TextView) this.findViewById(R.id.labelView);
				timeElapsedView = (TextView) this.findViewById(R.id.timeView);
				countDownTimer = new MyCountDownTimer(startTime, interval);
				//text.setText(text.getText() );
				
				np1 = (NumberPicker) findViewById(R.id.np1);
				np1.setMaxValue(9);
				np1.setMinValue(0);
				np1.setWrapSelectorWheel(false);
				np2 = (NumberPicker) findViewById(R.id.np2);
				np2.setMaxValue(9);
				np2.setMinValue(0);
				np2.setWrapSelectorWheel(false);
				np3 = (NumberPicker) findViewById(R.id.np3);
				np3.setMaxValue(9);
				np3.setMinValue(0);
				np3.setWrapSelectorWheel(false);
				np4 = (NumberPicker) findViewById(R.id.np4);
				np4.setMaxValue(9);
				np4.setMinValue(0);
				np4.setWrapSelectorWheel(false);
				
		
		np1.setOnValueChangedListener(new OnValueChangeListener() {
				@Override
				public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
					npv1 = Integer.parseInt(String.valueOf(newVal));
				}
			});
		    
		 
		np2.setOnValueChangedListener(new OnValueChangeListener() {
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				npv2 = Integer.parseInt(String.valueOf(newVal));
			}
		});
	    
		np3.setOnValueChangedListener(new OnValueChangeListener() {
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				npv3 = Integer.parseInt(String.valueOf(newVal));
			}
		});
		
		np4.setOnValueChangedListener(new OnValueChangeListener() {
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				npv4 = Integer.parseInt(String.valueOf(newVal));
			}
		});
	}
		
		@Override
	    protected void onPause(){
			countDownTimer.cancel();
			timerHasStarted = false;
			super.onPause();
		}
		@Override
		protected void onResume(){
			super.onResume();
			np1.setValue(0);
			np2.setValue(0);
			np3.setValue(0);
			np4.setValue(0);
			countDownTimer.start();
			Code = false;
			timerHasStarted = true;

		}
		
		@Override
		public void onClick(View v)
			{
				if (timerHasStarted)
					{
						stopB.setText("StopClock");		
						if ( npv1 == 1 && npv2 == 2 && npv3 == 3 && npv4 == 4 ){
			    			countDownTimer.cancel();
			    			timeElapsedView.setText("Alarm Deactivated!");
			    			stopB.setText("ResetTimer");
			    			Code = true;
			    			timerHasStarted = false;
						}
					}
				else
					{
					stopB.setText("StopTimer");
						np1.setValue(0);
						np2.setValue(0);
						np3.setValue(0);
						np4.setValue(0);
						countDownTimer.start();
						timerHasStarted = true;
						Code = false;
						
					}
			}

		// CountDownTimer class
		public class MyCountDownTimer extends CountDownTimer
			{

				public MyCountDownTimer(long startTime, long interval)
					{
						super(startTime, interval);
					}

				@Override
				public void onFinish()
					{
						timeElapsedView.setText("Time's up!");
						if(!Code){
							Toast.makeText(MainActivity.this, "Oh no...", Toast.LENGTH_SHORT).show();  
			    			Intent intent = new Intent();
			    			intent.setClass(MainActivity.this, StopScreen.class);
			    			startActivity(intent);	
						}
						timerHasStarted = false;
					}

				@Override
				public void onTick(long millisUntilFinished)
					{
						timeElapsedView.setText("" + millisUntilFinished/1000 + " secs left");
						timeElapsed = millisUntilFinished;
					}
			}
	}