package com.keithecker.theonesdk;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

abstract class TheOneAPIRequest {

    public final String THE_ONE_API_URL_BASE = "https://the-one-api.dev/v2/";

    private String BearerToken;

    // Pagination
    private Integer Limit;
    private Integer Page;
    private Integer Offset;

    // Filtering/Sorting
    private String additionalQueryParams;

    public String getBearerToken() {
        return BearerToken;
    }

    public void setBearerToken(String bearerToken) {
        this.BearerToken = bearerToken;
    }

    public Integer getmLimit() {
        return Limit;
    }

    public void setLimit(Integer limit) {
        this.Limit = limit;
    }

    public Integer getPage() {
        return Page;
    }

    public void setPage(Integer page) {
        this.Page = page;
    }

    public Integer getOffset() {
        return Offset;
    }

    public void setOffset(Integer offset) {
        this.Offset = offset;
    }

    public Integer getLimit() {
        return Limit;
    }

    public String getAdditionalQueryParams() {
        return additionalQueryParams;
    }

    public void setAdditionalQueryParams(String additionalQueryParams) {
        this.additionalQueryParams = additionalQueryParams;
    }

    /**
     * Generates a Java.net URL object from the given endpointRoute as well as
     * pagination/sorting/filtering information.
     * This URL will then be used by Child API classes to make the request.
     * 
     * @param endpointRoute The API route to be appended to the API base
     *                      URL(https://the-one-api.dev/v2/)
     * @return
     * @throws MalformedURLException
     * @throws UnsupportedEncodingException
     */
    public URL getURL(String endpointRoute) throws MalformedURLException, UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder(THE_ONE_API_URL_BASE);

        sb.append(endpointRoute);

        List<String> queryParams = new ArrayList<>();

        if (this.Limit != null) {
            queryParams.add("limit=" + this.Limit);
        }

        if (this.Page != null) {
            queryParams.add("page=" + this.Page);
        }

        if (this.Offset != null) {
            queryParams.add("offset=" + this.Offset);
        }

        if (getAdditionalQueryParams() != null &&
                !getAdditionalQueryParams().equals("")) {
            queryParams.add(getAdditionalQueryParams().replaceAll(" ", "%20"));
        }

        if (queryParams.size() > 0) {
            sb.append("?");
            for (String queryParam : queryParams) {
                sb.append(queryParam + ",");
            }
            sb.deleteCharAt(sb.lastIndexOf(","));
        }

        URL url = new URL(sb.toString());
        return url;
    }
}