(ns clj-hex-grid.neighbours)

(def cube_neighbours {:north {:x 0 :y 1 :z -1} 
											:south {:x 0 :y -1 :z 1}
											:northeast {:x 1 :y 0 :z -1}
											:northwest {:x -1 :y 1 :z 0}
											:southeast {:x 1 :y -1 :z 0}
											:southwest {:x -1 :y 0 :z 1}
										})

(def odd_q_neighbours {:north {:x 0 :y -1} 
											:south {:x 0 :y 1}
											:northeast {:x 1 :y -1}
											:northwest {:x -1 :y -1}
											:southeast {:x 1 :y 0}
											:southwest {:x -1 :y 0}
										})

(defn neighbour_for_cube
	""
	[{x :x y :y z :z} orientation]
	(let [offsets (get cube_neighbours orientation)]
		; (println offsets)
		{	:x (+ x (get offsets :x)) 
			:y (+ y (get offsets :y)) 
			:z (+ z (get offsets :z))}))

(defn neighbour_for_odd_q
	""
	[{x :x y :y} orientation]
	(let [offsets (get odd_q_neighbours orientation)]
		; (println offsets)
		{	:x (+ x (get offsets :x)) 
			:y (+ y (get offsets :y))}))
