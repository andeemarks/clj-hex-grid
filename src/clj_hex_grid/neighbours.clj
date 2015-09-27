(ns clj-hex-grid.neighbours)

(defn neighbour_for
	""
	[{x :x y :y z :z} orientation]
	{:x 0 :y 1 :z -1})