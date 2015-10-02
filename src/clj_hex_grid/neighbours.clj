(ns clj-hex-grid.neighbours)

(def flat_neighbour_orientations [:north :south :northeast :northwest :southeast :southwest])
(def pointy_neighbour_orientations [:west :east :northeast :northwest :southeast :southwest])

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

(def even_q_neighbours {even_column 
												{	:north {:x 0 :y -1} 
													:south {:x 0 :y 1}
													:northeast {:x 1 :y 0}
													:northwest {:x -1 :y 0}
													:southeast {:x 1 :y 1}
													:southwest {:x -1 :y 1}}
											odd_column 
												{	:north {:x 0 :y -1} 	
													:south {:x 0 :y 1}
													:northeast {:x 1 :y -1}
													:northwest {:x -1 :y -1}
													:southeast {:x 1 :y 0}
													:southwest {:x -1 :y 0}}
										})

(def odd_r_neighbours {even_column 
												{	:northeast {:x 0 :y -1} 
													:east {:x 1 :y 0}
													:southeast {:x 0 :y 1}
													:southwest {:x -1 :y 1}
													:west {:x -1 :y 0}
													:northwest {:x -1 :y -1}}
											odd_column 
												{	:northeast {:x 1 :y -1} 
													:east {:x 1 :y 0}
													:southeast {:x 1 :y 1}
													:southwest {:x 0 :y 1}
													:west {:x -1 :y 0}
													:northwest {:x 0 :y -1}}
										})

(def even_r_neighbours {even_column 
												{	:northeast {:x 1 :y -1} 
													:east {:x 1 :y 0}
													:southeast {:x 1 :y 1}
													:southwest {:x 0 :y -1}
													:west {:x -1 :y 0}
													:northwest {:x 0 :y -1}}
											odd_column 
												{	:northeast {:x 0 :y -1} 
													:east {:x 1 :y 0}
													:southeast {:x 0 :y 1}
													:southwest {:x -1 :y 1}
													:west {:x -1 :y 0}
													:northwest {:x -1 :y -1}}
										})

(defn- offset_cube_coordinates_from_origin
	""
	[{origin_x :x origin_y :y origin_z :z} offsets]
	{	:x (+ origin_x (get offsets :x)) 
		:y (+ origin_y (get offsets :y)) 
		:z (+ origin_z (get offsets :z))})

(defn neighbour_for_cube
	""
	[origin orientation]
	(let [offsets (get cube_neighbours orientation)]
		(offset_cube_coordinates_from_origin origin offsets)))

(defn neighbours_for_cube
	""
	[origin]
	(map #(neighbour_for_cube origin %1) flat_neighbour_orientations))

(defn- offset_offset_coordinates_from_origin
	""
	[{origin_x :x origin_y :y} offsets]
	{	:x (+ origin_x (get offsets :x)) 
		:y (+ origin_y (get offsets :y))})

(defn neighbour_for_odd_q
	""
	[origin orientation]
	(let [parity (bit-and (get origin :x) 1)
				offsets (get (get odd_q_neighbours parity) orientation)]
		(offset_offset_coordinates_from_origin origin offsets)))

(defn neighbours_for_odd_q
	""
	[origin]
	(map #(neighbour_for_odd_q origin %1) flat_neighbour_orientations))

(defn neighbour_for_even_q
	""
	[origin orientation]
	(let [parity (bit-and (get origin :x) 1)
				offsets (get (get even_q_neighbours parity) orientation)]
		(offset_offset_coordinates_from_origin origin offsets)))

(defn neighbours_for_even_q
	""
	[origin]
	(map #(neighbour_for_even_q origin %1) flat_neighbour_orientations))

(defn neighbour_for_odd_r
	""
	[origin orientation]
	(let [parity (bit-and (get origin :y) 1)
				offsets (get (get odd_r_neighbours parity) orientation)]
		(offset_offset_coordinates_from_origin origin offsets)))

(defn neighbours_for_odd_r
	""
	[origin]
	(map #(neighbour_for_odd_r origin %1) pointy_neighbour_orientations))

(defn neighbour_for_even_r
	""
	[origin orientation]
	(let [parity (bit-and (get origin :y) 1)
				offsets (get (get even_r_neighbours parity) orientation)]
		(offset_offset_coordinates_from_origin origin offsets)))

(defn neighbours_for_even_r
	""
	[origin]
	(map #(neighbour_for_even_r origin %1) pointy_neighbour_orientations))
