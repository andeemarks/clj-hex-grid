(ns clj-hex-grid.coordinate)

(defn cube_to_offset_odd_q [{x :x y :y z :z}]
	; z + (x - (x&1)) / 2
	{:col x :row (+ z (/ (- x (bit-and x 1)) 2))})

(defn cube_to_offset_even_q [{x :x y :y z :z}]
	; z + (x + (x&1)) / 2
	{:col x :row (+ z (/ (+ x (bit-and x 1)) 2))})

(defn cube_to_offset_even_r [{x :x y :y z :z}]
	; x + (z + (z&1)) / 2
	{:row z :col (+ x (/ (+ z (bit-and z 1)) 2))})

(defn cube_to_offset_odd_r [{x :x y :y z :z}]
	; x + (z - (z&1)) / 2
	{:row z :col (+ x (/ (- z (bit-and z 1)) 2))})
