package com.zsgs.interviewpanelapp.datalayer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.google.gson.Gson;

public class Serialization {
	private Gson gson = new Gson();

	public <T> void serializeData(T object, String DATA_FOLDER_PATH, String fileName) {
		createDataFolder(DATA_FOLDER_PATH);
		String completeFilePath = DATA_FOLDER_PATH + File.separator + fileName;
		try (Writer writer = new FileWriter(completeFilePath)) {
			gson.toJson(object, writer);
		} catch (IOException e) {
			throw new RuntimeException("Error while serializing data: " + e.getMessage(), e);
		}
	}

	private void createDataFolder(String DATA_FOLDER_PATH) {
		File dataFolder = new File(DATA_FOLDER_PATH);
		if (!dataFolder.exists() && !dataFolder.mkdir()) {
			throw new RuntimeException("Failed to create data folder: " + DATA_FOLDER_PATH);
		}
	}
}