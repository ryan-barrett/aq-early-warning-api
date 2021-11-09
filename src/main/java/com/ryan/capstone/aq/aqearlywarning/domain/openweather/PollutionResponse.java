package com.ryan.capstone.aq.aqearlywarning.domain.openweather;

import java.util.List;

public class PollutionResponse {
    Coord coord;
    List<PollutionListItem> list;

    public double getLongitude() {
        return coord.getLon();
    }

    public double getLatitude() {
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
        double lon;
        double lat;

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
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
