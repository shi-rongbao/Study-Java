package com.study.interclass;

public class Car {
    private String carName;
    private String carColor;
    private int carAge;

    public Car() {
    }

    public Car(String carName, String carColor, int carAge) {
        this.carName = carName;
        this.carColor = carColor;
        this.carAge = carAge;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public int getCarAge() {
        return carAge;
    }

    public void setCarAge(int carAge) {
        this.carAge = carAge;
    }

    class Engine {
        private String engineName;
        private int engineAge;

        public Engine() {
        }

        public Engine(String engineName, int engineAge) {
            this.engineName = engineName;
            this.engineAge = engineAge;
        }

        public String getEngineName() {
            return engineName;
        }

        public void setEngineName(String engineName) {
            this.engineName = engineName;
        }

        public int getEngineAge() {
            return engineAge;
        }

        public void setEngineAge(int engineAge) {
            this.engineAge = engineAge;
        }
    }
}
