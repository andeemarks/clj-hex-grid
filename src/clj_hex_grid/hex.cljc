(ns clj-hex-grid.hex)

(defn calc-corner-x [center size angle_rad]
  (+ (get center :x) (* (or size 1) (Math/cos angle_rad))))

(defn calc-corner-y [center size angle_rad]
  (+ (get center :y) (* (or size 1) (Math/sin angle_rad))))

(defn calc-hex-corner
  [center corner size offset]
  {:pre [(some? center)
         (some? corner)
         (<= 0 corner 5)]}
  (let [angle_deg (+ offset (* corner 60))
        angle_rad (* (/ Math/PI 180) angle_deg)
        corner_x (calc-corner-x center size angle_rad)
        corner_y (calc-corner-y center size angle_rad)]
    {:x corner_x :y corner_y}))