package com.example.robert.widgetexample;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by robert on 11.7.2017.
 */

public class ExampleAppWidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;

        // Perform this loop procedure for each App Widget that belongs to this provider
        for(int i = 0; i < N; i++) {
            int appWidgetId = appWidgetIds[i];

            // Get the layout for the App Widget and attach an on-click listener
            // to the button
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            views.setTextViewText(R.id.textView_name, getRandomName());

            // Create an Intent to update Widget
            Intent intent = new Intent(context, ExampleAppWidgetProvider.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.button_name, pendingIntent);

            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }

    }

    private String getRandomName() {
        ArrayList<String> names = new ArrayList<>();
        names.add("John");
        names.add("Hary");
        names.add("Emma");
        names.add("William");

        Random random = new Random();
        int randomIndex = random.nextInt(names.size());

        return names.get(randomIndex);

    }
}
