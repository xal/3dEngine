package com.jff.engine3d.model.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import com.jff.engine3d.model.objec.primitives.TrafficLight;
import com.jff.engine3d.model.object.params.AbstractObjectParams;
import com.jff.engine3d.model.object.params.BicycleParams;
import com.jff.engine3d.model.object.params.TrafficLightParams;
import com.jff.engine3d.model.primitives.*;
import com.jff.engine3d.model.scene.Scene;

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


        Gson gson = createGson();


        String json = gson.toJson(scene);

        OutputStreamWriter writer = new OutputStreamWriter(os);

        writer.write(json);

        writer.close();
        os.close();
    }

    public Scene loadScene(String path) throws IOException {


        InputStream is = getInputStreamForFile(path);

        Gson gson = createGson();

        Reader reader = new InputStreamReader(is);
        Scene scene = gson.fromJson(reader, Scene.class);

        reader.close();
        is.close();

        return scene;
    }

    private Gson createGson() {
        Gson gson;

        RuntimeTypeAdapterFactory<AbstractObject> rta1;
        RuntimeTypeAdapterFactory<AbstractObjectParams> rta2;

        rta1 = RuntimeTypeAdapterFactory.of(
                AbstractObject.class)
                .registerSubtype(Box.class)
                .registerSubtype(Cylinder.class)

                .registerSubtype(FrustumCone.class)


                .registerSubtype(TrafficLight.class)
                .registerSubtype(Tor.class);
        rta2 = RuntimeTypeAdapterFactory.of(
                AbstractObjectParams.class)
                .registerSubtype(TrafficLightParams.class)
                .registerSubtype(BicycleParams.class);


        gson = new GsonBuilder()
                .registerTypeAdapterFactory(rta1)
                .registerTypeAdapterFactory(rta2)
                .setPrettyPrinting().
                        create();


        return gson;
    }
}
