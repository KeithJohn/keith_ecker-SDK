package com.keithecker.theonesdk;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BookAPI extends TheOneAPIRequest {

    /**
     * Gets All books from The One API based on the pagination, sorting, and
     * filtering options set.
     * 
     * @return
     * @throws Exception
     */
    public List<Book> getAllBooks() throws Exception {

        URL url = getURL("book");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        if (getBearerToken() != null && !getBearerToken().equals("")) {
            con.setRequestProperty("Authorization", "Bearer " + getBearerToken());
        }

        int responseCode = con.getResponseCode();
        if (responseCode != 200) {
            throw new Exception("Error has occured: " + responseCode + "-" + con.getResponseMessage());
        }

        String responseString = null;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            responseString = content.toString();
        }

        List<Book> books = parseBookResponse(responseString);
        return books;
    }

    /**
     * Parses the response JSON from The One API to a list of Book objects
     * 
     * @param responseString - The JSON response as a string
     * @return
     * @throws JsonMappingException
     * @throws JsonProcessingException
     */
    private List<Book> parseBookResponse(String responseString) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(responseString);
        JsonNode docsNode = rootNode.get("docs");
        List<Book> books = new ArrayList<>();
        for (JsonNode objNode : docsNode) {
            books.add(new Book(objNode.get("_id").textValue(), objNode.get("name").textValue()));
        }
        return books;
    }

}