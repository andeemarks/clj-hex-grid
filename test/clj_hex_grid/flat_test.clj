(ns clj-hex-grid.flat-test
  (:require [clj-hex-grid.flat-hex :as flat])
  (:use [midje.sweet]))

(facts "for flat-topped hexen"

       (facts "when building a flat hex"
              (fact "all attributes are returned"
                    (let [flat_hex (flat/hex {:center {:x 10 :y 10} :size 5})]
                      (get flat_hex :type) => :flat
                      (get flat_hex :size) => 5
                      (get flat_hex :center) => {:x 10 :y 10}
                      (get-in flat_hex [:corners 0]) => truthy
                      (get-in flat_hex [:corners 1]) => truthy
                      (get-in flat_hex [:corners 2]) => truthy
                      (get-in flat_hex [:corners 3]) => truthy
                      (get-in flat_hex [:corners 4]) => truthy
                      (get-in flat_hex [:corners 5]) => truthy)))

       (facts "when calculating corner coordinates"
              (fact "default size is 1"
                    (flat/hex-corner {:center {:x 1 :y 1} :corner 1}) => (flat/hex-corner {:center {:x 1 :y 1} :size 1 :corner 1})
                    (flat/hex-corner {:center {:x 1 :y 1} :corner 1}) =not=> (flat/hex-corner {:center {:x 1 :y 1} :size 2 :corner 1}))
              (fact "corner must be specified"
                    (flat/hex-corner {:center {:x 1 :y 1}}) => (throws AssertionError))
              (fact "corner must be in valid range"
                    (flat/hex-corner {:center {:x 1 :y 1} :corner -1}) => (throws AssertionError)
                    (flat/hex-corner {:center {:x 1 :y 1} :corner 0}) => truthy
                    (flat/hex-corner {:center {:x 1 :y 1} :corner 1}) => truthy
                    (flat/hex-corner {:center {:x 1 :y 1} :corner 2}) => truthy
                    (flat/hex-corner {:center {:x 1 :y 1} :corner 3}) => truthy
                    (flat/hex-corner {:center {:x 1 :y 1} :corner 4}) => truthy
                    (flat/hex-corner {:center {:x 1 :y 1} :corner 5}) => truthy
                    (flat/hex-corner {:center {:x 1 :y 1} :corner 6}) => (throws AssertionError))
              (fact "center must be specified"
                    (flat/hex-corner {:corner 1}) => (throws AssertionError))
              (fact "coordinates contain x and y"
                    (get (flat/hex-corner {:center {:x 1 :y 1} :corner 1}) :x) => (roughly 2 1)
                    (get (flat/hex-corner {:center {:x 1 :y 1} :corner 1}) :y) => (roughly 2 1)))

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
             (flat/vertical-distance-to-adjacent) => (flat/height)))
