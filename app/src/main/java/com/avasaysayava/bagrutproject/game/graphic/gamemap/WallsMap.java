package com.avasaysayava.bagrutproject.game.graphic.gamemap;

import com.avasaysayava.bagrutproject.game.Game;

import java.util.Collections;
import java.util.List;

public class WallsMap extends GameMap {
    @SuppressWarnings("unchecked")
    public WallsMap(Game game, float x, float y) {
        super(
                game,
                new List[][]{
                        new List[]{
                                Collections.singletonList(game.wallsTileSet.getTile(0).withZ(1)),
                                Collections.singletonList(game.wallsTileSet.getTile(1).withZ(1)),
                                Collections.singletonList(game.wallsTileSet.getTile(2).withZ(1)),
                                Collections.singletonList(game.wallsTileSet.getTile(3).withZ(1)),
                                Collections.singletonList(game.wallsTileSet.getTile(4).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(5).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(6).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(7).withZ(1)),
                                Collections.singletonList(game.wallsTileSet.getTile(8).withZ(1)),
                                Collections.singletonList(game.wallsTileSet.getTile(9).withZ(1)),
                                Collections.emptyList(),
                                Collections.singletonList(game.wallsTileSet.getTile(10).withZ(1)),
                                Collections.singletonList(game.wallsTileSet.getTile(11).withZ(1)),
                                Collections.singletonList(game.wallsTileSet.getTile(12).withZ(1))
                        },
                        new List[]{
                                Collections.singletonList(game.wallsTileSet.getTile(13).withZ(1)),
                                Collections.emptyList(),
                                Collections.singletonList(game.wallsTileSet.getTile(14).withZ(1)),
                                Collections.singletonList(game.wallsTileSet.getTile(14).withZ(1)),
                                Collections.singletonList(game.wallsTileSet.getTile(15).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(16).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(17).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(13).withZ(1)),
                                Collections.singletonList(game.wallsTileSet.getTile(18).withZ(1)),
                                Collections.singletonList(game.wallsTileSet.getTile(19).withZ(1)),
                                Collections.emptyList(),
                                Collections.singletonList(game.wallsTileSet.getTile(20).withZ(1)),
                                Collections.singletonList(game.wallsTileSet.getTile(21).withZ(1))
                        },
                        new List[]{
                                Collections.singletonList(game.wallsTileSet.getTile(22).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(23).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(24).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(14).withZ(1)),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.singletonList(game.wallsTileSet.getTile(13).withZ(1)),
                                Collections.singletonList(game.wallsTileSet.getTile(25).withZ(1)),
                                Collections.singletonList(game.wallsTileSet.getTile(26).withZ(1)),
                                Collections.emptyList(),
                                Collections.singletonList(game.wallsTileSet.getTile(27).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(28).withZ(0))
                        },
                        new List[]{
                                Collections.singletonList(game.wallsTileSet.getTile(29).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(30).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(31).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(32).withZ(1)),
                                Collections.singletonList(game.wallsTileSet.getTile(1).withZ(1)),
                                Collections.singletonList(game.wallsTileSet.getTile(1).withZ(1)),
                                Collections.singletonList(game.wallsTileSet.getTile(1).withZ(1)),
                                Collections.singletonList(game.wallsTileSet.getTile(33).withZ(1)),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.singletonList(game.wallsTileSet.getTile(34).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(35).withZ(0))
                        },
                        new List[]{},
                        new List[]{
                                Collections.singletonList(game.wallsTileSet.getTile(36).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(37).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(38).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(39).withZ(0)),
                                Collections.emptyList(),
                                Collections.singletonList(game.wallsTileSet.getTile(40).withZ(0)),
                                Collections.emptyList(),
                                Collections.singletonList(game.wallsTileSet.getTile(41).withZ(1)),
                                Collections.singletonList(game.wallsTileSet.getTile(42).withZ(1)),
                                Collections.singletonList(game.wallsTileSet.getTile(43).withZ(1)),
                                Collections.singletonList(game.wallsTileSet.getTile(44).withZ(1)),
                                Collections.singletonList(game.wallsTileSet.getTile(45).withZ(1)),
                                Collections.singletonList(game.wallsTileSet.getTile(46).withZ(1))
                        },
                        new List[]{
                                Collections.singletonList(game.wallsTileSet.getTile(47).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(48).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(49).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(50).withZ(0)),
                                Collections.emptyList(),
                                Collections.singletonList(game.wallsTileSet.getTile(51).withZ(0)),
                                Collections.emptyList(),
                                Collections.singletonList(game.wallsTileSet.getTile(52).withZ(1)),
                                Collections.singletonList(game.wallsTileSet.getTile(53).withZ(1)),
                                Collections.singletonList(game.wallsTileSet.getTile(54).withZ(1)),
                                Collections.singletonList(game.wallsTileSet.getTile(55).withZ(1)),
                                Collections.singletonList(game.wallsTileSet.getTile(56).withZ(1)),
                                Collections.singletonList(game.wallsTileSet.getTile(57).withZ(1))
                        },
                        new List[]{},
                        new List[]{
                                Collections.singletonList(game.wallsTileSet.getTile(58).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(59).withZ(0)),
                                Collections.emptyList(),
                                Collections.singletonList(game.wallsTileSet.getTile(60).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(61).withZ(0)),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.singletonList(game.wallsTileSet.getTile(62).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(63).withZ(0)),
                                Collections.emptyList(),
                                Collections.singletonList(game.wallsTileSet.getTile(64).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(65).withZ(0))
                        },
                        new List[]{
                                Collections.singletonList(game.wallsTileSet.getTile(66).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(67).withZ(0)),
                                Collections.emptyList(),
                                Collections.singletonList(game.wallsTileSet.getTile(68).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(69).withZ(0)),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.singletonList(game.wallsTileSet.getTile(70).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(71).withZ(0)),
                                Collections.emptyList(),
                                Collections.singletonList(game.wallsTileSet.getTile(72).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(73).withZ(0))
                        },
                        new List[]{},
                        new List[]{
                                Collections.singletonList(game.wallsTileSet.getTile(74).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(75).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(76).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(77).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(78).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(79).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(80).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(81).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(82).withZ(1)),
                                Collections.singletonList(game.wallsTileSet.getTile(83).withZ(1)),
                                Collections.singletonList(game.wallsTileSet.getTile(84).withZ(1))
                        },
                        new List[]{
                                Collections.singletonList(game.wallsTileSet.getTile(85).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(86).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(87).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(86).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(88).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(89).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(90).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(89).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(91).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(92).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(93).withZ(0))
                        },
                        new List[]{
                                Collections.singletonList(game.wallsTileSet.getTile(94).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(95).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(96).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(95).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(97).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(98).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(99).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(98).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(100).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(101).withZ(0)),
                                Collections.singletonList(game.wallsTileSet.getTile(102).withZ(0))
                        }
                },
                32,
                x,
                y
        );
    }
}
