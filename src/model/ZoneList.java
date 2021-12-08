package model;

import java.util.ArrayList;

public class ZoneList {

    private ArrayList<NPC> listStarterArea, listPathways, listGraveyard, listGrassyplains, listVillageSquare,
            listMarket, listLumberYard, listLake, listForestEdge, listWheatField;
    private ArrayList<Zone> levels;
    private Zone start, pathway, villageSquare, grassyPlains, graveyard,
            market, lake, wheatFields, forestEdge, lumberYard;

    private ZoneList() {
        generateLists();

        // Intialize the zones
        start = new Zone("Start Zone", "Final Assets/World/PNG/World-StartArea-1440x900.png", listStarterArea);
        pathway = new Zone("Pathways", "Final Assets/World/PNG/World-Pathways-1440x900.png", listPathways);
        graveyard = new Zone("Graveyard", "Final Assets/World/PNG/World-Graveyard-1440x900.png", listGraveyard);
        grassyPlains = new Zone("Grassy Plains", "Final Assets/World/PNG/World-GrassyPlains-1440x900.png",
                listGrassyplains);
        villageSquare = new Zone("Village Square", "Final Assets/World/PNG/World-VillageSquare-1440x900.png",
                listVillageSquare);
        market = new Zone("Rundown Market", "Final Assets/World/PNG/World-RunDown-1440x900.png", listMarket);
        lumberYard = new Zone("Lumber Yard", "Final Assets/World/PNG/World-LumberYard-1440x900.png", listLumberYard);
        lake = new Zone("Lake", "Final Assets/World/PNG/World-Lake-1440x900.png", listLake);
        wheatFields = new Zone("Wheat Fields", "Final Assets/World/PNG/World-WheatField-1440x900.png", listWheatField);
        forestEdge = new Zone("Forest Edge", "Final Assets/World/PNG/World-ForestEdge-1440x900.png", listForestEdge);

        // Connect the zones directionally
        setDirectionalZones(start, pathway, null, null, null);
        setDirectionalZones(pathway, villageSquare, grassyPlains, null, graveyard);
        setDirectionalZones(grassyPlains, market, null, null, pathway);
        setDirectionalZones(graveyard, lake, pathway, null, null);
        setDirectionalZones(market, lumberYard, null, grassyPlains, villageSquare);
        setDirectionalZones(villageSquare, forestEdge, market, pathway, lake);
        setDirectionalZones(lake, wheatFields, villageSquare, graveyard, null);
        setDirectionalZones(lumberYard, null, null, market, forestEdge);
        setDirectionalZones(forestEdge, null, lumberYard, villageSquare, wheatFields);
        setDirectionalZones(wheatFields, null, forestEdge, lake, null);

        // Add zones to the levels list
        levels = new ArrayList<Zone>();
        levels.add(start);
        levels.add(pathway);
        levels.add(graveyard);
        levels.add(grassyPlains);
        levels.add(villageSquare);
        levels.add(market);
        levels.add(lumberYard);
        levels.add(lake);
        levels.add(wheatFields);
        levels.add(forestEdge);

    }

    // Singleton for Zonelist.
    private static ZoneList zoneInstance = new ZoneList(); // create instance to be referenced

    public static ZoneList instance() {
        return zoneInstance;
    }

    /**
     * Sets a zone's north, west, south, and east possible zones.
     * 
     * @param currentZone - the zone currently in play.
     * @param northZone   - the zone north of the current zone
     * @param westZone    - the zone west of the current zone
     * @param southZone   - the zone south of the current zone
     * @param eastZone    - the zone east of the current zone
     */
    public void setDirectionalZones(Zone currentZone, Zone northZone, Zone westZone, Zone southZone, Zone eastZone) {
        currentZone.setNorthZone(northZone);
        currentZone.setWestZone(westZone);
        currentZone.setSouthZone(southZone);
        currentZone.setEastZone(eastZone);
    }

    // ********************************************************** //
    // BEWARE: Ugliest code you have ever seen, turn back now! //
    // ********************************************************** //

