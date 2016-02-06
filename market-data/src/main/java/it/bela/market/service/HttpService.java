package it.bela.market.service;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class HttpService {

	private static final String USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21";
	private static Log logger = LogFactory.getLog(HttpService.class);
	
	public String get(String url) throws IOException {

		try {
			//TODO: da verificare in caso di esecuzione da web
			TimeUnit.SECONDS.sleep(new Random().nextInt(60));
		} catch (InterruptedException e) {
			logger.error("Error sleeping...", e);
		}
		
		logger.info("Start retrieving url: " + url);
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);

		// add request header
		request.addHeader("User-Agent", USER_AGENT);
		HttpResponse response = client.execute(request);
		
		String responseString = EntityUtils.toString(response.getEntity());
		
		return responseString;
	}
}
