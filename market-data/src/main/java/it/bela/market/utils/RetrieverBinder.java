package it.bela.market.utils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.bela.market.annotation.FieldSelector;
import it.bela.market.annotation.PageUrl;
import it.bela.market.service.HttpService;

@Component
public class RetrieverBinder<T> {

	@Autowired
	private HttpService httpService;

	public RetrieverBinder() {
		super();
	}

	public  T parse(Class<T> clazz, Parser parser, String searchToken) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParseException, IOException {

		T instance = null;

		Annotation annotation = clazz.getAnnotation(PageUrl.class);
		if(annotation instanceof PageUrl){
			PageUrl pageUrl = (PageUrl) annotation;

			List<Element> elements = getElements(pageUrl.value() + searchToken, pageUrl.urlToRetrieveSelectors(), parser);

			instance = parse(elements, clazz);
		}

		return instance;
	}

	public T parse(List<Element> elements, Class<T> clazz)
			throws IllegalAccessException, InvocationTargetException, ParseException, InstantiationException {

		T instance = clazz.newInstance();

		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {

			Annotation annotation = method.getAnnotation(FieldSelector.class);
			if(annotation instanceof FieldSelector){
				FieldSelector fieldSelector = (FieldSelector) annotation;

				String value = "";
				if (!fieldSelector.attribute().equals("")){
					value = elements.get(fieldSelector.elementIndex()).select(fieldSelector.value()).attr(fieldSelector.attribute());
				} else {
					value = elements.get(fieldSelector.elementIndex()).select(fieldSelector.value()).text();
				}

				String pattern = fieldSelector.pattern();

				// Create a Pattern object
				Pattern r = Pattern.compile(pattern);

				// Now create matcher object.
				Matcher m = r.matcher(value);
				if (m.find( )) {
					value = m.group(fieldSelector.groupIndex());
				}

				if (fieldSelector.javaType().equals("String")){
					method.invoke(instance, value);
				} else if (fieldSelector.javaType().equals("int")){
					NumberFormat format = NumberFormat.getInstance(Locale.ITALY);
					Number number = format.parse(value);
					method.invoke(instance, number.intValue());
				} else if (fieldSelector.javaType().equals("double")){
					NumberFormat format = NumberFormat.getInstance(Locale.ITALY);
					Number number = format.parse(value);
					method.invoke(instance, number.doubleValue());
				} 				

			}
		}

		return instance;
	}

	private List<Element> getElements(String baseUrl, String[] urlToRetrieveSelectors, Parser parser) throws IOException {
		List<Element> elements = new ArrayList<Element>();

		Document currentDocument = getDocument(baseUrl, parser);
		elements.add(currentDocument);

		if (urlToRetrieveSelectors != null)
			for (int i = 0; i < urlToRetrieveSelectors.length; i++) {
				String urlToRetrieve = currentDocument.select(urlToRetrieveSelectors[i]).get(0).absUrl("href");
				currentDocument = getDocument(urlToRetrieve, parser);
				elements.add(currentDocument);
			}

		return elements;
	}

	private Document getDocument(String baseUrl, Parser parser) throws IOException {
		String documentString = httpService.get(baseUrl);
		Document currentDocument = Jsoup.parse(documentString, baseUrl, parser);
		return currentDocument;
	}

}
