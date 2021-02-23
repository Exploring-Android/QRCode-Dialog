package com.wtrwx.qrdialog;
 
import android.app.Activity;
import android.os.Bundle;

import com.wtrwx.qrdialog.QRDialog;
import android.widget.Button;
import android.view.View;

public class MainActivity extends Activity { 
     
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		Button btn_showdialog =findViewById(R.id.btn_showdialog);
		btn_showdialog.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View view) {
					QRDialog.show(MainActivity.this,"https://www.baidu.com");
				}
			});
        
    }
	
} 
