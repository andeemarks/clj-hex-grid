(ns clj-hex-grid.core)

(defn width 
	([{size :size}] (* size 2))
	([] 2))