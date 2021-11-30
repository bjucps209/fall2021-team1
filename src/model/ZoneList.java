package model;


import java.util.ArrayList;

public class ZoneList {
    
    private ArrayList<NPC> listStarterArea, listPathways;
    private ArrayList<Zone> levels;
    private Zone currentZone;

    private ZoneList() {
        generateLists();
        levels = new ArrayList<Zone>();
        levels.add(new Zone("Start Zone", "Pathway", null, null, null, listStarterArea));
        levels.add(new Zone("Pathway", "Village Square", "Grassy Plains", "Start Zone", "Graveyard", listPathways));

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
        listStarterArea.get(0).setInteractable(true);
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

        // Pathways ****************************
        listPathways = new ArrayList<NPC>();
        listPathways.add(new Item("", "stump", 0));
        listPathways.get(0).setPosition(-46, -143);
        listPathways.add(new Item("", "tree", 0));
        listPathways.get(1).setPosition(994, -34);
        listPathways.add(new Item("", "hFence", 0));
        listPathways.get(2).setPosition(903, 84);
        listPathways.add(new Item("", "vFence", 0));
        listPathways.get(3).setPosition(395, 575);
        listPathways.add(new Item("", "well", 0));
        listPathways.get(4).setPosition(903, 575);
        // *************************************



        


        

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
