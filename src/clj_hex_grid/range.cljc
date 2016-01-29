(ns clj-hex-grid.range
  (require [clj-hex-grid.neighbours :as n]))

(defn- map_over_y
  "var dz = -dx-dy
   results.append(cube_add(center, Cube(dx, dy, dz)))"
  [dx dy origin]
  (let [dz (- (- dx) dy)]
    (n/offset_cube_coordinates_from_origin origin {:x dx :y dy :z dz})))

(defn- range_over_y
  [^long dx ^long distance]
  (range
    (Math/max (- distance) (- (- dx) distance))
    (+ 1 (Math/min distance (+ (- dx) distance)))))

(defn- map_over_x
  [dx origin distance]
  (map #(map_over_y dx %1 origin)
       (range_over_y dx distance)))

(defn- range_over_x
  [distance]
  (range (- distance) (+ 1 distance)))

(defn range-from
  "var results = []
   for each -N ≤ dx ≤ N:
     for each max(-N, -dx-N) ≤ dy ≤ min(N, -dx+N):
       var dz = -dx-dy
       results.append(cube_add(center, Cube(dx, dy, dz)))"
  [origin distance]
  (flatten (map #(map_over_x %1 origin distance)
                (range_over_x distance))))