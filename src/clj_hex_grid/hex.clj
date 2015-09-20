(ns clj-hex-grid.hex)

(defn calc_corner_x [center size angle_rad]
	(+ (get center :x) (* (or size 1) (Math/cos angle_rad))))

(defn calc_corner_y [center size angle_rad]
	(+ (get center :y) (* (or size 1) (Math/sin angle_rad))))

(defn calc_hex_corner 
	[center corner size offset]
	(if (or (nil? center) (nil? corner))
		(throw Exception))
	(if (or (< corner 0) (> corner 5))
		(throw Exception))

	(let [angle_deg (+ offset (* corner 60))
				angle_rad (* (/ Math/PI 180) angle_deg)
				corner_x (calc_corner_x center size angle_rad)
				corner_y (calc_corner_y center size angle_rad)]
				{:x corner_x :y corner_y}))