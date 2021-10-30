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
        Float co;
        Float no;
        Float no2;
        Float o3;
        Float so2;
        Float pm2_5;
        Float pm10;
        Float nh3;

        public Float getCo() {
            return co;
        }

        public void setCo(Float co) {
            this.co = co;
        }

        public Float getNo() {
            return no;
        }

        public void setNo(Float no) {
            this.no = no;
        }

        public Float getNo2() {
            return no2;
        }

        public void setNo2(Float no2) {
            this.no2 = no2;
        }

        public Float getO3() {
            return o3;
        }

        public void setO3(Float o3) {
            this.o3 = o3;
        }

        public Float getSo2() {
            return so2;
        }

        public void setSo2(Float so2) {
            this.so2 = so2;
        }

        public Float getPm2_5() {
            return pm2_5;
        }

        public void setPm2_5(Float pm2_5) {
            this.pm2_5 = pm2_5;
        }

        public Float getPm10() {
            return pm10;
        }

        public void setPm10(Float pm10) {
            this.pm10 = pm10;
        }

        public Float getNh3() {
            return nh3;
        }

        public void setNh3(Float nh3) {
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
