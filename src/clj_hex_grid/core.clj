(ns clj-hex-grid.core)

(defn width 
	([{size :size}] (* size 2))
	([] (width {:size 1})))

(defn height
	([{size :size}] (* (/ (Math/sqrt 3) 2) (width {:size size})))
	([] (height {:size 1})))

(defn horizontal-distance-to-adjacent 
	([]	(horizontal-distance-to-adjacent {:size 1}))
	([attrs] (double (* (/ 3 4) (width attrs)))))

(defn vertical-distance-to-adjacent 
	([]	(vertical-distance-to-adjacent {:size 1}))
	([attrs] (height attrs)))
