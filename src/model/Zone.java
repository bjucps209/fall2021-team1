package model;

import java.util.ArrayList;

// Holds information for each individual zone. Does not contain enemies, only Items and NPCS. 
public class Zone {
    
    private String zoneName;
    private String northName, westName, southName, eastName;
    private ArrayList<NPC> objectList;
    

    public Zone(String zoneName, String northName, String westName, String southName, String eastName,
            ArrayList<NPC> objectList) {
        this.zoneName = zoneName;
        this.northName = northName;
        this.westName = westName;
        this.southName = southName;
        this.eastName = eastName;
        this.objectList = objectList;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getNorthName() {
        return northName;
    }

    public void setNorthName(String northName) {
        this.northName = northName;
    }

    public String getWestName() {
        return westName;
    }

    public void setWestName(String westName) {
        this.westName = westName;
    }

    public String getSouthName() {
        return southName;
    }

    public void setSouthName(String southName) {
        this.southName = southName;
    }

    public String getEastName() {
        return eastName;
    }

    public void setEastName(String eastName) {
        this.eastName = eastName;
    }

    public ArrayList<NPC> getObjectList() {
        return objectList;
    }

    public void setObjectList(ArrayList<NPC> objectList) {
        this.objectList = objectList;
    }

    
}
