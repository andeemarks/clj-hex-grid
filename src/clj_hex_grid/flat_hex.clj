(ns clj-hex-grid.flat-hex
	(:require [clj-hex-grid.hex :as hex]))

(defn width 
	([{size :size}] (* size 2))
	([] (width {:size 1})))

(defn height
	([{size :size}] (* (/ (Math/sqrt 3) 2) (width {:size size})))
	([] (height {:size 1})))

(defn horizontal-distance-to-adjacent 
	([]	(horizontal-distance-to-adjacent {:size 1}))
	([attrs] (double (* 0.75 (width attrs)))))

(defn vertical-distance-to-adjacent 
	([]	(vertical-distance-to-adjacent {:size 1}))
	([attrs] (height attrs)))

(defn hex_corner 
	[{center :center corner :corner size :size}]
	(hex/calc_hex_corner center corner size 0))
