package ro.pub.cs.systems.eim.practicaltest01;

//import android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class PracticalTest01MainActivity extends Activity {

	private final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;
	
	
	private Button secondActivity = null;
	private Button secondButton = null;
	private Button firstButton = null;
	
    private EditText firstCounterEditText = null;
    private EditText secondCounterEditText = null;
    
    private ActivityButtonClickListener actButtonListener = new ActivityButtonClickListener();
    private FirstIncrementButtonClickListener firstIncrementButtonClickListener = new FirstIncrementButtonClickListener();    	
    private SecondIncrementButtonClickListener secondIncrementButtonClickListener = new SecondIncrementButtonClickListener(); 
	
    private class ActivityButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
        	int nrClicksLeft = Integer.parseInt(firstCounterEditText.getText().toString());
        	int nrClickRight = Integer.parseInt(secondCounterEditText.getText().toString());
        	
        	int totalClicks = nrClickRight + nrClicksLeft;
			Intent intent = new Intent(getApplicationContext(), PracticalTest01SecondaryActivity.class);
			intent.putExtra("totalClicks", totalClicks);
			startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);        	
        }
    }    
    
    private class FirstIncrementButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
        	int nrClicks = Integer.parseInt(firstCounterEditText.getText().toString());
        	nrClicks++;
        	firstCounterEditText.setText(String.valueOf(nrClicks));
        }
    }	
    
    private class SecondIncrementButtonClickListener implements View.OnClickListener {


        @Override
        public void onClick(View view) {
        	int nrClicks = Integer.parseInt(secondCounterEditText.getText().toString());
        	nrClicks++;
        	secondCounterEditText.setText(String.valueOf(nrClicks));
        }
    }	    
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test01_main);
		
		firstCounterEditText = (EditText) findViewById(R.id.left_edit_text);
		firstCounterEditText.setEnabled(false);
		firstCounterEditText.setText(String.valueOf(0));
		
		secondCounterEditText = (EditText) findViewById(R.id.right_edit_text);
		secondCounterEditText.setEnabled(false);
		secondCounterEditText.setText(String.valueOf(0));
		
		firstButton = (Button) findViewById(R.id.left_button);
		firstButton.setOnClickListener(firstIncrementButtonClickListener);
		
		secondButton = (Button) findViewById(R.id.right_button);
		secondButton.setOnClickListener(secondIncrementButtonClickListener);
		
		secondActivity = (Button)findViewById(R.id.navigate_to_secondary_activity_button);
		secondActivity.setOnClickListener(actButtonListener);
	}

	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
	    savedInstanceState.putString("leftCount", firstCounterEditText.getText().toString());
	    savedInstanceState.putString("rightCount", secondCounterEditText.getText().toString());
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

		if (savedInstanceState.containsKey("leftCount")) {
			firstCounterEditText.setText(savedInstanceState.getString("leftCount"));
		} else {
			firstCounterEditText.setText(String.valueOf(0));
		}
		
		if (savedInstanceState.containsKey("rightCount")) {
			secondCounterEditText.setText(savedInstanceState.getString("rightCount"));
		} else {
			secondCounterEditText.setText(String.valueOf(0));
		}		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == SECONDARY_ACTIVITY_REQUEST_CODE) {
			Toast.makeText(this, "Activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
		}
	}	

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practical_test01_main, menu);
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
