package ro.pub.cs.systems.eim.practicaltest01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import ro.pub.cs.systems.eim.practicaltest01.R;

public class PracticalTest01SecondaryActivity extends Activity {

	EditText totalClicks = null;
	
	Button okButton = null;
	Button cancelButton = null;
	ButtonClickListener btnListener = new ButtonClickListener();
	
    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
        	if (view.getId() == R.id.cancel_button) {
        		setResult(RESULT_CANCELED, null);
        	}
        	if (view.getId() == R.id.ok_button) {
        		setResult(RESULT_OK, null);
        	}
        	finish();
        }
    }	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test01_secondary);

		totalClicks = (EditText)findViewById(R.id.global_counter_edit_text);
		//System.out.println("1111");
		Intent intent = getIntent();
		if (intent != null && intent.getExtras().containsKey("totalClicks")) {
			int numberOfClicks = intent.getIntExtra("totalClicks", -1);
			totalClicks.setText(String.valueOf(numberOfClicks));
		}
		//System.out.println("2222222");
		okButton = (Button)findViewById(R.id.ok_button);
		okButton.setOnClickListener(btnListener);
		cancelButton = (Button)findViewById(R.id.cancel_button);
		
		cancelButton.setOnClickListener(btnListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practical_test01_secondary, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