    /**
     * Creates a list of objects for each zone.
     */
    public void generateLists() {
        // Starting area ********************
        listStarterArea = new ArrayList<NPC>();

        listStarterArea.add(new NPC("Be careful in there, it's dangerous.", "NPC"));
        listStarterArea.get(0).setPosition(332, 393);
        listStarterArea.get(0).setInteractable(true);

        listStarterArea.add(new Item("", "tree", 0));
        listStarterArea.get(1).setPosition(26, 395);

        listStarterArea.add(new Item("", "tree", 0));
        listStarterArea.get(2).setPosition(932, 585);

        listStarterArea.add(new Item("", "tree", 0));
        listStarterArea.get(3).setPosition(1053, 268);

        listStarterArea.add(new Item("", "hFence", 0));
        listStarterArea.get(4).setPosition(-157, 172);

        listStarterArea.add(new Item("", "hFence", 0));
        listStarterArea.get(5).setPosition(28, 172);

        listStarterArea.add(new Item("", "hFence", 0));
        listStarterArea.get(6).setPosition(213, 172);

        listStarterArea.add(new Item("", "hFence", 0));
        listStarterArea.get(7).setPosition(397, 172);

        listStarterArea.add(new Item("", "hFence", 0));
        listStarterArea.get(8).setPosition(1363, 172);

        listStarterArea.add(new Item("", "hFence", 0));
        listStarterArea.get(9).setPosition(1179, 172);

        listStarterArea.add(new Item("", "hFence", 0));
        listStarterArea.get(10).setPosition(995, 172);

        listStarterArea.add(new Item("", "hFence", 0));
        listStarterArea.get(11).setPosition(810, 172);

        listStarterArea.add(new Item("", "archPoles", 0));
        listStarterArea.get(12).setCollidable(false);
        listStarterArea.get(12).setPosition(601, 21);

        listStarterArea.add(new Item("", "arch", 0));
        listStarterArea.get(13).setCollidable(false);
        listStarterArea.get(13).setPosition(600, 18);
        // **************************************

        // Pathways ****************************
        listPathways = new ArrayList<NPC>();
        listPathways.add(new Item("", "stump", 0));
        listPathways.get(0).setPosition(34, 34);

        listPathways.add(new Item("", "tree", 0));
        listPathways.get(1).setPosition(994, -34);

        listPathways.add(new Item("", "hFence", 0));
        listPathways.get(2).setPosition(903, 170);

        listPathways.add(new Item("", "vFence", 0));
        listPathways.get(3).setPosition(481, 575);

        listPathways.add(new Item("", "well", 0));
        listPathways.get(4).setPosition(903, 575);

        listPathways.add(new NPC("","coord"));
        listPathways.get(5).setPosition(301, 81);
        // *************************************

        // Graveyard ***************************
        listGraveyard = new ArrayList<NPC>();
        listGraveyard.add(new Item("", "tree", 0));
        listGraveyard.get(0).setPosition(-35, -69);

        listGraveyard.add(new Item("", "tree", 0));
        listGraveyard.get(1).setPosition(51, 593);

        listGraveyard.add(new Item("", "tree", 0));
        listGraveyard.get(2).setPosition(1172, -31);

        listGraveyard.add(new Item("", "tomb", 0));
        listGraveyard.get(3).setPosition(512, 351);

        listGraveyard.add(new Item("", "tomb", 0));
        listGraveyard.get(4).setPosition(680, 351);

        listGraveyard.add(new Item("", "tomb", 0));
        listGraveyard.get(5).setPosition(867, 351);

        listGraveyard.add(new Item("", "tomb", 0));
        listGraveyard.get(6).setPosition(1032, 351);

        listGraveyard.add(new Item("", "tomb", 0));
        listGraveyard.get(7).setPosition(1179, 351);

        listGraveyard.add(new Item("", "tomb", 0));
        listGraveyard.get(8).setPosition(512, 657);

        listGraveyard.add(new Item("", "tomb", 0));
        listGraveyard.get(9).setPosition(688, 657);

        listGraveyard.add(new Item("", "tomb", 0));
        listGraveyard.get(10).setPosition(878, 657);

        listGraveyard.add(new Item("", "tomb", 0));
        listGraveyard.get(11).setPosition(1032, 657);

        listGraveyard.add(new Item("", "tomb", 0));
        listGraveyard.get(12).setPosition(1196, 657);

        listGraveyard.add(new Item("", "hFence", 0));
        listGraveyard.get(13).setPosition(548, 245);

        listGraveyard.add(new Item("", "hFence", 0));
        listGraveyard.get(14).setPosition(732, 245);

        listGraveyard.add(new Item("", "hFence", 0));
        listGraveyard.get(15).setPosition(916, 245);

        listGraveyard.add(new Item("", "hFence", 0));
        listGraveyard.get(16).setPosition(1100, 245);

        listGraveyard.add(new Item("", "hFence", 0));
        listGraveyard.get(17).setPosition(1100, 788);

        listGraveyard.add(new Item("", "hFence", 0));
        listGraveyard.get(18).setPosition(916, 788);

        listGraveyard.add(new Item("", "hFence", 0));
        listGraveyard.get(19).setPosition(732, 788);

        listGraveyard.add(new Item("", "hFence", 0));
        listGraveyard.get(20).setPosition(548, 788);

        listGraveyard.add(new Item("", "hFence", 0));
        listGraveyard.get(21).setPosition(364, 788);

        listGraveyard.add(new Item("", "hFence", 0));
        listGraveyard.get(22).setPosition(364, 245);

        listGraveyard.add(new Item("", "vFence", 0));
        listGraveyard.get(23).setPosition(358, 231);

        listGraveyard.add(new Item("", "vFence", 0));
        listGraveyard.get(24).setPosition(358, 630);

        listGraveyard.add(new Item("", "vFence", 0));
        listGraveyard.get(25).setPosition(1278, 231);

        listGraveyard.add(new Item("", "vFence", 0));
        listGraveyard.get(26).setPosition(1278, 630);

        // listGraveyard.add(new NPC("Here lies the CpS 209 class of fall, 2021. Eleven started, three left.", "lSign"));   <- Feel free to change if you like Andrew
        // listGraveyard.get(27).setPosition(360, 358);

        listGraveyard.add(new NPC("Here lies the CpS 209 class of fall, 2021. Ten started, three left.", "sign"));
        listGraveyard.get(27).setPosition(368, 358);
        listGraveyard.get(27).setInteractable(true);

        listGraveyard.add(new NPC("", "coord"));
        listGraveyard.get(28).setPosition(909, 84);

        listGraveyard.add(new NPC("", "coord"));
        listGraveyard.get(29).setPosition(768, 450);

        listGraveyard.add(new Item("", "coin", 300));
        listGraveyard.get(30).setPosition(1274, 531);
        listGraveyard.get(30).setInteractable(true);
        // **************************************

        // Grassy Plains ********************
        listGrassyplains = new ArrayList<NPC>();
        listGrassyplains.add(new Item("", "tree", 0));
        listGrassyplains.get(0).setPosition(14, -21);

        listGrassyplains.add(new Item("", "tree", 0));
        listGrassyplains.get(1).setPosition(78, 532);

        listGrassyplains.add(new Item("", "tree", 0));
        listGrassyplains.get(2).setPosition(536, 537);

        listGrassyplains.add(new Item("", "tree", 0));
        listGrassyplains.get(3).setPosition(779, 365);

        listGrassyplains.add(new Item("", "tree", 0));
        listGrassyplains.get(4).setPosition(215, 260);

        listGrassyplains.add(new Item("", "tree", 0));
        listGrassyplains.get(5).setPosition(975, 568);

        listGrassyplains.add(new NPC("", "coord"));
        listGrassyplains.get(6).setPosition(467, 386);

        listGrassyplains.add(new NPC("", "coord"));
        listGrassyplains.get(7).setPosition(403, 20);

        listGrassyplains.add(new NPC("", "coord"));
        listGrassyplains.get(8).setPosition(1113, 20);

        listGrassyplains.add(new Item("", "coin", 500));
        listGrassyplains.get(9).setPosition(390, 710);
        listGrassyplains.get(9).setInteractable(true);
        // **************************************

        // Village Square ***********************
        listVillageSquare = new ArrayList<NPC>();
        listVillageSquare.add(new Item("", "well", 0));
        listVillageSquare.get(0).setPosition(633, 322);

        listVillageSquare.add(new Item("", "house", 0));
        listVillageSquare.get(1).setPosition(88, -98);

        listVillageSquare.add(new Item("", "house", 0));
        listVillageSquare.get(2).setPosition(825, -98);

        listVillageSquare.add(new NPC("Scary times, stranger.", "NPC"));
        listVillageSquare.get(3).setPosition(228, 270);
        listVillageSquare.get(3).setInteractable(true);

        listVillageSquare.add(new NPC("", "coord"));
        listVillageSquare.get(4).setPosition(1007, 280);

        listVillageSquare.add(new NPC("", "coord"));
        listVillageSquare.get(5).setPosition(1245, 505);

        listVillageSquare.add(new NPC("", "coord"));
        listVillageSquare.get(6).setPosition(228, 578);
        // **************************************

        // Run-down Market **********************
        listMarket = new ArrayList<NPC>();
        listMarket.add(new Item("", "tree", 0));
        listMarket.get(0).setPosition(228, -32);

        listMarket.add(new Item("", "tree", 0));
        listMarket.get(1).setPosition(-8, -57);
        listMarket.add(new NPC("Sorry traveler, my shop's been closed for quite awhile\nI've just come to visit my family.", "NPC"));
        listMarket.get(2).setPosition(386, 495);
        listMarket.get(2).setInteractable(true);

        listMarket.add(new Item("", "house", 0));
        listMarket.get(3).setPosition(-19, -112);

        listMarket.add(new Item("", "tree", 0));
        listMarket.get(4).setPosition(-8, 171);

        listMarket.add(new Item("", "tree", 0));
        listMarket.get(5).setPosition(215, 144);

        listMarket.add(new Item("", "hFence", 0));
        listMarket.get(6).setPosition(129, 367);

        listMarket.add(new Item("", "hFence", 0));
        listMarket.get(7).setPosition(-55, 367);

        listMarket.add(new Item("", "tomb", 0));
        listMarket.get(8).setPosition(40, 507);

        listMarket.add(new Item("", "tomb", 0));
        listMarket.get(9).setPosition(144, 507);

        listMarket.add(new Item("", "tomb", 0));
        listMarket.get(10).setPosition(248, 507);

        listMarket.add(new Item("", "vFence", 0));
        listMarket.get(11).setPosition(308, 440);

        listMarket.add(new Item("", "hFence", 0));
        listMarket.get(12).setPosition(-55, 601);

        listMarket.add(new Item("", "hFence", 0));
        listMarket.get(13).setPosition(130, 601);

        listMarket.add(new NPC("", "coord"));
        listMarket.get(14).setPosition(656, 14);
        
        listMarket.add(new NPC("", "coord"));
        listMarket.get(15).setPosition(1020, 726);
        // **************************************

        // Lumber Yard **************************
        listLumberYard = new ArrayList<NPC>();
        listLumberYard.add(new NPC("Good yield this year!\nShame about the monsters...", "NPC"));
        listLumberYard.get(0).setPosition(64, 663);
        listLumberYard.get(0).setInteractable(true);

        listLumberYard.add(new Item("", "stump", 0));
        listLumberYard.get(1).setPosition(0, 322);

        listLumberYard.add(new Item("", "stump", 0));
        listLumberYard.get(2).setPosition(213, 322);

        listLumberYard.add(new Item("", "stump", 0));
        listLumberYard.get(3).setPosition(418, 322);

        listLumberYard.add(new Item("", "stump", 0));
        listLumberYard.get(4).setPosition(621, 322);

        listLumberYard.add(new Item("", "stump", 0));
        listLumberYard.get(5).setPosition(0, 194);

        listLumberYard.add(new Item("", "stump", 0));
        listLumberYard.get(6).setPosition(213, 194);

        listLumberYard.add(new Item("", "stump", 0));
        listLumberYard.get(7).setPosition(418, 194);

        listLumberYard.add(new Item("", "stump", 0));
        listLumberYard.get(8).setPosition(621, 194);

        listLumberYard.add(new Item("", "stump", 0));
        listLumberYard.get(9).setPosition(0, 66);

        listLumberYard.add(new Item("", "stump", 0));
        listLumberYard.get(10).setPosition(213, 66);

        listLumberYard.add(new Item("", "stump", 0));
        listLumberYard.get(11).setPosition(418, 66);

        listLumberYard.add(new NPC("Johnson Lumber Farm", "sign"));
        listLumberYard.get(12).setPosition(450, 500);
        listLumberYard.get(12).setInteractable(true);

        listLumberYard.add(new NPC("", "coord"));
        listLumberYard.get(13).setPosition(592, 557);

        listLumberYard.add(new NPC("", "coord"));
        listLumberYard.get(14).setPosition(925, 14);

        listLumberYard.add(new NPC("", "coord"));
        listLumberYard.get(15).setPosition(1053, 290);
        // **************************************

        // Lake *********************************
        listLake = new ArrayList<NPC>();
        listLake.add(new NPC("Lake Goo-La-Goon", "lSign"));
        listLake.get(0).setPosition(1016, 106);
        listLake.get(0).setInteractable(true);
        // **************************************

        // Wheatfield ***************************
        listWheatField = new ArrayList<NPC>();
        listWheatField.add(new Item("", "hFence", 0));
        listWheatField.get(0).setPosition(532, -19);

        listWheatField.add(new Item("", "vFence", 0));
        listWheatField.get(1).setPosition(526, 357);

        listWheatField.add(new Item("", "vFence", 0));
        listWheatField.get(2).setPosition(526, -33);

        listWheatField.add(new Item("", "hFence", 0));
        listWheatField.get(3).setPosition(532, 516);

        listWheatField.add(new Item("", "hFence", 0));
        listWheatField.get(4).setPosition(716, 516);

        listWheatField.add(new Item("", "hFence", 0));
        listWheatField.get(5).setPosition(900, 516);

        listWheatField.add(new Item("", "hFence", 0));
        listWheatField.get(6).setPosition(1084, 516);

        listWheatField.add(new Item("", "hFence", 0));
        listWheatField.get(7).setPosition(1176, 516);

        listWheatField.add(new Item("", "vFence", 0));
        listWheatField.get(8).setPosition(1354, 69);

        listWheatField.add(new Item("", "vFence", 0));
        listWheatField.get(9).setPosition(1354, 213);

        listWheatField.add(new Item("", "vFence", 0));
        listWheatField.get(10).setPosition(1354, 357);

        listWheatField.add(new Item("", "hFence", 0));
        listWheatField.get(11).setPosition(716, -19);

        listWheatField.add(new Item("", "hFence", 0));
        listWheatField.get(12).setPosition(900, -19);

        listWheatField.add(new Item("", "hFence", 0));
        listWheatField.get(13).setPosition(1084, -19);

        listWheatField.add(new Item("", "hFence", 0));
        listWheatField.get(14).setPosition(1176, -19);

        listWheatField.add(new Item("", "stump", 0));
        listWheatField.get(15).setPosition(1284, -143);

        listWheatField.add(new Item("", "well", 0));
        listWheatField.get(16).setPosition(158, 14);

        listWheatField.add(new NPC("Haven't had a good yield in years...", "NPC"));
        listWheatField.get(17).setPosition(363, 133);
        listWheatField.get(17).setInteractable(true);

        listWheatField.add(new NPC("", "coord"));
        listWheatField.get(18).setPosition(1026, 115);

        listWheatField.add(new NPC("", "coord"));
        listWheatField.get(19).setPosition(1090, 699);

        listWheatField.add(new NPC("", "coord"));
        listWheatField.get(20).setPosition(720, 257);
        // ***************************************

        // Forest Edge ***************************
        listForestEdge = new ArrayList<NPC>();
        listForestEdge.add(new Item("", "tree", 0));
        listForestEdge.get(0).setPosition(14, -77);

        listForestEdge.add(new Item("", "tree", 0));
        listForestEdge.get(1).setPosition(168, -71);

        listForestEdge.add(new NPC(
                "Lost Woods ahead.\nAll who enter never return.", "sign"));
        listForestEdge.get(2).setPosition(666, 216);
        listForestEdge.get(2).setInteractable(true);

        listForestEdge.add(new Item("", "tree", 0));
        listForestEdge.get(3).setPosition(320, -63);

        listForestEdge.add(new Item("", "tree", 0));
        listForestEdge.get(4).setPosition(464, -77);

        listForestEdge.add(new Item("", "tree", 0));
        listForestEdge.get(5).setPosition(698, -71);

        listForestEdge.add(new Item("", "tree", 0));
        listForestEdge.get(6).setPosition(584, -63);

        listForestEdge.add(new Item("", "tree", 0));
        listForestEdge.get(7).setPosition(964, -40);

        listForestEdge.add(new Item("", "tree", 0));
        listForestEdge.get(8).setPosition(826, -49);

        listForestEdge.add(new Item("", "tree", 0));
        listForestEdge.get(9).setPosition(1259, -71);

        listForestEdge.add(new Item("", "tree", 0));
        listForestEdge.get(10).setPosition(1131, -49);

        listForestEdge.add(new Item("", "tree", 0));
        listForestEdge.get(11).setPosition(-78, -49);

        listForestEdge.add(new NPC("", "coord"));
        listForestEdge.get(12).setPosition(998, 500);

        listForestEdge.add(new NPC("", "coord"));
        listForestEdge.get(13).setPosition(344, 243);
        // ****************************************
    }

    public ArrayList<Zone> getLevels() {
        return levels;
    }

    public void setLevels(ArrayList<Zone> levels) {
        this.levels = levels;
    }

    public Zone getZone(String name) {

        return this.levels.stream().filter(z -> (z.getZoneName().equals(name))).findFirst().get();

    }

}
