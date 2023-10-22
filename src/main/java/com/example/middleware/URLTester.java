package com.example.middleware;

public class URLTester {

    private Boolean isValidUrl;
    private String routeType;

    public URLTester(Boolean isValidUrl, String routeType) {
        this.isValidUrl = isValidUrl;
        this.routeType = routeType;
    }

    public Boolean getValidUrl() {
        return isValidUrl;
    }

    public void setValidUrl(Boolean validUrl) {
        isValidUrl = validUrl;
    }

    public String getRouteType() {
        return routeType;
    }

    public void setRouteType(String routeType) {
        this.routeType = routeType;
    }
}
