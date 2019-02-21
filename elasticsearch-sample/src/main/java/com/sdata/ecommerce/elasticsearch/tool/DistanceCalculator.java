package com.sdata.ecommerce.elasticsearch.tool;

/**
 * Created by yiyun on 17-6-6.
 * http://www.geodatasource.com/developers/java
 */
public class DistanceCalculator {

    public static double distance(double lat1, double lon1, double lat2, double lon2, Unit unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == Unit.KILOMETERS) {
            dist = dist * 1.609344;
        } else if (unit == Unit.NAUTICAL_MILE) {
            dist = dist * 0.8684;
        }

        return (dist);
    }

    public enum Unit {
        MILES, KILOMETERS, NAUTICAL_MILE,
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts decimal degrees to radians						 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts radians to decimal degrees						 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
