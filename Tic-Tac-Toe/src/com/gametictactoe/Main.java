package com.gametictactoe;

import android.app.Activity;
import com.gametictactoe.R;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class Main extends Activity {
	
	
	// Dictates whether X goes first or not, based upon if xGoFirst is true or not.
	private RadioButton xFirst;
	private RadioButton vsComp;
	Boolean xGoFirst;	
	Boolean comp;
	

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
           
		// Load main screen
		setContentView(R.layout.main);		
	}
	
	public void onPlayClicked(View view)
	{
		// Define xFirst as the radio button with the id xFirst
		 xFirst = (RadioButton)findViewById(R.id.xFirst);
		
		 // If xFirst is checked X goes first (true), if not then O is checked.
		if( xFirst.isChecked()){
			xGoFirst = true;
		} else {
			xGoFirst = false;
		}
		
		vsComp = (RadioButton)findViewById(R.id.compOn);
				
				// see if they are playing against human or comp
		if( vsComp.isChecked()){
			comp = true;
			//X goes first if v comp
			xGoFirst = true;
		} else {
			comp = false;
		}
		
		// Load main screen, if xGoFirst is true then X is the first move.
	    setContentView(new Board(this, xGoFirst, comp));

	}
	
}
