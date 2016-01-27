(ns clj-hex-grid.distance
  (require [clj-hex-grid.coordinate :as c]))

(defn cube_between
  "max(abs(a.x - b.x), abs(a.y - b.y), abs(a.z - b.z))"
  [{origin_x :x origin_y :y origin_z :z} {dest_x :x dest_y :y dest_z :z}]
  (let [x_delta (Math/abs (- origin_x dest_x))
        y_delta (Math/abs (- origin_y dest_y))
        z_delta (Math/abs (- origin_z dest_z))]
    (max x_delta y_delta z_delta)))

(defn even_q_between
  "var ac = offset_to_cube(a)
   var bc = offset_to_cube(b)
   return cube_distance(ac, bc)"
  [{origin_col :x origin_row :y} {dest_col :x dest_row :y}]
  (let [origin_cube (c/offset_even_q_to_cube {:row origin_row :col origin_col})
        dest_cube (c/offset_even_q_to_cube {:row dest_row :col dest_col})]
    (cube_between origin_cube dest_cube)))

(defn odd_q_between
  "var ac = offset_to_cube(a)
   var bc = offset_to_cube(b)
   return cube_distance(ac, bc)"
  [{origin_col :x origin_row :y} {dest_col :x dest_row :y}]
  (let [origin_cube (c/offset_odd_q_to_cube {:row origin_row :col origin_col})
        dest_cube (c/offset_odd_q_to_cube {:row dest_row :col dest_col})]
    (cube_between origin_cube dest_cube)))

(defn even_r_between
  "var ac = offset_to_cube(a)
   var bc = offset_to_cube(b)
   return cube_distance(ac, bc)"
  [{origin_col :x origin_row :y} {dest_col :x dest_row :y}]
  (let [origin_cube (c/offset_even_r_to_cube {:row origin_row :col origin_col})
        dest_cube (c/offset_even_r_to_cube {:row dest_row :col dest_col})]
    (cube_between origin_cube dest_cube)))

(defn odd_r_between
  "var ac = offset_to_cube(a)
   var bc = offset_to_cube(b)
   return cube_distance(ac, bc)"
  [{origin_col :x origin_row :y} {dest_col :x dest_row :y}]
  (let [origin_cube (c/offset_odd_r_to_cube {:row origin_row :col origin_col})
        dest_cube (c/offset_odd_r_to_cube {:row dest_row :col dest_col})]
    (cube_between origin_cube dest_cube)))