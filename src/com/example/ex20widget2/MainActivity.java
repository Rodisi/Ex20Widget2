package com.example.ex20widget2;

import java.util.Calendar;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;
import android.widget.Toast;


public class MainActivity extends AppWidgetProvider {
	
	public static void onUpdate(Context context, AppWidgetManager AppWidgetManager, int appWidgetIds){
		final Calendar c=Calendar.getInstance();
		 int year=c.get(Calendar.YEAR);
		 int month=c.get(Calendar.MONTH);
		 int day=c.get(Calendar.DAY_OF_MONTH);
		
		SharedPreferences dataUser = PreferenceManager.getDefaultSharedPreferences(context);
		int anoUser=dataUser.getInt("ano", 0);
		int mesUser=dataUser.getInt("mes", 0)+1;
		int diaUser=dataUser.getInt("dia", 0);
		
		
		final Calendar aniversario =Calendar.getInstance();
		
		if(month>mesUser-1){
			year++;
		}
		if(month==mesUser-1 && day>diaUser ){
			year++;
		}
		aniversario.set(year,mesUser-1,diaUser);
		
		long todayMilis=c.getTimeInMillis();
		long aniversarioMilis=aniversario.getTimeInMillis();
		
		long diff=aniversarioMilis-todayMilis;
		long diffDays=diff/(24 * 60 * 60 *1000);
		
			
			RemoteViews Rviews =new RemoteViews(context.getPackageName(),R.layout.activity_main);
			Rviews.setTextViewText(R.id.textView2,""+diffDays+" dias para " +diaUser+"/"+mesUser+"/"+year);
			
			AppWidgetManager.updateAppWidget(appWidgetIds, Rviews);
			Toast.makeText(context,"widget added",Toast.LENGTH_SHORT).show();
			AppWidgetManager.updateAppWidget(appWidgetIds, Rviews);
	
	}
	
	
}
	


