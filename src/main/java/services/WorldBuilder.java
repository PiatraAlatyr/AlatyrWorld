package services;

import world.Island;

import static world.Island.createIsland;

public class WorldBuilder {

    Island island;

    public WorldBuilder(int x, int y) {
        island = createIsland(x, y);
    }

    public Island getIsland() {
        return island;
    }
}
