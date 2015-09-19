(ns clj-hex-grid.pointy-hex)

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

(defn- calc_corner_x [center size angle_rad]
	(+ (get center :x) (* (or size 1) (Math/cos angle_rad))))

(defn- calc_corner_y [center size angle_rad]
	(+ (get center :y) (* (or size 1) (Math/sin angle_rad))))

(defn- calc_hex_corner 
	[center corner size]
	(let [angle_deg (+ 30 (* corner 60))
				angle_rad (/ Math/PI (* 180 angle_deg))
				corner_x (calc_corner_x center size angle_rad)
				corner_y (calc_corner_x center size angle_rad)]
				{:x corner_x :y corner_y}))

(defn hex_corner 
	[{center :center corner :corner size :size}]
	(if (or (nil? center) (nil? corner))
		(throw Exception))
	(if (or (< corner 0) (> corner 5))
		(throw Exception))
	(calc_hex_corner center corner size))
