(ns clj-hex-grid.coordinate)

(defn cube_to_offset_odd_q 
	"z + (x - (x&1)) / 2"
	[{x :x y :y z :z}]
	{:col x :row (+ z (/ (- x (bit-and x 1)) 2))})

(defn cube_to_offset_even_q 
	"z + (x + (x&1)) / 2"
	[{x :x y :y z :z}]
	{:col x :row (+ z (/ (+ x (bit-and x 1)) 2))})

(defn cube_to_offset_even_r 
	"x + (z + (z&1)) / 2"
	[{x :x y :y z :z}]
	{:row z :col (+ x (/ (+ z (bit-and z 1)) 2))})

(defn cube_to_offset_odd_r 
	"col => x + (z - (z&1)) / 2"
	[{x :x y :y z :z}]
	{:row z :col (+ x (/ (- z (bit-and z 1)) 2))})

(defn offset_even_q_to_cube 
	"z => row - (col + (col&1)) / 2
	y => -x-z
	x => col"
	[{row :row col :col}]
	(let [x col
				z (- row (/ (+ col (bit-and col 1)) 2))
				y (- (- 0 x) z)]
		{:x x :y y :z z}))