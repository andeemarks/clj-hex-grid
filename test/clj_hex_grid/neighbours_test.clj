(ns clj-hex-grid.neighbours-test
	(:require [clj-hex-grid.neighbours :as neighbours])
  (:use [midje.sweet]))

(facts "when determining neighbours"
	(facts "for flat hexen"
		(fact "the northern neighbour is y + 1, z - 1"
			(neighbours/neighbour_for {:x 0 :y 0 :z 0} :north) => {:x 0 :y 1 :z -1})
		(fact "the southern neighbour is y - 1, z + 1"
			(neighbours/neighbour_for {:x 0 :y 0 :z 0} :south) => {:x 0 :y -1 :z 1})
		(fact "the north eastern neighbour is x + 1, z - 1"
			(neighbours/neighbour_for {:x 0 :y 0 :z 0} :northeast) => {:x 1 :y 0 :z -1})
		(fact "the north western neighbour is y + 1, x - 1"
			(neighbours/neighbour_for {:x 0 :y 0 :z 0} :northwest) => {:x -1 :y 1 :z 0})
		(fact "the south eastern neighbour is x + 1, z - 1"
			(neighbours/neighbour_for {:x 0 :y 0 :z 0} :southeast) => {:x 1 :y -1 :z 0})
		(fact "the south western neighbour is z + 1, x - 1"
			(neighbours/neighbour_for {:x 0 :y 0 :z 0} :southwest) => {:x -1 :y 0 :z 1})
		)
	)
