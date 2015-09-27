(ns clj-hex-grid.neighbours-test
	(:require [clj-hex-grid.neighbours :as neighbours])
  (:use [midje.sweet]))

(facts "when determining neighbours"
	(facts "for flat hexen"
		(fact "the northern neighbour is y + 1, z -1"
			(neighbours/neighbour_for {:x 0 :y 0 :z 0} :north) => {:x 0 :y 1 :z -1})
		)
	)
