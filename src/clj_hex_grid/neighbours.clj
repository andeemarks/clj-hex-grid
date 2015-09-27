(ns clj-hex-grid.neighbours)

(def cube_neighbours {:north {:x 0 :y 1 :z -1} 
											:south {:x 0 :y -1 :z 1}
											:northeast {:x 1 :y 0 :z -1}
											:northwest {:x -1 :y 1 :z 0}
											:southeast {:x 1 :y -1 :z 0}
											:southwest {:x -1 :y 0 :z 1}
										})

(def odd_column 1)
(def even_column 0)

(def odd_q_neighbours {even_column 
												{	:north {:x 0 :y -1} 
													:south {:x 0 :y 1}
													:northeast {:x 1 :y -1}
													:northwest {:x -1 :y -1}
													:southeast {:x 1 :y 0}
													:southwest {:x -1 :y 0}}
											odd_column 
												{	:north {:x 0 :y -1} 	
													:south {:x 0 :y 1}
													:northeast {:x 1 :y -1}
													:northwest {:x -1 :y 0}
													:southeast {:x 1 :y 1}
													:southwest {:x -1 :y 1}}
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
	(let [parity (bit-and x 1)
				offsets (get (get odd_q_neighbours parity) orientation)]
		; (println offsets)
		{	:x (+ x (get offsets :x)) 
			:y (+ y (get offsets :y))}))
