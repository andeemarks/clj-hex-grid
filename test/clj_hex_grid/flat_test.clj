(ns clj-hex-grid.flat-test
	(:require [clj-hex-grid.flat-hex :as flat])
	(:require [clojure.pprint :as pp])
  (:use [midje.sweet]))

(facts "for flat-topped hexen"

	(facts "when generating all corners"
		(fact "all corners are returned"
			(let [corners (flat/hex_corners {:center {:x 10 :y 10} :size 5})
						_ (pp/pprint corners)]
				(get-in corners [:corners 0]) => truthy
				(get-in corners [:corners 1]) => truthy
				(get-in corners [:corners 2]) => truthy
				(get-in corners [:corners 3]) => truthy
				(get-in corners [:corners 4]) => truthy
				(get-in corners [:corners 5]) => truthy)))

	(facts "when calculating corner coordinates"
		(fact "default size is 1"
			(flat/hex_corner {:center {:x 1 :y 1} :corner 1}) => (flat/hex_corner {:center {:x 1 :y 1} :size 1 :corner 1})
			(flat/hex_corner {:center {:x 1 :y 1} :corner 1}) =not=> (flat/hex_corner {:center {:x 1 :y 1} :size 2 :corner 1}))
		(fact "corner must be specified"
			(flat/hex_corner {:center {:x 1 :y 1}}) => (throws Exception))
		(fact "corner must be in valid range"
			(flat/hex_corner {:center {:x 1 :y 1} :corner -1}) => (throws Exception)
			(flat/hex_corner {:center {:x 1 :y 1} :corner 0}) => truthy
			(flat/hex_corner {:center {:x 1 :y 1} :corner 1}) => truthy
			(flat/hex_corner {:center {:x 1 :y 1} :corner 2}) => truthy
			(flat/hex_corner {:center {:x 1 :y 1} :corner 3}) => truthy
			(flat/hex_corner {:center {:x 1 :y 1} :corner 4}) => truthy
			(flat/hex_corner {:center {:x 1 :y 1} :corner 5}) => truthy
			(flat/hex_corner {:center {:x 1 :y 1} :corner 6}) => (throws Exception)
			)
		(fact "center must be specified"
			(flat/hex_corner {:corner 1}) => (throws Exception))
		(fact "coordinates contain x and y"
			(get (flat/hex_corner {:center {:x 1 :y 1} :corner 1}) :x) => (roughly 2 1)
			(get (flat/hex_corner {:center {:x 1 :y 1} :corner 1}) :y) => (roughly 2 1)))

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
