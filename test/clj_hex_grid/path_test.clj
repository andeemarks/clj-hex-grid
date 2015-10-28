(ns clj-hex-grid.path-test
	(:require [clj-hex-grid.path :as path])
	(:require [clj-hex-grid.neighbours :as n])
  (:use [midje.sweet]))

(def cube_origin {:x 0 :y 0 :z 0})

(facts "when calculating paths"
	(fact "two elements in path fom a point to a neighbour"
		(let [neighbours (n/neighbours_for_cube cube_origin)
			  path (path/path_between cube_origin (first neighbours))]
			(count path) => 2
			(first path) => cube_origin
			(second path) => (first neighbours)))
	(fact "only one element in path from a point to itself"
		(let [path (path/path_between cube_origin cube_origin)]
			(count path) => 1
			path => (contains cube_origin)))
	)
