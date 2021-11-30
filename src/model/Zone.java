package model;

import java.util.ArrayList;

// Holds information for each individual zone. Does not contain enemies, only Items and NPCS. 
public class Zone {
    
    private String zoneName, backgroundPath;
    private Zone northZone, westZone, southZone, eastZone;
    private ArrayList<NPC> objectList;
    

    public Zone(String zoneName, String backgroundPath, Zone northZone, Zone westZone, Zone southZone, Zone eastZone,
            ArrayList<NPC> objectList) {
        this.zoneName = zoneName;
        this.backgroundPath = backgroundPath;
        this.northZone = northZone;
        this.westZone = westZone;
        this.southZone = southZone;
        this.eastZone = eastZone;
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
