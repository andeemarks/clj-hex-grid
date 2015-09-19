(ns clj-hex-grid.pointy-test
	(:require [clj-hex-grid.pointy-hex :as pointy])
  (:use [midje.sweet]))

(facts "for pointy-topped hexen"

		(facts "when calculating widths"
			(fact "default size is 1"
				(pointy/width) => (Math/sqrt 3))
			(fact "width is the height multiplied by half the square root of 3"
				(pointy/width {:size 1}) => (Math/sqrt 3)
				(pointy/width {:size 2}) => (* 2 (Math/sqrt 3))))

		(facts "when calculating heights"
			(fact "default size is 1"
				(pointy/height) => 2)
			(fact "height is twice the size"
				(pointy/height {:size 1}) => 2
				(pointy/height {:size 2}) => 4))


	(facts "when calculating distances"
		(fact "horizontal-distance between adjacent hexes is same as width"
			(pointy/horizontal-distance-to-adjacent) => (pointy/width)
			(pointy/horizontal-distance-to-adjacent {:size 1}) => (pointy/width))

		(fact "vertical-distance between adjacent hexes is 0.75 of the height"
			(pointy/vertical-distance-to-adjacent {:size 2}) => 3.0
			(pointy/vertical-distance-to-adjacent) => 1.5))
		
)
