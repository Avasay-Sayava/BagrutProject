package com.avasaysayava.bagrutproject.game.graphic.gamemap;

import com.avasaysayava.bagrutproject.game.Game;

import java.util.Collections;
import java.util.List;

public class GroundMap extends GameMap {
    @SuppressWarnings("unchecked")
    public GroundMap(Game game, float x, float y) {
        super(
                game,
                new List[][]{
                        new List[]{
                                Collections.singletonList(game.groundTileSet.getTile(0).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(1).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(2).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(3).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(4).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(5).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(6).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(7).withZ(0))
                        },
                        new List[]{
                                Collections.singletonList(game.groundTileSet.getTile(8).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(9).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(10).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(11).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(12).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(13).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(14).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(15).withZ(0))
                        },
                        new List[]{
                                Collections.singletonList(game.groundTileSet.getTile(16).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(17).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(18).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(19).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(20).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(21).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(22).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(23).withZ(0))
                        },
                        new List[]{
                                Collections.singletonList(game.groundTileSet.getTile(24).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(25).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(26).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(27).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(28).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(29).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(30).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(31).withZ(0))
                        },
                        new List[]{
                                Collections.singletonList(game.groundTileSet.getTile(32).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(33).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(34).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(35).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(36).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(37).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(38).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(39).withZ(0))
                        },
                        new List[]{
                                Collections.singletonList(game.groundTileSet.getTile(40).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(41).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(42).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(43).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(44).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(45).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(46).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(47).withZ(0))
                        },
                        new List[]{
                                Collections.singletonList(game.groundTileSet.getTile(48).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(49).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(50).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(51).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(52).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(53).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(54).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(55).withZ(0))
                        },
                        new List[]{
                                Collections.singletonList(game.groundTileSet.getTile(56).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(57).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(58).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(59).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(60).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(61).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(62).withZ(0)),
                                Collections.singletonList(game.groundTileSet.getTile(63).withZ(0))
                        }
                },
                32,
                x,
                y
        );
    }
}
