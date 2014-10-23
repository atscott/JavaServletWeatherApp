package Weather;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jdom2.JDOMException;

public class WeatherInfoFetcher {

	private WeatherXmlParser xmlConverter = new WeatherXmlParser();

	public ForecastData getForecastForZip(String zip) throws JDOMException,
			IOException, BadZipCodeException {
		ForecastData data = new ForecastData();
		String geoXml = getGeoXmlFromWeb(zip);
		data.Geo = xmlConverter.getRequestURL(geoXml);
		String forecastXML = getWeatherXmlFromWeb(data);
		xmlConverter.addXmlToForecastData(forecastXML, data);
		return data;
	}

	private String getWeatherXmlFromWeb(ForecastData data)
			throws ClientProtocolException, IOException, JDOMException {
		HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpGet httpget = new HttpGet(
					"http://api.wunderground.com/api/34dbe96b2fb1649a/forecast10day/q/"
							+ data.Geo.RequestURL + ".xml");
			return httpclient.execute(httpget, new BasicResponseHandler());
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
	}

	private String getGeoXmlFromWeb(String zip) throws ClientProtocolException,
			IOException {
		HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpGet httpget = new HttpGet(
					"http://api.wunderground.com/api/34dbe96b2fb1649a/geolookup/q/"
							+ zip + ".xml");
			String result = httpclient.execute(httpget,
					new BasicResponseHandler());
			return result;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
	}

	

}
