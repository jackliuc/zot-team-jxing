package com.zot.web.rs.provider;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class GsonProvider implements MessageBodyWriter<Object> {
	private final Gson gson;
	private final static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss:Z";

	public GsonProvider() {
		gson = new GsonBuilder().registerTypeHierarchyAdapter(Date.class, new JsonSerializer<Date>() {
			public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
				SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_FORMAT, Locale.ENGLISH);
				return new JsonPrimitive(format.format(src));
			}
		}).setDateFormat(DEFAULT_DATE_FORMAT).create();
	}

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public long getSize(Object obj, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public void writeTo(Object obj, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {
		entityStream.write(gson.toJson(obj, type).getBytes("UTF-8"));

	}

}