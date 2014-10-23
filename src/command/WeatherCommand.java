package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.jdom2.JDOMException;

import Weather.BadZipCodeException;
import Weather.ForecastData;
import Weather.WeatherInfoFetcher;

public class WeatherCommand implements Command {

	WeatherInfoFetcher fetcher = new WeatherInfoFetcher();

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws CommandException {
		String zip = request.getParameter("zip");
		if (zip != null) {
			try {
				ForecastData data = fetcher.getForecastForZip(zip);
				request.setAttribute("WeatherData", data);
			} catch (JDOMException e) {
				throw new WrappingCommandException(e);
			} catch (IOException e) {
				throw new WrappingCommandException(e);
			} catch (BadZipCodeException e) {
				request.setAttribute("error", "Could not get weather data for zip " + zip);
			}
		}

		return null;
	}

}
