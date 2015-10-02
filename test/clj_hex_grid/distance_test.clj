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
		))