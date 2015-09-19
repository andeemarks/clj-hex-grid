(ns clj-hex-grid.pointy-hex
	(:require [clj-hex-grid.hex :as hex]))

(defn height
	([{size :size}] (* size 2))
	([] (height {:size 1})))

(defn width 
	([{size :size}] (* (/ (Math/sqrt 3) 2) (height {:size size})))
	([] (width {:size 1})))

(defn horizontal-distance-to-adjacent 
	([]	(horizontal-distance-to-adjacent {:size 1}))
	([attrs] (double (width attrs))))

(defn vertical-distance-to-adjacent 
	([]	(vertical-distance-to-adjacent {:size 1}))
	([attrs] (* 0.75 (height attrs))))

(defn hex_corner 
	[{center :center corner :corner size :size}]
	(hex/calc_hex_corner center corner size 30))

(defn hex_corners [attrs]
	{0 (hex_corner (assoc attrs :corner 0))
	 1 (hex_corner (assoc attrs :corner 1))	
	 2 (hex_corner (assoc attrs :corner 2))	
	 3 (hex_corner (assoc attrs :corner 3))	
	 4 (hex_corner (assoc attrs :corner 4))	
	 5 (hex_corner (assoc attrs :corner 5))	
	 })
