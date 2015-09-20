(ns clj-hex-grid.pointy-test
	(:require [clj-hex-grid.pointy-hex :as pointy])
	(:require [clojure.pprint :as pp])
  (:use [midje.sweet]))

(facts "for pointy-topped hexen"

	(facts "when generating all corners"
		(fact "all corners are returned"
			(let [corners (pointy/hex_corners {:center {:x 10 :y 10} :size 5})
						_ (pp/pprint corners)]
				(get corners :type) => :pointy
				(get corners :size) => 5
				(get corners :center) => {:x 10 :y 10}
				(get-in corners [:corners 0]) => truthy
				(get-in corners [:corners 1]) => truthy
				(get-in corners [:corners 2]) => truthy
				(get-in corners [:corners 3]) => truthy
				(get-in corners [:corners 4]) => truthy
				(get-in corners [:corners 5]) => truthy)))

	(facts "when calculating corner coordinates"
		(fact "default size is 1"
			(pointy/hex_corner {:center {:x 1 :y 1} :corner 1}) => (pointy/hex_corner {:center {:x 1 :y 1} :size 1 :corner 1})
			(pointy/hex_corner {:center {:x 1 :y 1} :corner 1}) =not=> (pointy/hex_corner {:center {:x 1 :y 1} :size 2 :corner 1}))
		(fact "corner must be specified"
			(pointy/hex_corner {:center {:x 1 :y 1}}) => (throws Exception))
		(fact "corner must be in valid range"
			(pointy/hex_corner {:center {:x 1 :y 1} :corner -1}) => (throws Exception)
			(pointy/hex_corner {:center {:x 1 :y 1} :corner 0}) => truthy
			(pointy/hex_corner {:center {:x 1 :y 1} :corner 1}) => truthy
			(pointy/hex_corner {:center {:x 1 :y 1} :corner 2}) => truthy
			(pointy/hex_corner {:center {:x 1 :y 1} :corner 3}) => truthy
			(pointy/hex_corner {:center {:x 1 :y 1} :corner 4}) => truthy
			(pointy/hex_corner {:center {:x 1 :y 1} :corner 5}) => truthy
			(pointy/hex_corner {:center {:x 1 :y 1} :corner 6}) => (throws Exception)
			)
		(fact "center must be specified"
			(pointy/hex_corner {:corner 1}) => (throws Exception))
		(fact "coordinates contain x and y"
			(get (pointy/hex_corner {:center {:x 1 :y 1} :corner 1}) :x) => (roughly 2 1)
			(get (pointy/hex_corner {:center {:x 1 :y 1} :corner 1}) :y) => (roughly 2 1)))

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
