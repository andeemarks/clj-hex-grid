(ns clj-hex-grid.path-test
	(:require [clj-hex-grid.path :as path])
  (:use [midje.sweet]))

(def cube_origin {:x 0 :y 0 :z 0})

(facts "when calculating paths"
	(fact "only one element in path from a point to itself"
		(path/path_between cube_origin cube_origin) => (just cube_origin))
	)
