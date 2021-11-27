package com.ryan.capstone.aq.aqearlywarning.domain.maps;

import java.util.List;

public class GeocodeResponse {
    List<GeocodeResult> results;

    public GeocodeResponse() {
    }

    public GeocodeResponse(List<GeocodeResult> results) {
        this.results = results;
    }

    public List<GeocodeResult> getResults() {
        return results;
    }

    public void setResults(List<GeocodeResult> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "GeocodeResponse{" +
                "results=" + results +
                '}';
    }

    static class GeocodeResult {
        String formattedAddress;

        public GeocodeResult() {
        }

        public GeocodeResult(String formattedAddress) {
            this.formattedAddress = formattedAddress;
        }

        public String getFormattedAddress() {
            return formattedAddress;
        }

        public void setFormattedAddress(String formattedAddress) {
            this.formattedAddress = formattedAddress;
        }

        public void setformatted_address(String formattedAddress) {
            this.formattedAddress = formattedAddress;
        }

        @Override
        public String toString() {
            return "GeocodeResult{" +
                    "formattedAddress='" + formattedAddress + '\'' +
                    '}';
        }
    }
}
