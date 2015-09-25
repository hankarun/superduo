package barqsoft.footballscores.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;

import java.util.ArrayList;

import barqsoft.footballscores.R;

@SuppressLint("NewApi")
public class WidgetDataProvider implements RemoteViewsFactory{
    Context mContext = null;
    ArrayList<WidgetEntities> entities = new ArrayList<>();

    public WidgetDataProvider(Context context, Intent intent)
    {
        mContext = context;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return entities.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews mView = new RemoteViews(mContext.getPackageName(),
                R.layout.widget_item);

        mView.setTextViewText(R.id.homeNameView,entities.get(i).getHomeName());
        mView.setTextViewText(R.id.awayNameView,entities.get(i).getAvayName());
        mView.setTextViewText(R.id.homeScoreView,entities.get(i).getHomeScore());
        mView.setTextViewText(R.id.awayScoreView,entities.get(i).getAvayScore());

        return mView;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

}
