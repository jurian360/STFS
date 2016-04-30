package sr.business.stfs.utils;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;

/**
 * Created by jurian360 on 4/30/2016.
 */
public class Dataholder {
    private static Dataholder ourInstance = new Dataholder();

    public static Dataholder getInstance() {
        return ourInstance;
    }

    private Dataholder() {
    }

    private OkHttpClient client;

    public OkHttpClient getClient() {
        if(client == null){
            this.client =  new OkHttpClient();
        }
        return client;
    }

    private NetworkClient networkClient;

    public NetworkClient getNetworkClient() {
        if(networkClient == null){
            this.networkClient = new NetworkClient();
        }
        return networkClient;
    }

    public void setNetworkClient(NetworkClient networkClient) {
        this.networkClient = networkClient;
    }

    public Gson gson;

    public Gson getGson() {
        if(gson == null){
            this.gson = new Gson();
        }
        return gson;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }
}
