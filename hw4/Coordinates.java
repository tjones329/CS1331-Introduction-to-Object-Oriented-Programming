/**
* @author tjones329
* @version 0.1.0
*/
public class Coordinates {
    private final double latitude;
    private final double longitude;
    /**
    * @param latitude a double representing a latitude
    * @param longitude a double representing a longitude
    */
    public Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    /**
    * @param other an object to check equals with
    * @return boolean representing if other is equal to an object
    */
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Coordinates)) {
            return false;
        }
        Coordinates c = (Coordinates) other;
        if (c.latitude == this.latitude && c.longitude == this.latitude) {
            return true;
        }
        return false;
    }
    /**
    * @return a string representation of the longitude and latitude of an object
    */
    public String toString() {
        String s = "latitude: " + this.latitude + ", "
                  + "longitude: " + this.longitude;
        return s;
    }
    /**
    * @return a double that is the latitude of an object
    */
    public double getLatitude() {
        return this.latitude;
    }
    /**
    * @return a double that is the longitude of the object
    */
    public double getLongitude() {
        return this.longitude;
    }
}
