package model;

import java.util.ArrayList;

/** Holds information for each individual zone. Does not contain enemies, only Items and NPCS. */
public class Zone {
    
    /** The name of the zone. */
    private String zoneName;
    /** Represents the background image for the zone. */
    private String backgroundPath;
    /** The zone next to this zone, or null if none exists. */
    private Zone northZone, westZone, southZone, eastZone;
    /** The list of objects in this zone. */
    private ArrayList<NPC> objectList;

    /** Holds information for each individual zone. Does not contain enemies, only Items and NPCS.
     * @param zoneName the name of the zone
     * @param backgroundPath string representing the background
     * @param objectList the objects in the zone
     */
    public Zone(String zoneName, String backgroundPath, ArrayList<NPC> objectList) {
        this.zoneName = zoneName;
        this.backgroundPath = backgroundPath;
        this.objectList = objectList;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getBackgroundPath() {
        return backgroundPath;
    }

    public void setBackgroundPath(String backgroundPath) {
        this.backgroundPath = backgroundPath;
    }

    public Zone getNorthZone() {
        return northZone;
    }

    public void setNorthZone(Zone northZone) {
        this.northZone = northZone;
    }

    public Zone getWestZone() {
        return westZone;
    }

    public void setWestZone(Zone westZone) {
        this.westZone = westZone;
    }

    public Zone getSouthZone() {
        return southZone;
    }

    public void setSouthZone(Zone southZone) {
        this.southZone = southZone;
    }

    public Zone getEastZone() {
        return eastZone;
    }

    public void setEastZone(Zone eastZone) {
        this.eastZone = eastZone;
    }

    public ArrayList<NPC> getObjectList() {
        return objectList;
    }

    public void setObjectList(ArrayList<NPC> objectList) {
        this.objectList = objectList;
    }

    
}
