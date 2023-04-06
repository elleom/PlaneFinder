package eu.oelleo.pf.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Aircraft {

    /**
     * response example
     * {
     * "id": 3,
     * "callsign": "SAL002",
     * "squawk": "sqwk",
     * "reg": "N54321",
     * "flightno": "SAL002",
     * "route": "route",
     * "type": "LJ",
     * "category": "ct",
     * "altitude": 40000,
     * "heading": 65,
     * "speed": 440,
     * "lat": 39.8412964,
     * "lon": -105.0048267,
     * "barometer": 0.0,
     * "vert_rate": 0,
     * "selected_altitude": 0,
     * "polar_distance": 0.0,
     * "polar_bearing": 0.0,
     * "is_adsb": false,
     * "is_on_ground": true,
     * "last_seen_time": "2023-04-06T19:45:02.741463800Z",
     * "pos_update_time": "2023-04-06T19:45:02.741463800Z",
     * "bds40_seen_time": "2023-04-06T19:45:02.741463800Z"
     * }
     */

    @Id
    private Long id;
    private String callsign, squawk, reg, flightno, route, type, category;
    private int altitude, heading, speed;
    @JsonProperty("vert_rate")
    private int vertRate;
    @JsonProperty("selected_altitude")
    private int selectedAltitude;
    private double lat, lon, barometer;
    @JsonProperty("polar_distance")
    private double polarDistance;
    @JsonProperty("polar_bearing")
    private double polarBeareing;
    @JsonProperty("is_adsb")
    private boolean isAdsb;
    @JsonProperty("is_on_ground")
    private boolean isOnGround;
    @JsonProperty("last_seen_time")
    private Instant lastSeenTime;
    @JsonProperty("pos_update_time")
    private Instant posUpdateTime;
    @JsonProperty("bds40_seen_time")
    private Instant bds40SeenTime;

    public String getLastSeenTime() {
        return lastSeenTime.toString();
    }

    public void setLastSeenTime(String lastSeenTime) {
        if (null != lastSeenTime) {
            this.lastSeenTime = Instant.parse(lastSeenTime);
        } else {
            this.lastSeenTime = Instant.ofEpochSecond(0);
        }
    }

    public String getPosUpdateTime() {
        return this.posUpdateTime.toString();
    }

    public void setPosUpdateTime(String posUpdateTime) {
        if (null != posUpdateTime) {
            this.posUpdateTime = Instant.parse(posUpdateTime);
        } else {
            this.posUpdateTime = Instant.ofEpochSecond(0);
        }
    }

    public String getBds40SeenTime() {
        return this.bds40SeenTime.toString();
    }

    public void setBds40SeenTime(String bds40SeenTime) {
        if (null != bds40SeenTime) {
            this.bds40SeenTime = Instant.parse(bds40SeenTime);
        } else {
            this.bds40SeenTime = Instant.ofEpochSecond(0);
        }
    }
}
