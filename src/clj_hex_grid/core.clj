(ns clj-hex-grid.core)

(defn width 
	([{size :size}] (* size 2))
	([] (width {:size 1})))

(defn distance-to-adjacent 
	([]	(distance-to-adjacent {:size 1}))
	([attrs] (double (* (/ 3 4) (width attrs)))))
