package de.benkan.settings.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.benkan.data.models.settings.UiSettings;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Locale;
import java.util.Optional;

public class SettingsClient {

    public final String baseUrl;

    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    public SettingsClient(SettingsClientConfig clientConfig) {
        this.client = new OkHttpClient();
        this.objectMapper = new ObjectMapper();

        this.baseUrl = String.format(Locale.ROOT, "%s:%d%s",
                clientConfig.hostName(),
                clientConfig.port(),
                clientConfig.basePath());
    }

    public Optional<UiSettings> getSettingsSync() {
        Request request = new Request.Builder()
                .url(baseUrl + "/settings")
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return Optional.of(objectMapper.readValue(response.body().byteStream(), UiSettings.class));
            }
            return Optional.empty();
        } catch (IOException e) {
            return Optional.empty();
        }
    }
}
