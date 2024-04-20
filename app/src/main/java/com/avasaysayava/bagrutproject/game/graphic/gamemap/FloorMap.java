package com.avasaysayava.bagrutproject.game.graphic.gamemap;

import com.avasaysayava.bagrutproject.game.Game;

import java.util.Collections;
import java.util.List;

public class FloorMap extends GameMap {
    @SuppressWarnings("unchecked")
    public FloorMap(Game game, float x, float y) {
        super(
                game,
                new List[][]{
                        new List[]{
                                Collections.singletonList(game.floorTileSet.getTile(0).withZ(0)),
                                Collections.singletonList(game.floorTileSet.getTile(1).withZ(0)),
                                Collections.singletonList(game.floorTileSet.getTile(2).withZ(0)),
                                Collections.singletonList(game.floorTileSet.getTile(3).withZ(0)),
                                Collections.singletonList(game.floorTileSet.getTile(4).withZ(0)),
                                Collections.singletonList(game.floorTileSet.getTile(5).withZ(0)),
                                Collections.singletonList(game.floorTileSet.getTile(6).withZ(0)),
                                Collections.singletonList(game.floorTileSet.getTile(7).withZ(0))
                        },
                        new List[]{
                                Collections.singletonList(game.floorTileSet.getTile(8).withZ(0)),
                                Collections.singletonList(game.floorTileSet.getTile(9).withZ(0)),
                                Collections.singletonList(game.floorTileSet.getTile(10).withZ(0)),
                                Collections.singletonList(game.floorTileSet.getTile(11).withZ(0)),
                                Collections.singletonList(game.floorTileSet.getTile(12).withZ(0)),
                                Collections.singletonList(game.floorTileSet.getTile(13).withZ(0)),
                                Collections.singletonList(game.floorTileSet.getTile(14).withZ(0)),
                                Collections.singletonList(game.floorTileSet.getTile(15).withZ(0))
                        },
                        new List[]{
                                Collections.singletonList(game.floorTileSet.getTile(16).withZ(0)),
                                Collections.singletonList(game.floorTileSet.getTile(17).withZ(0)),
                                Collections.singletonList(game.floorTileSet.getTile(18).withZ(0)),
                                Collections.singletonList(game.floorTileSet.getTile(19).withZ(0)),
                                Collections.singletonList(game.floorTileSet.getTile(20).withZ(0)),
                                Collections.singletonList(game.floorTileSet.getTile(21).withZ(0)),
                                Collections.singletonList(game.floorTileSet.getTile(22).withZ(0)),
                                Collections.singletonList(game.floorTileSet.getTile(23).withZ(0))
                        },
                        new List[]{
                                Collections.singletonList(game.floorTileSet.getTile(24).withZ(0)),
                                Collections.singletonList(game.floorTileSet.getTile(25).withZ(0)),
                                Collections.singletonList(game.floorTileSet.getTile(26).withZ(0)),
                                Collections.singletonList(game.floorTileSet.getTile(27).withZ(0)),
                                Collections.singletonList(game.floorTileSet.getTile(28).withZ(0)),
                                Collections.emptyList(),
                                Collections.singletonList(game.floorTileSet.getTile(29).withZ(0)),
                                Collections.emptyList()
                        },
                        new List[]{
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.singletonList(game.floorTileSet.getTile(30).withZ(0)),
                                Collections.singletonList(game.floorTileSet.getTile(31).withZ(0)),
                                Collections.singletonList(game.floorTileSet.getTile(32).withZ(0))
                        },
                        new List[]{
                                Collections.singletonList(game.floorTileSet.getTile(33).withZ(0)),
                                Collections.singletonList(game.floorTileSet.getTile(34).withZ(0)),
                                Collections.singletonList(game.floorTileSet.getTile(35).withZ(0)),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.singletonList(game.floorTileSet.getTile(36).withZ(0)),
                                Collections.emptyList()
                        },
                        new List[]{
                                Collections.singletonList(game.floorTileSet.getTile(37).withZ(0)),
                                Collections.emptyList(),
                                Collections.singletonList(game.floorTileSet.getTile(38).withZ(0)),
                                Collections.emptyList(),
                                Collections.singletonList(game.floorTileSet.getTile(39).withZ(0)),
                                Collections.singletonList(game.floorTileSet.getTile(40).withZ(0)),
                                Collections.singletonList(game.floorTileSet.getTile(41).withZ(0)),
                                Collections.singletonList(game.floorTileSet.getTile(42).withZ(0))
                        },
                        new List[]{
                                Collections.singletonList(game.floorTileSet.getTile(43).withZ(0)),
                                Collections.singletonList(game.floorTileSet.getTile(44).withZ(0)),
                                Collections.singletonList(game.floorTileSet.getTile(45).withZ(0)),
                                Collections.emptyList(),
                                Collections.singletonList(game.floorTileSet.getTile(46).withZ(0)),
                                Collections.singletonList(game.floorTileSet.getTile(47).withZ(0)),
                                Collections.singletonList(game.floorTileSet.getTile(48).withZ(0)),
                                Collections.singletonList(game.floorTileSet.getTile(49).withZ(0))
                        }
                },
                32,
                x,
                y
        );
    }
}
