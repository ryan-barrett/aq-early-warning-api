package com.ryan.capstone.aq.aqearlywarning.domain.openweather;

import java.util.List;

public class PollutionResponse {
    Coord coord;
    List<PollutionListItem> list;

    public Double getLongitude() {
        return coord.getLon();
    }

    public Double getLatitude() {
        return coord.getLat();
    }

    public List<PollutionListItem> getList() {
        return list;
    }

    public void setList(List<PollutionListItem> list) {
        this.list = list;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    @Override
    public String toString() {
        return "PollutionResponse{" +
                "coord=" + coord +
                ", list=" + list +
                '}';
    }

    static class Coord {
        Double lon;
        Double lat;

        public Double getLon() {
            return lon;
        }

        public void setLon(Double lon) {
            this.lon = lon;
        }

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        @Override
        public String toString() {
            return "Coord{" +
                    "lon=" + lon +
                    ", lat=" + lat +
                    '}';
        }
    }
}
