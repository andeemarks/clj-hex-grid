(ns clj-hex-grid.distance)

(defn between
	"max(abs(a.x - b.x), abs(a.y - b.y), abs(a.z - b.z))"
	[{origin_x :x origin_y :y origin_z :z} {dest_x :x dest_y :y dest_z :z}]
	(let [x_delta (Math/abs (- origin_x dest_x))
				y_delta (Math/abs (- origin_y dest_y))
				z_delta (Math/abs (- origin_z dest_z))]
		(max x_delta y_delta z_delta)))