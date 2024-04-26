package com.avasaysayava.bagrutproject.game.graphic.gamemap;

import com.avasaysayava.bagrutproject.game.Game;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Level1Map extends GameMap {
    @SuppressWarnings("unchecked")
    public Level1Map(Game game, float x, float y) {
        super(game,
                new List[][]{
                        new List[]{
                                Arrays.asList(game.groundTileSet.getTile(62), game.wallsTileSet.getTile(0).withZ(0)),
                                Arrays.asList(game.groundTileSet.getTile(3), game.wallsTileSet.getTile(1).withZ(0), game.structuresTileSet.getTile(10).withZ(0)),
                                Arrays.asList(game.groundTileSet.getTile(63), game.wallsTileSet.getTile(2).withZ(0)),
                        },
                        new List[]{
                                Arrays.asList(game.groundTileSet.getTile(7), game.wallsTileSet.getTile(13).withZ(0)),
                                Arrays.asList(game.groundTileSet.getTile(11), game.structuresTileSet.getTile(16).withZ(0)),
                                Arrays.asList(game.groundTileSet.getTile(11), game.wallsTileSet.getTile(14).withZ(0))
                        },
                        new List[]{
                                Arrays.asList(game.groundTileSet.getTile(7), game.wallsTileSet.getTile(13).withZ(0)),
                                Arrays.asList(game.groundTileSet.getTile(3), game.structuresTileSet.getTile(21).withZ(0)),
                                Arrays.asList(game.groundTileSet.getTile(11), game.wallsTileSet.getTile(14).withZ(0)),
                        },
                        new List[]{
                                Arrays.asList(game.groundTileSet.getTile(7), game.wallsTileSet.getTile(13).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(11)),
                                Arrays.asList(game.groundTileSet.getTile(11), game.wallsTileSet.getTile(14).withZ(0))
                        },
                        new List[]{
                                Arrays.asList(game.groundTileSet.getTile(7), game.wallsTileSet.getTile(13).withZ(0)),
                                Arrays.asList(game.groundTileSet.getTile(11), game.floorTileSet.getTile(27)),
                                Arrays.asList(game.groundTileSet.getTile(11), game.wallsTileSet.getTile(14).withZ(0))
                        },
                        new List[]{
                                Arrays.asList(game.groundTileSet.getTile(7), game.wallsTileSet.getTile(13).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(11)),
                                Arrays.asList(game.groundTileSet.getTile(11), game.wallsTileSet.getTile(14).withZ(0))
                        },
                        new List[]{
                                Collections.singletonList(game.wallsTileSet.getTile(41).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(56).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(46).withZ(0)),
                        }
                },
                32,
                x,
                y
        );
    }
}
