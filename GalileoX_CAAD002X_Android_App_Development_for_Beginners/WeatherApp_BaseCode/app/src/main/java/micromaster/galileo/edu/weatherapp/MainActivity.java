package micromaster.galileo.edu.weatherapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.IOException;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import micromaster.galileo.edu.weatherapp.API.WeatherInterface;
import micromaster.galileo.edu.weatherapp.model.WeatherData;
import micromaster.galileo.edu.weatherapp.model.WeatherResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private final static String BASE_URL = "http://api.wunderground.com/api/";
    private final static String API_KEY = "a2e729415b773f90";

    @BindView(R.id.pressure)
    TextView pressure;
    @BindView(R.id.countryName)
    TextView countryName;
    @BindView(R.id.temperature)
    TextView temperature;
    @BindView(R.id.humidity)
    TextView humidity;
    @BindView(R.id.weather)
    TextView weather;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        AsyncWeatherTask asyncWeatherTask = new AsyncWeatherTask();
        asyncWeatherTask.execute();
    }

    class AsyncWeatherTask extends AsyncTask<Void, Void, WeatherResponse> {

        @Override
        protected void onPostExecute(WeatherResponse weatherResponse) {

            if (weatherResponse != null) {
                WeatherData data = weatherResponse.getWeatherData();
                pressure.setText(String.format(Locale.getDefault(), "%.2f", data.getPressure()));
                countryName.setText(data.getDisplayLocation().getCityName());
                temperature.setText(data.getTemp());
                humidity.setText(data.getHumidity());
                weather.setText(data.getWeather());
            }
        }

        @Override
        protected WeatherResponse doInBackground(Void... params) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            WeatherInterface weatherInterface = retrofit.create(WeatherInterface.class);
            Call<WeatherResponse> call = weatherInterface.getWeatherFromSanFrancisco(API_KEY);
            WeatherResponse weatherResponse = null;
            try {
                weatherResponse = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return weatherResponse;
        }
    }
}
