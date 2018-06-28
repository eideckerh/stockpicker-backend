package de.stockpicker.backend.client.alphavantage.webservice.timeseries;

public enum Interval {
    Minute {
        @Override
        public String toString() {
            return "1min";
        }
    },
    FiveMinutes {
        @Override
        public String toString() {
            return "5min";
        }
    },
    FifteenMinutes {
        @Override
        public String toString() {
            return "15min";
        }
    }
}
