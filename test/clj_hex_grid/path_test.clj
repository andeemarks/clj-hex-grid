(ns clj-hex-grid.path-test
	(:require [clj-hex-grid.path :as path])
	(:require [clj-hex-grid.neighbours :as n])
  (:use [midje.sweet]))

(def cube_origin {:x 0 :y 0 :z 0})

(facts "when calculating paths"
	(fact "two elements in path fom a point to a neighbour"
		(let [neighbours (n/neighbours_for_cube cube_origin)
			  destination (first neighbours)
			  path (path/path_between cube_origin destination)]
			(count path) => 2
			(first path) => cube_origin
			(second path) => destination))
	(fact "only expected elements in longer horizontal path"
		(let [destination {:x -5 :y 5 :z 0}
			  path (path/path_between cube_origin destination)]
			(count path) => 6
			(nth path 0) => cube_origin
			(nth path 1) => {:x -1 :y 1 :z 0}
			(nth path 2) => {:x -2 :y 2 :z 0}
			(nth path 3) => {:x -3 :y 3 :z 0}
			(nth path 4) => {:x -4 :y 4 :z 0}
			(nth path 5) => destination))
	(fact "only one element in path from a point to itself"
		(let [path (path/path_between cube_origin cube_origin)]
			(count path) => 1
			path => (contains cube_origin)))
	)
