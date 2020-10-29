package is.ru.google_maps_test;
import android.util.Log;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import static org.junit.Assert.*;

public class MapsActivityTest {
    private Object EqualsBuilder;

    //Log.d("resultslist size: ", String.valueOf(resultsList.size()));

    @Test
    public void checkJsonPoints(){
        /// TESTING TO MAKE SURE THAT resultsList IS CHANGING WHEN WE CREATE A NEW POINT
        ArrayList<HashMap<String, String>> resultsList = new ArrayList<>();
        MapsActivity activity = new MapsActivity();

        HashMap<String, String> compare_true = new HashMap<>();
        compare_true.put("data_name", "Tindastóll");
        compare_true.put("data_url", "https://skidi.gunnartr.net/skitindastoll.jpg");
        compare_true.put("data_lat", "65.8");
        compare_true.put("data_long", "-19.7166667");
        resultsList.add(compare_true);

        HashMap<String, String> compare_false = new HashMap<>();
        compare_false.put("data_name", "Hvammstangi");
        compare_false.put("data_lat", "65.3999984");
        compare_false.put("data_long", "-20.9499962");

        HashMap<String, String> camera_feed = activity.getDataPoint(0, resultsList);

        assertEquals(compare_true, camera_feed);
        assertNotEquals(compare_false, camera_feed);
    }
}