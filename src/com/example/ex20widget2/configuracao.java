package com.example.ex20widget2;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class configuracao extends Activity{
	private int mAppWidgetId;
	Context context;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setResult(RESULT_CANCELED);
		setContentView(R.layout.config);
		
		final DatePicker dataUser=(DatePicker) findViewById(R.id.datePicker1);
		dataUser.init(1980, 0, 1, null);
		
		Intent intent= getIntent();
		Bundle extras = intent.getExtras();
		if(extras != null){
			mAppWidgetId=extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
		}
		if(mAppWidgetId==AppWidgetManager.INVALID_APPWIDGET_ID){
			finish();
		}
		
		Button botao1=(Button)findViewById(R.id.button1);
		botao1.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				int year =dataUser.getYear();
				int month=dataUser.getMonth();
				int day=dataUser.getDayOfMonth();
				final Context context=configuracao.this;
				
				SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(context);
				SharedPreferences.Editor editor=preferences.edit();
				editor.putInt("ano", year);
				editor.putInt("mes", month);
				editor.putInt("dia", day);
				editor.commit();
				
				AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
				MainActivity.onUpdate(context ,appWidgetManager ,mAppWidgetId);
				Intent resultValue =new Intent();
				resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
				setResult(RESULT_OK,resultValue);
				finish();
				
			}
		});
	}

}
