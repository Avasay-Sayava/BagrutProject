package com.avasaysayava.bagrutproject.game.graphic.gamemap.debugmap;

import com.avasaysayava.bagrutproject.game.Game;

import java.util.Collections;
import java.util.List;

public class GlyphFloorMap extends DebugMap {
    @SuppressWarnings("unchecked")
    public GlyphFloorMap(Game game, float x, float y) {
        super(
                game,
                new List[][]{
                        new List[]{
                                Collections.singletonList(game.glyphFloorTileSet.getTile(0).withZ(0)),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(1).withZ(0)),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(2).withZ(0)),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(3).withZ(0)),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(4).withZ(0)),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(5).withZ(0)),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(6).withZ(0)),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(7).withZ(0))
                        },
                        new List[]{
                                Collections.singletonList(game.glyphFloorTileSet.getTile(8).withZ(0)),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(9).withZ(0)),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(10).withZ(0)),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(11).withZ(0)),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(12).withZ(0)),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(13).withZ(0)),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(14).withZ(0)),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(15).withZ(0))
                        },
                        new List[]{
                                Collections.singletonList(game.glyphFloorTileSet.getTile(16).withZ(0)),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(17).withZ(0)),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(18).withZ(0)),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(19).withZ(0)),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(20).withZ(0)),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(21).withZ(0)),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(22).withZ(0)),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(23).withZ(0))
                        },
                        new List[]{
                                Collections.singletonList(game.glyphFloorTileSet.getTile(24).withZ(0)),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(25).withZ(0)),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(26).withZ(0)),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(27).withZ(0)),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(28).withZ(0)),
                                Collections.emptyList(),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(29).withZ(0)),
                                Collections.emptyList()
                        },
                        new List[]{
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(30).withZ(0)),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(31).withZ(0)),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(32).withZ(0))
                        },
                        new List[]{
                                Collections.singletonList(game.glyphFloorTileSet.getTile(33).withZ(0)),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(34).withZ(0)),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(35).withZ(0)),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(36).withZ(0)),
                                Collections.emptyList()
                        },
                        new List[]{
                                Collections.singletonList(game.glyphFloorTileSet.getTile(37).withZ(0)),
                                Collections.emptyList(),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(38).withZ(0)),
                                Collections.emptyList(),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(39).withZ(0)),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(40).withZ(0)),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(41).withZ(0)),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(42).withZ(0))
                        },
                        new List[]{
                                Collections.singletonList(game.glyphFloorTileSet.getTile(43).withZ(0)),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(44).withZ(0)),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(45).withZ(0)),
                                Collections.emptyList(),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(46).withZ(0)),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(47).withZ(0)),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(48).withZ(0)),
                                Collections.singletonList(game.glyphFloorTileSet.getTile(49).withZ(0))
                        }
                },
                32,
                x,
                y
        );
    }
}
