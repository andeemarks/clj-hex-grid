(ns clj-hex-grid.core)

(defn width [attrs]
	(* (get attrs :size) 2))
