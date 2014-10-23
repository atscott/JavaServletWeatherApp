package Weather;

import java.io.IOException;
import java.io.Reader;

import java.io.StringReader;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;


class WeatherXmlParser {

	public GeoData getRequestURL(String geoXml) throws JDOMException,
			IOException, BadZipCodeException {
		GeoData data = new GeoData();
		Element root = getXmlRoot(geoXml);

		Element location = root.getChild("location");
		if(location == null)
			throw new BadZipCodeException();
		data.RequestURL = location.getChildText("requesturl");
		data.State = location.getChildText("state");
		data.City = location.getChildText("city");
		data.Latitude = location.getChildText("lat");
		data.Longitude = location.getChildText("lon");

		return data;
	}

	public void addXmlToForecastData(String forecastXml, ForecastData data)
			throws JDOMException, IOException {
		Element root = getXmlRoot(forecastXml);
		List<Element> forecasts = root.getChild("forecast")
				.getChild("simpleforecast").getChild("forecastdays")
				.getChildren();
		for (Element forecastday : forecasts) {
			Forecast forecastItem = new Forecast();
			forecastItem.Date = forecastday.getChild("date").getChildText(
					"pretty");
			forecastItem.High = forecastday.getChild("high").getChildText(
					"fahrenheit");
			forecastItem.Low = forecastday.getChild("low").getChildText(
					"fahrenheit");
			forecastItem.Description = forecastday.getChildText("conditions");
			forecastItem.PictureURL = forecastday.getChildText("icon_url");
			forecastItem.ChanceOfPrecip = forecastday.getChildText("pop");
			forecastItem.PrecipitationInches = forecastday.getChild(
					"qpf_allday").getChildText("in");
			data.addForecast(forecastItem);
		}

	}

	private Element getXmlRoot(String xml) throws JDOMException, IOException {
		SAXBuilder builder = new SAXBuilder();
		Reader in = new StringReader(xml);
		Document doc = builder.build(in);
		return doc.getRootElement();
	}
	
	

}
