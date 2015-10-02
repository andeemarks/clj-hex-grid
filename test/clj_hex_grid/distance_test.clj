(ns clj-hex-grid.distance-test
	(:require [clj-hex-grid.distance :as distance])
	(:require [clj-hex-grid.neighbours :as n])
  (:use [midje.sweet]))

(def origin {:x 0 :y 0 :z 0})

(facts "when determining distance"
	(facts "for cube coordinates"
		(fact "all neighbours are distance 1"
			(let [neighbours (n/neighbours_for_cube origin)]
			(distance/between origin (nth neighbours 0)) => 1
			(distance/between origin (nth neighbours 1)) => 1
			(distance/between origin (nth neighbours 2)) => 1
			(distance/between origin (nth neighbours 3)) => 1
			(distance/between origin (nth neighbours 4)) => 1
			(distance/between origin (nth neighbours 5)) => 1))
		(fact "no distance between a point and itself"
			(distance/between origin origin) => 0)
		(fact "other distances are also calculated correctly"
			(distance/between origin {:x 2 :y 0 :z -2}) => 2
			(distance/between origin {:x 1 :y 2 :z -3}) => 3
			(distance/between origin {:x -4 :y 3 :z 1}) => 4
			(distance/between origin {:x -3 :y -2 :z 5}) => 5
			)
		)
	)