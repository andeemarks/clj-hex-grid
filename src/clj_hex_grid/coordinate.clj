(ns clj-hex-grid.coordinate)

(defn cube_to_offset_odd_q [{x :x y :y z :z}]
	{:col x :row "1"})

(defn cube_to_offset_even_q [{x :x y :y z :z}]
	{:col x :row "1"})

(defn cube_to_offset_even_r [{x :x y :y z :z}]
	{:row z})

(defn cube_to_offset_odd_r [{x :x y :y z :z}]
	{:row z})
