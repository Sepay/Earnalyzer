package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class CryptoValueFetcher {
    private static final String COIN_LIST_API = "https://api.coingecko.com/api/v3/coins/list";
    private static final String VALUE_API = "https://api.coingecko.com/api/v3/simple/price?ids=%s&vs_currencies=usd";

    public static double getCryptoValue(String abbreviation) throws IOException {
        String id = getCryptoIdByAbbreviation(abbreviation);
        if (id != null) {
            String apiUrl = String.format(VALUE_API, id);
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String response = reader.readLine();
                reader.close();

                // Parse the JSON response
                // Assuming the response is in the format {"id": {"usd": value}}
                JSONObject jsonObject = new JSONObject(response);
                JSONObject currencyObject = jsonObject.getJSONObject(id);
                double value = currencyObject.getDouble("usd");
                return value;
            } else {
                throw new IOException("Failed to fetch crypto value. Response code: " + responseCode);
            }
        } else {
            throw new IllegalArgumentException("Cryptocurrency abbreviation not found: " + abbreviation);
        }
    }

    private static String getCryptoIdByAbbreviation(String abbreviation) throws IOException {
        URL url = new URL(COIN_LIST_API);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String response = reader.readLine();
            reader.close();

            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject coinObject = jsonArray.getJSONObject(i);
                String symbol = coinObject.getString("symbol");
                if (abbreviation.equalsIgnoreCase(symbol)) {
                    return coinObject.getString("id");
                }
            }
        } else {
            throw new IOException("Failed to fetch cryptocurrency list. Response code: " + responseCode);
        }

        return null;
    }
}
