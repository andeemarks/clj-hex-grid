(ns clj-hex-grid.core)

(defn width 
	([{size :size}] (* size 2))
	([] 2))

(defn distance-to-adjacent 
	([]	(double (* (/ 3 4) (width))))
	([attrs] (double (* (/ 3 4) (width attrs)))))
