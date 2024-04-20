package com.avasaysayava.bagrutproject.game.graphic.gamemap;

import com.avasaysayava.bagrutproject.game.Game;

import java.util.Collections;
import java.util.List;

public class StructuresMap extends GameMap {
    @SuppressWarnings("unchecked")
    public StructuresMap(Game game, float x, float y) {
        super(
                game,
                new List[][]{
                        new List[]{
                                Collections.singletonList(game.structuresTileSet.getTile(0).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(1).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(2).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(3).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(4).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(5).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(6).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(7).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(8).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(9).withZ(1)),
                                Collections.emptyList(),
                                Collections.singletonList(game.structuresTileSet.getTile(10).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(11).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(12).withZ(1))
                        },
                        new List[]{
                                Collections.singletonList(game.structuresTileSet.getTile(13).withZ(1)),
                                Collections.emptyList(),
                                Collections.singletonList(game.structuresTileSet.getTile(14).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(14).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(15).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(16).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(17).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(13).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(18).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(19).withZ(1)),
                                Collections.emptyList(),
                                Collections.singletonList(game.structuresTileSet.getTile(20).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(21).withZ(1))
                        },
                        new List[]{
                                Collections.singletonList(game.structuresTileSet.getTile(22).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(23).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(24).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(14).withZ(1)),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.singletonList(game.structuresTileSet.getTile(13).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(25).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(26).withZ(1)),
                                Collections.emptyList(),
                                Collections.singletonList(game.structuresTileSet.getTile(27).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(28).withZ(0))
                        },
                        new List[]{
                                Collections.singletonList(game.structuresTileSet.getTile(29).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(30).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(31).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(32).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(1).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(1).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(1).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(33).withZ(1)),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.singletonList(game.structuresTileSet.getTile(34).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(35).withZ(0))
                        },
                        new List[]{},
                        new List[]{
                                Collections.singletonList(game.structuresTileSet.getTile(36).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(37).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(38).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(39).withZ(0)),
                                Collections.emptyList(),
                                Collections.singletonList(game.structuresTileSet.getTile(40).withZ(0)),
                                Collections.emptyList(),
                                Collections.singletonList(game.structuresTileSet.getTile(41).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(42).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(43).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(44).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(45).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(46).withZ(1))
                        },
                        new List[]{
                                Collections.singletonList(game.structuresTileSet.getTile(47).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(48).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(49).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(50).withZ(0)),
                                Collections.emptyList(),
                                Collections.singletonList(game.structuresTileSet.getTile(51).withZ(0)),
                                Collections.emptyList(),
                                Collections.singletonList(game.structuresTileSet.getTile(52).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(53).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(54).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(55).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(56).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(57).withZ(1))
                        },
                        new List[]{},
                        new List[]{
                                Collections.singletonList(game.structuresTileSet.getTile(58).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(59).withZ(0)),
                                Collections.emptyList(),
                                Collections.singletonList(game.structuresTileSet.getTile(60).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(61).withZ(0)),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.singletonList(game.structuresTileSet.getTile(62).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(63).withZ(0)),
                                Collections.emptyList(),
                                Collections.singletonList(game.structuresTileSet.getTile(64).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(65).withZ(0))
                        },
                        new List[]{
                                Collections.singletonList(game.structuresTileSet.getTile(66).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(67).withZ(0)),
                                Collections.emptyList(),
                                Collections.singletonList(game.structuresTileSet.getTile(68).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(69).withZ(0)),
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.singletonList(game.structuresTileSet.getTile(70).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(71).withZ(0)),
                                Collections.emptyList(),
                                Collections.singletonList(game.structuresTileSet.getTile(72).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(73).withZ(0))
                        },
                        new List[]{},
                        new List[]{
                                Collections.singletonList(game.structuresTileSet.getTile(74).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(75).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(76).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(77).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(78).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(79).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(80).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(81).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(82).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(83).withZ(1)),
                                Collections.singletonList(game.structuresTileSet.getTile(84).withZ(1))
                        },
                        new List[]{
                                Collections.singletonList(game.structuresTileSet.getTile(85).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(86).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(87).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(86).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(88).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(89).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(90).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(89).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(91).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(92).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(93).withZ(0))
                        },
                        new List[]{
                                Collections.singletonList(game.structuresTileSet.getTile(94).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(95).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(96).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(95).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(97).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(98).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(99).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(98).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(100).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(101).withZ(0)),
                                Collections.singletonList(game.structuresTileSet.getTile(102).withZ(0))
                        }
                },
                32,
                x,
                y
        );
    }
}
