(ns clj-hex-grid.pointy-hex)

(defn height
	([{size :size}] (* size 2))
	([] (height {:size 1})))

(defn width 
	([{size :size}] (* (/ (Math/sqrt 3) 2) (height {:size size})))
	([] (width {:size 1})))

(defn horizontal-distance-to-adjacent 
	([]	(horizontal-distance-to-adjacent {:size 1}))
	([attrs] (double (* 0.75 (width attrs)))))

(defn vertical-distance-to-adjacent 
	([]	(vertical-distance-to-adjacent {:size 1}))
	([attrs] (* 0.75 (height attrs))))
