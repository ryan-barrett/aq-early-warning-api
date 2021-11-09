package com.ryan.capstone.aq.aqearlywarning.domain.openweather;

public class PollutionListItem {
    Main main;
    Components components;
    long dt;

    public int getAqi() {
        return main.getAqi();
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Components getComponents() {
        return components;
    }

    public void setComponents(Components components) {
        this.components = components;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    @Override
    public String toString() {
        return "PollutionListItem{" +
                "main=" + main +
                ", components=" + components +
                ", dt=" + dt +
                '}';
    }

    public static class Components {
        float co;
        float no;
        float no2;
        float o3;
        float so2;
        float pm2_5;
        float pm10;
        float nh3;

        public float getCo() {
            return co;
        }

        public void setCo(float co) {
            this.co = co;
        }

        public float getNo() {
            return no;
        }

        public void setNo(float no) {
            this.no = no;
        }

        public float getNo2() {
            return no2;
        }

        public void setNo2(float no2) {
            this.no2 = no2;
        }

        public float getO3() {
            return o3;
        }

        public void setO3(float o3) {
            this.o3 = o3;
        }

        public float getSo2() {
            return so2;
        }

        public void setSo2(float so2) {
            this.so2 = so2;
        }

        public float getPm2_5() {
            return pm2_5;
        }

        public void setPm2_5(float pm2_5) {
            this.pm2_5 = pm2_5;
        }

        public float getPm10() {
            return pm10;
        }

        public void setPm10(float pm10) {
            this.pm10 = pm10;
        }

        public float getNh3() {
            return nh3;
        }

        public void setNh3(float nh3) {
            this.nh3 = nh3;
        }

        @Override
        public String toString() {
            return "Components{" +
                    "co=" + co +
                    ", no=" + no +
                    ", no2=" + no2 +
                    ", o3=" + o3 +
                    ", so2=" + so2 +
                    ", pm2_5=" + pm2_5 +
                    ", pm10=" + pm10 +
                    ", nh3=" + nh3 +
                    '}';
        }
    }

    static class Main {
        int aqi;

        public int getAqi() {
            return aqi;
        }

        public void setAqi(int aqi) {
            this.aqi = aqi;
        }

        @Override
        public String toString() {
            return "Main{" +
                    "aqi=" + aqi +
                    '}';
        }
    }
}
