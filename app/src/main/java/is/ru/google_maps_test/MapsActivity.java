package is.ru.google_maps_test;

import androidx.fragment.app.FragmentActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ProgressDialog pd;
    Map<String, String> mMarkerMap = new HashMap<>();
    public ArrayList<HashMap<String, String>> resultsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
<<<<<<< HEAD
        resultsList = new ArrayList<>();

        //new JsonTask().execute("https://icelandnow.cdn.prismic.io/api/v2/documents/search?ref=X5BrfxAAACIAGIHl&pageSize=100#format=json");
        try {
            JSONObject jsonObj = new JSONObject(loadJSONFromAsset());
            JSONArray result_array = jsonObj.getJSONArray("results");
            ArrayList<HashMap<String, String>> camera_feed_results = new ArrayList<>();
            for(int i=0; i < result_array.length(); i++){
                JSONObject results_filtered = result_array.getJSONObject(i);
                JSONObject data_filtered = results_filtered.getJSONObject("data");
                String data_name = data_filtered.getString("name");
                String data_url = data_filtered.getString("url");
                String data_category = data_filtered.getString("category");
                String data_provider = data_filtered.getString("provider");
                String data_photovideo = data_filtered.getString("photovideo");
                String data_lat = data_filtered.getString("lat");
                String data_long = data_filtered.getString("long");

                //txtJson.setText(data_name);
                // tmp hash map for single camera feed
                HashMap<String, String> camera_feed = new HashMap<>();
                // add each child node to Hashmap key => value
                camera_feed.put("data_name", data_name);
                camera_feed.put("data_url", data_url);
                camera_feed.put("data_lat", data_lat);
                camera_feed.put("data_long", data_long);
                resultsList.add(camera_feed);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
=======
        // Uncomment code below when working with JSON API
        //new JsonTask().execute("https://icelandnow.cdn.prismic.io/api/v2/documents/search?ref=X5BrfxAAACIAGIHl&pageSize=100#format=json");
>>>>>>> upstream/master

        //get data from json file
        String file_data = loadJSONFromAsset();
        resultsList = getJsonData(file_data, resultsList);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        getMapData();

    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("iceland-now-json.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    public ArrayList<HashMap<String, String>> getJsonData(String file_data, ArrayList<HashMap<String, String>> results_list) {
        try {
            JSONObject jsonObj = new JSONObject(file_data);
            JSONArray result_array = jsonObj.getJSONArray("results");
            ArrayList<HashMap<String, String>> camera_feed_results = new ArrayList<>();
            for(int i=0; i < result_array.length(); i++){
                JSONObject results_filtered = result_array.getJSONObject(i);
                JSONObject data_filtered = results_filtered.getJSONObject("data");
                String data_name = data_filtered.getString("name");
                String data_url = data_filtered.getString("url");
                String data_category = data_filtered.getString("category");
                String data_provider = data_filtered.getString("provider");
                String data_photovideo = data_filtered.getString("photovideo");
                String data_lat = data_filtered.getString("lat");
                String data_long = data_filtered.getString("long");

                //txtJson.setText(data_name);
                // tmp hash map for single camera feed
                HashMap<String, String> camera_feed = new HashMap<>();
                // add each child node to Hashmap key => value
                camera_feed.put("data_name", data_name);
                camera_feed.put("data_url", data_url);
                camera_feed.put("data_lat", data_lat);
                camera_feed.put("data_long", data_long);
                results_list.add(camera_feed);
            }
            return results_list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getMapData() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

<<<<<<< HEAD
    public void updateResponseList(ArrayList<HashMap<String, String>> arr) {
        resultsList = arr;
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("iceland-now-json.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

=======
>>>>>>> upstream/master
    public class JsonTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(MapsActivity.this);
            pd.setMessage("Please wait");
            pd.setCancelable(false);
            pd.show();
        }

        protected String doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();


                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");
                }

                try {
                    JSONObject jsonObj = new JSONObject(String.valueOf(buffer));
                    JSONArray result_array = jsonObj.getJSONArray("results");
                    ArrayList<HashMap<String, String>> camera_feed_results = new ArrayList<>();
                    for(int i=0; i < result_array.length(); i++){
                        JSONObject results_filtered = result_array.getJSONObject(i);
                        JSONObject data_filtered = results_filtered.getJSONObject("data");
                        String data_name = data_filtered.getString("name");
                        String data_url = data_filtered.getString("url");
                        String data_category = data_filtered.getString("category");
                        String data_provider = data_filtered.getString("provider");
                        String data_photovideo = data_filtered.getString("photovideo");
                        String data_lat = data_filtered.getString("lat");
                        String data_long = data_filtered.getString("long");

                        //txtJson.setText(data_name);
                        // tmp hash map for single camera feed
                        HashMap<String, String> camera_feed = new HashMap<>();
                        // add each child node to Hashmap key => value
                        camera_feed.put("data_name", data_name);
                        camera_feed.put("data_url", data_url);
                        camera_feed.put("data_lat", data_lat);
                        camera_feed.put("data_long", data_long);
                        camera_feed_results.add(camera_feed);
                    }
<<<<<<< HEAD
                    updateResponseList(camera_feed_results);
                    //Intent intent  = new Intent(getApplicationContext(), MapsActivity.class);
                    //intent.putExtra("message", resultsList);
                    //startActivity(intent);
=======
>>>>>>> upstream/master

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                return buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pd.isShowing()){
                pd.dismiss();
            }
        }

    }

    public HashMap<String, String> getDataPoint(int i,   ArrayList<HashMap<String, String>> array) {
        HashMap<String, String> camera_feed = array.get(i);
        return camera_feed;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     * @return
     */

    public int sizeOfResultsList() {
        return resultsList.size();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
<<<<<<< HEAD
        Log.d("resultslist size: ", String.valueOf(resultsList.size()));
=======
>>>>>>> upstream/master

        // Add a marker in Sydney and move the camera
        //LatLng isafjordur = new LatLng(66.07475, -23.13498);
        //mMap.addMarker(new MarkerOptions().position(isafjordur).title("isafjordur"));
        //LatLng akureyri = new LatLng(65.6833306, -18.0999996);
        //mMap.addMarker(new MarkerOptions().position(akureyri).title("Akureyri"));
<<<<<<< HEAD

        for(int i=0; i < resultsList.size(); i++){
            HashMap<String, String> camera_feed = resultsList.get(i);
            String data_name = camera_feed.get("data_name");
            String data_url = camera_feed.get("data_url");
            String data_lat = camera_feed.get("data_lat");
            String data_long = camera_feed.get("data_long");

            double i_lat = Double.valueOf(data_lat);
            double i_long = Double.valueOf(data_long);
            //Log.d("1results: ", String.valueOf(data_name));
            LatLng i_position = new LatLng(i_lat, i_long);
            mMap.addMarker(new MarkerOptions().position(i_position).title(data_name));

        }
        LatLng iceland = new LatLng(64.9312762, -19.0211697);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(iceland));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                //String placeID = mMarkerMap.get(marker.getId());
                //String placeName = marker.getTitle();
                Intent intent = new Intent(getApplicationContext(), WebView_camera.class);
                //intent.putExtra(PLACE_NAME, placeName);
                //intent.putExtra(PLACE_ID, placeID);
                startActivity(intent);
                return false;
            }
        });
=======

        for(int i=0; i < resultsList.size(); i++){
            HashMap<String, String> camera_feed = getDataPoint(i, resultsList);
            String data_name = camera_feed.get("data_name");
            String data_url = camera_feed.get("data_url");
            String data_lat = camera_feed.get("data_lat");
            String data_long = camera_feed.get("data_long");

            double i_lat = Double.valueOf(data_lat);
            double i_long = Double.valueOf(data_long);

            LatLng i_position = new LatLng(i_lat, i_long);
            mMap.addMarker(new MarkerOptions().position(i_position).title(data_name));

            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    //String placeID = mMarkerMap.get(marker.getId());
                    //String placeName = marker.getTitle();
                    Intent intent = new Intent(getApplicationContext(), WebView_camera.class);
                    intent.putExtra("data_url", data_url);
                    //intent.putExtra(PLACE_ID, placeID);
                    startActivity(intent);
                    return false;
                }
            });

        }
        LatLng iceland = new LatLng(64.9312762, -19.0211697);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(iceland));


>>>>>>> upstream/master

    } //END OF  public void onMapReady(GoogleMap googleMap)
}