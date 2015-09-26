(ns clj-hex-grid.coordinate)

(defn cube_to_offset_even_q [{x :x y :y z :z}]
	{:col x :row "1"})