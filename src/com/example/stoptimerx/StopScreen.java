package com.example.stoptimerx;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class StopScreen extends Activity implements OnClickListener{

	private Button backB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_screen);
        backB = (Button) this.findViewById(R.id.backButton);
		backB.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_stop_screen, menu);
        return true;
    }
    
    @Override
    protected void onPause(){
    
		Intent intent = new Intent();
		intent.setClass(StopScreen.this, MainActivity.class);
		startActivity(intent);
		super.onPause();
    }
    
    @Override
	public void onClick(View v)
	{
    	Toast.makeText(StopScreen.this, "Back to Timer", Toast.LENGTH_SHORT).show();  
    	Intent intent = new Intent();
		intent.setClass(StopScreen.this, MainActivity.class);
		startActivity(intent);
	}

}
