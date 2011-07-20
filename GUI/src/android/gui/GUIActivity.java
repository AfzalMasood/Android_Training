package android.gui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.Toast;

public class GUIActivity extends Activity {
    /** Called when the activity is first created. */
    
	private CheckBox ch;
	private static int progress;
	private ProgressBar progressBar;
	private int progressStatus=0;
	private Handler handler = new Handler();
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        progress = 0;
        
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        
        //---do some work in background thread---
        
        new Thread(new Runnable()
        {
        	public void run()
        	{
        		//---do some work here---
        		while (progressStatus < 10)
        		{
        			progressStatus = doSomeWork();
        		}
        		//---hides the progress bar---
        		handler.post(new Runnable()
        		{
        			public void run()
        			{
        				//---0 - VISIBLE; 4 - INVISIBLE; 8 - GONE---
        				progressBar.setVisibility(8);
        			}
        		});
        	}
        	
      
        	//---do some long lasting work here---
        	private int doSomeWork()
        	{
        		try {
        				//---simulate doing some work---
        				Thread.sleep(500);
        			} catch (InterruptedException e)
        			{
        				e.printStackTrace();
        			}
        			
        		return ++progress;
        	}
        	
        }).start();
      
        
    }
    
    public void funOfImageBtn(View v)
    {
    	DisplayToast("u hv pressed the Image Button");
    }

    public void funOfCheckBox(View v)
    {
    	ch=(CheckBox) findViewById(R.id.checkBox1);
    	if(ch.isChecked())
    		DisplayToast("Check box is checked");
    	else
    		DisplayToast("Check box is unchecked");
    }
    
	private void DisplayToast(String string) 
	{
		Toast.makeText(getBaseContext(),string,Toast.LENGTH_SHORT).show();		
	}

	
    
}