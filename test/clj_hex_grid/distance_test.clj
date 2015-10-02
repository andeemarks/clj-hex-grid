(ns clj-hex-grid.distance-test
	(:require [clj-hex-grid.distance :as distance])
  (:use [midje.sweet]))

(def origin {:x 0 :y 0 :z 0})

(facts "when determining distance"
	(facts "for cube coordinates"
		(fact "all neighbours are distance 1"
			(distance/between origin {:x 1 :y 0 :z -1}) => 1)))