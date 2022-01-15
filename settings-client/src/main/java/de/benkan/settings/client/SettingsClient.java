package de.benkan.settings.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.benkan.data.models.settings.UiSettings;
import okhttp3.*;

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

    public Optional<UiSettings> postSettingsSync(UiSettings data) {
        String json;
        try {
            json = objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            return Optional.empty();
        }

        RequestBody body = RequestBody.create(
                json, MediaType.parse(javax.ws.rs.core.MediaType.APPLICATION_JSON));

        Request request = new Request.Builder()
                .url(baseUrl + "/settings")
                .post(body)
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
