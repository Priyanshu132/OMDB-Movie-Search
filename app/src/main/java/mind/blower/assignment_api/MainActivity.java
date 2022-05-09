package mind.blower.assignment_api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private ImageView image,search;
    private EditText searchView;
    private ScrollView view;
    private TextView title,actors,runtime,rating,year,genre,plot,language;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        setId();



        search.setOnClickListener(View->{
            String data = searchView.getText().toString();
            if(!data.equals("") && !data.equals(null)) {
                String url = "https://www.omdbapi.com/?apikey=535a47c4&t=" + data;

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {

                    try {

                        title.setText(response.getString("Title"));
                        actors.setText(response.getString("Actors"));
                        runtime.setText(response.getString("Runtime"));
                        rating.setText(response.getString("imdbRating"));
                        year.setText(response.getString("Year"));
                        genre.setText(response.getString("Genre"));
                        plot.setText(response.getString("Plot"));
                        language.setText(response.getString("Language"));
                        Picasso.get().load(response.getString("Poster")).fit().into(image);
                        view.setVisibility(android.view.View.VISIBLE);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }, error -> {
                    System.out.println("error  " + error.getMessage());
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                });

                requestQueue.add(jsonObjectRequest);
            }
            else {
                Toast.makeText(getApplicationContext(), "Enter Title to search", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setId() {
        view  =findViewById(R.id.view);
        image = findViewById(R.id.image);
        title = findViewById(R.id.title);
        actors = findViewById(R.id.actors);
        runtime = findViewById(R.id.runtime);
        plot = findViewById(R.id.plot);
        genre = findViewById(R.id.genre);
        year = findViewById(R.id.year);
        rating = findViewById(R.id.rating);
        language = findViewById(R.id.language);
        search = findViewById(R.id.button);
        searchView = findViewById(R.id.search_view);

        requestQueue = Volley.newRequestQueue(this);
    }
}