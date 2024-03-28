package com.zsgs.interviewpanelapp.datalayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;

import com.google.gson.Gson;

public class Deserialization {
	private Gson gson = new Gson();

	public <T> T deserializeData(String DATA_FOLDER_PATH, String fileName, Type type) {
		String completeFilePath = DATA_FOLDER_PATH + File.separator + fileName;
		try (Reader reader = new FileReader(completeFilePath)) {
			return gson.fromJson(reader, type);
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			throw new RuntimeException("Error while deserializing data: " + e.getMessage(), e);
		}
	}
}