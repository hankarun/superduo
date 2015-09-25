package barqsoft.footballscores.widget;


import android.database.Cursor;

public class WidgetEntities {
    String homeName;
    String avayName;
    String homeScore;
    String avayScore;

    public WidgetEntities(Cursor c){
        homeName = c.getString(3);
        avayName = c.getString(4);

        avayScore = c.getInt(6) == -1 ? "-" : c.getInt(6)+"";
        homeScore = c.getInt(7) == -1 ? "-" : c.getInt(7)+"";
    }

    String getHomeName(){ return homeName;}
    String getAvayName(){ return avayName;}
    String getHomeScore(){ return homeScore;}
    String getAvayScore(){ return avayScore;}
}
