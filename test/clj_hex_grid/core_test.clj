(ns clj-hex-grid.core-test
	(:require [clj-hex-grid.core :as hex])
  (:use [midje.sweet]))

(facts "for flat-topped hexen"
		(facts "when calculating widths"
			(fact "default size is 1"
				(hex/width) => 2)
			(fact "width is twice the size"
				(hex/width {:size 1}) => 2
				(hex/width {:size 2}) => 4))

		(facts "when calculating heights"
			(fact "default size is 1"
				(hex/height) => (Math/sqrt 3))
			(fact "height is the width multiplied by half the square root of 3"
				(hex/height {:size 1}) => (Math/sqrt 3)
				(hex/height {:size 2}) => (* 2 (Math/sqrt 3))))


	(facts "about hex spacing"
		(fact "horizontal-distance between adjacent hexes is 3/4 of width"
			(hex/distance-to-adjacent) => (* 2 0.75)
			(hex/distance-to-adjacent {:size 1}) => (* 2 0.75))
		)
)
