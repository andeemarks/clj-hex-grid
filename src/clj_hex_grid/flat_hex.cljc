(ns clj-hex-grid.flat-hex
  (:require [clj-hex-grid.hex :as hex]))

(defn width
  ([{size :size}] (* size 2))
  ([] (width {:size 1})))

(defn height
  ([{size :size}] (* (/ (Math/sqrt 3) 2) (width {:size size})))
  ([] (height {:size 1})))

(defn horizontal-distance-to-adjacent
  ([] (horizontal-distance-to-adjacent {:size 1}))
  ([attrs] (double (* 0.75 (width attrs)))))

(defn vertical-distance-to-adjacent
  ([] (vertical-distance-to-adjacent {:size 1}))
  ([attrs] (height attrs)))

(defn hex_corner
  [{center :center corner :corner size :size}]
  (hex/calc_hex_corner center corner size 0))

(defn hex [attrs]
  {:type    :flat
   :center  (get attrs :center)
   :size    (get attrs :size)
   :corners {0 (hex_corner (assoc attrs :corner 0))
             1 (hex_corner (assoc attrs :corner 1))
             2 (hex_corner (assoc attrs :corner 2))
             3 (hex_corner (assoc attrs :corner 3))
             4 (hex_corner (assoc attrs :corner 4))
             5 (hex_corner (assoc attrs :corner 5))}})
