package com.ryan.capstone.aq.aqearlywarning.domain.openweather;

import java.util.List;

public class PollutionResponse {
    Coord coord;
    List<PollutionListItem> list;

    public Float getLongitude() {
        return coord.getLon();
    }

    public Float getLatitude() {
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
        Float lon;
        Float lat;

        public Float getLon() {
            return lon;
        }

        public void setLon(Float lon) {
            this.lon = lon;
        }

        public Float getLat() {
            return lat;
        }

        public void setLat(Float lat) {
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
