package cz.ivosahlik.zomatoapi.enumeration;

/**
 * Intellij Idea
 * Created by ivosahlik on 02/04/2018
 */
public enum GeoCodeEnum {

    PRAHA("50.070008", "14.403369"),
    BRNO("49.189557", "16.600009"),
    BEROUN("49.9573", "13.9841"),
    PLZEN("49.7384", "13.3736"),
    OSTRAVA("49.836580", "18.291952");


    private String latitude;
    private String longtitude;


    GeoCodeEnum(String lat, String lon) {
        this.latitude = lat;
        this.longtitude = lon;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

}
