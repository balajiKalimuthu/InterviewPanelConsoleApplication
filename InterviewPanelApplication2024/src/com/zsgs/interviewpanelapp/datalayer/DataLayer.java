package com.zsgs.interviewpanelapp.datalayer;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

public class DataLayer {
	private Serialization serialization;
	private Deserialization deserialization;
	private final String DATA_FOLDER_PATH = System.getProperty("user.dir") + File.separator + "InterviewDataBase";

	public DataLayer() {
		serialization = new Serialization();
		deserialization = new Deserialization();
	}

	public <T> void serializeData(T object, String fileName) {
		serialization.serializeData(object, DATA_FOLDER_PATH, fileName);
	}

	public <T> T deserializeData(String fileName, Type type, Class<T> objects) {
		T deserializedData = deserialization.deserializeData(DATA_FOLDER_PATH, fileName, type);
		if (deserializedData == null) {
			deserializedData = createInstance(objects);
		}
		return deserializedData;
	}

	private <T> T createInstance(Class<T> objects) {
		try {
			Constructor<T> constructor = objects.getDeclaredConstructor();
			return constructor.newInstance();
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}