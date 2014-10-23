package Weather;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ForecastData {
	public String ResponseText;
	public String State;
	public String City;
	public String WeatherStationCity;
	public GeoData Geo = new GeoData();
	private List<Forecast> forecasts = new ArrayList<Forecast>();
	
	public void addForecast(Forecast forecast){
		this.forecasts.add(forecast);
	}
	
	public Iterator<Forecast> iterator(){
		return forecasts.iterator();
	}

}
