(ns clj-hex-grid.flat-test
	(:require [clj-hex-grid.flat-hex :as flat])
  (:use [midje.sweet]))

(facts "for flat-topped hexen"

		(facts "when calculating widths"
			(fact "default size is 1"
				(flat/width) => 2)
			(fact "width is twice the size"
				(flat/width {:size 1}) => 2
				(flat/width {:size 2}) => 4))

		(facts "when calculating heights"
			(fact "default size is 1"
				(flat/height) => (Math/sqrt 3))
			(fact "height is the width multiplied by half the square root of 3"
				(flat/height {:size 1}) => (Math/sqrt 3)
				(flat/height {:size 2}) => (* 2 (Math/sqrt 3))))


	(facts "when calculating distances"
		(fact "horizontal-distance between adjacent hexes is 3/4 of width"
			(flat/horizontal-distance-to-adjacent) => (* 2 0.75)
			(flat/horizontal-distance-to-adjacent {:size 1}) => (* 2 0.75)))

		(fact "vertical-distance between adjacent hexes is same as height"
			(flat/vertical-distance-to-adjacent {:size 2}) => (* 2 (flat/height))			
			(flat/vertical-distance-to-adjacent) => (flat/height))
		
)
