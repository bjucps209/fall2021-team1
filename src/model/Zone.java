package model;

import java.util.ArrayList;

// Holds information for each individual zone. Does not contain enemies, only Items and NPCS. 
public class Zone {
    
    private String zoneName, backgroundPath; // The current zone name, the URL to the background image of the current zone.
    private Zone northZone, westZone, southZone, eastZone; // The zones located on each of the 4 directions of the current zone.
    private ArrayList<NPC> objectList; // The list of every npc or item in the current zone.
    

    public Zone(String zoneName, String backgroundPath,
            ArrayList<NPC> objectList) {
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
