package model;


import java.util.ArrayList;

public class ZoneList {
    
    private ArrayList<NPC> listStarterArea;
    private ArrayList<Zone> levels;
    private Zone currentZone;

    private ZoneList() {
        generateLists();
        levels = new ArrayList<Zone>();
        levels.add(new Zone("Start Zone", "Pathway", null, null, null, listStarterArea));

        currentZone = levels.get(0);
    }

    // Singleton for Zonelist.
    private static ZoneList zoneInstance = new ZoneList(); // create instance to be referenced

    public static ZoneList instance() {

        return zoneInstance;

    }

    /**
     * Creates a list of objects for each zone.
     */
    public void generateLists() {
        // Starting area ********************
        listStarterArea = new ArrayList<NPC>();
        listStarterArea.add(new NPC("Be careful out there, it's dangerous.", "NPC"));
        listStarterArea.get(0).setPosition(332, 393);
        listStarterArea.add(new Item("", "tree", 0));
        listStarterArea.get(1).setPosition(26, 395);
        listStarterArea.add(new Item("", "tree", 0));
        listStarterArea.get(2).setPosition(932, 585);
        listStarterArea.add(new Item("", "tree", 0));
        listStarterArea.get(3).setPosition(1053, 268);
        listStarterArea.add(new Item("", "hFence", 0));
        listStarterArea.get(4).setPosition(-157, 86);
        listStarterArea.add(new Item("", "hFence", 0));
        listStarterArea.get(5).setPosition(28, 86);
        listStarterArea.add(new Item("", "hFence", 0));
        listStarterArea.get(6).setPosition(213, 86);
        listStarterArea.add(new Item("", "hFence", 0));
        listStarterArea.get(7).setPosition(397, 86);
        listStarterArea.add(new Item("", "hFence", 0));
        listStarterArea.get(8).setPosition(1363, 86);
        listStarterArea.add(new Item("", "hFence", 0));
        listStarterArea.get(9).setPosition(1179, 86);
        listStarterArea.add(new Item("", "hFence", 0));
        listStarterArea.get(10).setPosition(995, 86);
        listStarterArea.add(new Item("", "hFence", 0));
        listStarterArea.get(11).setPosition(810, 86);
        listStarterArea.add(new Item("", "archPoles", 0));
        listStarterArea.get(12).setPosition(601, 21);
        listStarterArea.add(new Item("", "arch", 0));
        listStarterArea.get(13).setPosition(600, 18);
        //**************************************

        


        

    }

    public ArrayList<Zone> getLevels() {
        return levels;
    }

    public void setLevels(ArrayList<Zone> levels) {
        this.levels = levels;
    }

    public Zone getCurrentZone() {
        return currentZone;
    }

    public void setCurrentZone(Zone currentZone) {
        this.currentZone = currentZone;
    }

    
}
