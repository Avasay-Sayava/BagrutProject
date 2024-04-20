package com.avasaysayava.bagrutproject.game.graphic.gamemap;

import com.avasaysayava.bagrutproject.game.Game;

import java.util.Collections;
import java.util.List;

public class PropsMap extends GameMap {
    @SuppressWarnings("unchecked")
    public PropsMap(Game game, float x, float y) {
        super(
                game,
                new List[][]{
                        new List[]{
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.singletonList(game.propsTileSet.getTile(0)),
                                Collections.emptyList(),
                                Collections.singletonList(game.propsTileSet.getTile(1)),
                                Collections.singletonList(game.propsTileSet.getTile(2)),
                                Collections.emptyList(),
                                Collections.singletonList(game.propsTileSet.getTile(3)),
                                Collections.emptyList(),
                                Collections.singletonList(game.propsTileSet.getTile(4))
                        },
                        new List[]{
                                Collections.singletonList(game.propsTileSet.getTile(13)),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.singletonList(game.propsTileSet.getTile(5)),
                                Collections.emptyList(),
                                Collections.singletonList(game.propsTileSet.getTile(6)),
                                Collections.singletonList(game.propsTileSet.getTile(7)),
                                Collections.emptyList(),
                                Collections.singletonList(game.propsTileSet.getTile(8)),
                                Collections.emptyList(),
                                Collections.singletonList(game.propsTileSet.getTile(9))
                        },
                        new List[]{
                                Collections.singletonList(game.propsTileSet.getTile(14)),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.singletonList(game.propsTileSet.getTile(10)),
                                Collections.singletonList(game.propsTileSet.getTile(11)),
                                Collections.singletonList(game.propsTileSet.getTile(12)),
                        }
                },
                32,
                x,
                y
        );
    }
}
