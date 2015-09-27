(ns clj-hex-grid.neighbours-test
	(:require [clj-hex-grid.neighbours :as neighbours])
  (:use [midje.sweet]))

(facts "when determining neighbours"
	(facts "for cube coordinates"
		(fact "the northern neighbour is y + 1, z - 1"
			(neighbours/neighbour_for_cube {:x 0 :y 0 :z 0} :north) => {:x 0 :y 1 :z -1})
		(fact "the southern neighbour is y - 1, z + 1"
			(neighbours/neighbour_for_cube {:x 0 :y 0 :z 0} :south) => {:x 0 :y -1 :z 1})
		(fact "the north eastern neighbour is x + 1, z - 1"
			(neighbours/neighbour_for_cube {:x 0 :y 0 :z 0} :northeast) => {:x 1 :y 0 :z -1})
		(fact "the north western neighbour is y + 1, x - 1"
			(neighbours/neighbour_for_cube {:x 0 :y 0 :z 0} :northwest) => {:x -1 :y 1 :z 0})
		(fact "the south eastern neighbour is x + 1, z - 1"
			(neighbours/neighbour_for_cube {:x 0 :y 0 :z 0} :southeast) => {:x 1 :y -1 :z 0})
		(fact "the south western neighbour is z + 1, x - 1"
			(neighbours/neighbour_for_cube {:x 0 :y 0 :z 0} :southwest) => {:x -1 :y 0 :z 1})
		)
	(facts "for odd-q offset coordinates"
		(facts "when on an even column"
			(fact "the northern neighbour is y - 1"
				(neighbours/neighbour_for_odd_q {:x 0 :y 0} :north) => {:x 0 :y -1})
			(fact "the southern neighbour is y + 1"
				(neighbours/neighbour_for_odd_q {:x 0 :y 0} :south) => {:x 0 :y 1})
			(fact "the north eastern neighbour is x + 1, y - 1"
				(neighbours/neighbour_for_odd_q {:x 0 :y 0} :northeast) => {:x 1 :y -1})
			(fact "the north western neighbour is x - 1, y - 1"
				(neighbours/neighbour_for_odd_q {:x 0 :y 0} :northwest) => {:x -1 :y -1})
			(fact "the south eastern neighbour is x + 1"
				(neighbours/neighbour_for_odd_q {:x 0 :y 0} :southeast) => {:x 1 :y 0})
			(fact "the south western neighbour is x - 1"
				(neighbours/neighbour_for_odd_q {:x 0 :y 0} :southwest) => {:x -1 :y 0})
			)
		(facts "when on an odd column"
			(fact "the northern neighbour is y - 1"
				(neighbours/neighbour_for_odd_q {:x 1 :y 0} :north) => {:x 1 :y -1})
			(fact "the southern neighbour is y + 1"
				(neighbours/neighbour_for_odd_q {:x 1 :y 0} :south) => {:x 1 :y 1})
			(fact "the north eastern neighbour is x + 1, y - 1"
				(neighbours/neighbour_for_odd_q {:x 1 :y 0} :northeast) => {:x 2 :y -1})
			(fact "the north western neighbour is x - 1"
				(neighbours/neighbour_for_odd_q {:x 1 :y 0} :northwest) => {:x 0 :y 0})
			(fact "the south eastern neighbour is x + 1, y + 1"
				(neighbours/neighbour_for_odd_q {:x 1 :y 0} :southeast) => {:x 2 :y 1})
			(fact "the south western neighbour is x - 1, y + 1"
				(neighbours/neighbour_for_odd_q {:x 1 :y 0} :southwest) => {:x 0 :y 1})
			)
		)
	)
