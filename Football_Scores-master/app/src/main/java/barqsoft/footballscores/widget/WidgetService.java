package barqsoft.footballscores.widget;

import android.annotation.SuppressLint;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.widget.RemoteViewsService;

import java.text.SimpleDateFormat;
import java.util.Date;

import barqsoft.footballscores.DatabaseContract;
import barqsoft.footballscores.R;
import barqsoft.footballscores.service.myFetchService;

@SuppressLint("NewApi")
public class WidgetService extends RemoteViewsService implements Loader.OnLoadCompleteListener<Cursor>{
    WidgetDataProvider dataProvider;
    CursorLoader mCursorLoader;


    @Override
    public void onCreate() {
        startService(new Intent(this, myFetchService.class));
    }

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        dataProvider = new WidgetDataProvider(
                getApplicationContext(), intent);

        String[] fragmentdate1 = new String[1];
        Date fragmentdate = new Date(System.currentTimeMillis());
        SimpleDateFormat mformat = new SimpleDateFormat("yyyy-MM-dd");
        fragmentdate1[0] = mformat.format(fragmentdate);
        mCursorLoader = new CursorLoader(getApplicationContext(), DatabaseContract.scores_table.buildScoreWithDate(),
                null,null,fragmentdate1,null);
        mCursorLoader.registerListener(0, this);
        mCursorLoader.startLoading();
        return dataProvider;
    }

    @Override
    public void onLoadComplete(Loader<Cursor> loader, Cursor data) {
        dataProvider.entities.clear();
        data.moveToFirst();
        while(!data.isAfterLast()){
            dataProvider.entities.add(new WidgetEntities(data));
            data.moveToNext();
        }
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
        int appWidgetIds[] = appWidgetManager.getAppWidgetIds(
                new ComponentName(getApplication(), WidgetProvider.class));
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widgetCollectionList);
    }
}
