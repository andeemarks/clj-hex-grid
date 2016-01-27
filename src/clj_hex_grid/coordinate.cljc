(ns clj-hex-grid.coordinate)

(defn cube->offset-odd-q
  "row => z + (x - (x&1)) / 2"
  [{x :x _ :y z :z}]
  {:col x :row (+ z (/ (- x (bit-and x 1)) 2))})

(defn cube->offset-even-q
  "row => z + (x + (x&1)) / 2"
  [{x :x _ :y z :z}]
  {:col x :row (+ z (/ (+ x (bit-and x 1)) 2))})

(defn cube->offset-even-r
  "col => x + (z + (z&1)) / 2"
  [{x :x _ :y z :z}]
  {:row z :col (+ x (/ (+ z (bit-and z 1)) 2))})

(defn cube->offset-odd-r
  "col => x + (z - (z&1)) / 2"
  [{x :x _ :y z :z}]
  {:row z :col (+ x (/ (- z (bit-and z 1)) 2))})

(defn offset-even-q->cube
  "z => row - (col + (col&1)) / 2
   y => -x-z
   x => col"
  [{row :row col :col}]
  (let [x col
        z (- row (/ (+ col (bit-and col 1)) 2))
        y (- (- 0 x) z)]
    {:x x :y y :z z}))

(defn offset-odd-q->cube
  "x => col
   z => row - (col - (col&1)) / 2
   y => -x-z"
  [{row :row col :col}]
  (let [x col
        z (- row (/ (- col (bit-and col 1)) 2))
        y (- (- 0 x) z)]
    {:x x :y y :z z}))

(defn offset-even-r->cube
  "x => col - (row + (row&1)) / 2
   z => row
   y => -x-z"
  [{row :row col :col}]
  (let [x (- col (/ (+ row (bit-and row 1)) 2))
        z row
        y (- (- 0 x) z)]
    {:x x :y y :z z}))

(defn offset-odd-r->cube
  "x => col - (row - (row&1)) / 2
   z => row
   y => -x-z"
  [{row :row col :col}]
  (let [x (- col (/ (- row (bit-and row 1)) 2))
        z row
        y (- (- 0 x) z)]
    {:x x :y y :z z}))
