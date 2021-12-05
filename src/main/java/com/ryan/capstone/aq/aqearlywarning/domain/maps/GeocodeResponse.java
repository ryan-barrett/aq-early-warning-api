package com.ryan.capstone.aq.aqearlywarning.domain.maps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
        Geometry geometry;

        public GeocodeResult() {
        }

        public GeocodeResult(String formattedAddress, Geometry geometry) {
            this.formattedAddress = formattedAddress;
            this.geometry = geometry;
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

        public Geometry getGeometry() {
            return geometry;
        }

        public void setGeometry(Geometry geometry) {
            this.geometry = geometry;
        }

        @Override
        public String toString() {
            return "GeocodeResult{" +
                    "formattedAddress='" + formattedAddress + '\'' +
                    '}';
        }

        static class Geometry {
            Map<String, Double> location = new HashMap<>();

            public Geometry() {
            }

            public Geometry(Map<String, Double> location) {
                this.location = location;
            }

            public Map<String, Double> getLocation() {
                return location;
            }

            public void setLocation(Map<String, Double> location) {
                this.location = location;
            }

            @Override
            public String toString() {
                return "Geometry{" +
                        "location=" + location +
                        '}';
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Geometry geometry = (Geometry) o;
                return Objects.equals(location, geometry.location);
            }

            @Override
            public int hashCode() {
                return Objects.hash(location);
            }
        }
    }
}
