package com.jff.engine3d.model;

import com.google.gson.Gson;

import java.io.*;

public class StorageHelper {

    public StorageHelper() {
    }

    public InputStream getInputStreamForFile(String path) throws IOException {
        File file = new File(path);

        InputStream is = new FileInputStream(file);

        return is;
    }

    public OutputStream getOutputStreamForFile(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
        OutputStream os = new FileOutputStream(file);

        return os;
    }

    public void saveScene(Scene scene, String path) throws IOException {

        OutputStream os = getOutputStreamForFile(path);

        Gson gson = new Gson();

        String json = gson.toJson(scene);

        OutputStreamWriter writer = new OutputStreamWriter(os);

        writer.write(json);

        writer.close();
        os.close();
    }

    public Scene loadScene(String path) throws IOException {


        InputStream is = getInputStreamForFile(path);

        Gson gson = new Gson();

        Reader reader = new InputStreamReader(is);
        Scene scene = gson.fromJson(reader, Scene.class);

        reader.close();
        is.close();

        return scene;
    }
}
