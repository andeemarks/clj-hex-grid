(ns clj-hex-grid.core-test
	(:require [clj-hex-grid.core :as hex])
  (:use [midje.sweet]))

(facts "about hex sizing..."

	(fact "default width is 1"
		(hex/width) => 2)
	(fact "width is twice the size"
		(hex/width {:size 1}) => 2
		(hex/width {:size 2}) => 4))

(facts "about hex spacing..."
	(fact "distance between adjacent hexes is 3/4 of width"
		(hex/distance-to-adjacent) => (* 2 0.75)
		(hex/distance-to-adjacent {:size 1}) => (* 2 0.75))
	)
