(ns clj-hex-grid.distance-test
	(:require [clj-hex-grid.distance :as distance])
	(:require [clj-hex-grid.neighbours :as n])
	(:require [clojure.pprint :as pp])
  (:use [midje.sweet]))

(def cube_origin {:x 0 :y 0 :z 0})
(def offset_origin {:x 0 :y 0})

(facts "when determining distance"
	(future-facts "for cube coordinates"
		(fact "all neighbours are distance 1"
			(let [neighbours (n/neighbours_for_cube cube_origin)]
				(distance/cube_between cube_origin (nth neighbours 0)) => 1
				(distance/cube_between cube_origin (nth neighbours 1)) => 1
				(distance/cube_between cube_origin (nth neighbours 2)) => 1
				(distance/cube_between cube_origin (nth neighbours 3)) => 1
				(distance/cube_between cube_origin (nth neighbours 4)) => 1
				(distance/cube_between cube_origin (nth neighbours 5)) => 1))
		(fact "no distance between a point and itself"
			(distance/cube_between cube_origin cube_origin) => 0)
		(fact "other distances are also calculated correctly"
			(distance/cube_between cube_origin {:x 2 :y 0 :z -2}) => 2
			(distance/cube_between cube_origin {:x 1 :y 2 :z -3}) => 3
			(distance/cube_between cube_origin {:x -4 :y 3 :z 1}) => 4
			(distance/cube_between cube_origin {:x -3 :y -2 :z 5}) => 5
			)
		)

	(facts "for offset coordinates"
		(fact "for even_q coordinates"
			(fact "all neighbours are distance 1"
				(let [neighbours (n/neighbours_for_even_q offset_origin)]
					(distance/even_q_between offset_origin (nth neighbours 0)) => 1
					(distance/even_q_between offset_origin (nth neighbours 1)) => 1
					(distance/even_q_between offset_origin (nth neighbours 2)) => 1
					(distance/even_q_between offset_origin (nth neighbours 3)) => 1
					(distance/even_q_between offset_origin (nth neighbours 4)) => 1
					(distance/even_q_between offset_origin (nth neighbours 5)) => 1))
			(fact "no distance between a point and itself"
				(distance/even_q_between offset_origin offset_origin) => 0)
			(fact "other distances are also calculated correctly"
				(distance/even_q_between offset_origin {:x 2 :y 0}) => 2
				(distance/even_q_between offset_origin {:x 1 :y 2}) => 2
				(distance/even_q_between offset_origin {:x -4 :y 3}) => 5
				(distance/even_q_between offset_origin {:x -3 :y -2}) => 4
				)
			)
		(fact "for odd_q coordinates"
			(fact "all neighbours are distance 1"
				(let [neighbours (n/neighbours_for_odd_q offset_origin)]
					(distance/odd_q_between offset_origin (nth neighbours 0)) => 1
					(distance/odd_q_between offset_origin (nth neighbours 1)) => 1
					(distance/odd_q_between offset_origin (nth neighbours 2)) => 1
					(distance/odd_q_between offset_origin (nth neighbours 3)) => 1
					(distance/odd_q_between offset_origin (nth neighbours 4)) => 1
					(distance/odd_q_between offset_origin (nth neighbours 5)) => 1))
			(fact "no distance between a point and itself"
				(distance/odd_q_between offset_origin offset_origin) => 0)
			(fact "other distances are also calculated correctly"
				(distance/odd_q_between offset_origin {:x 2 :y 0}) => 2
				(distance/odd_q_between offset_origin {:x 1 :y 2}) => 3
				(distance/odd_q_between offset_origin {:x -4 :y 3}) => 4
				(distance/odd_q_between offset_origin {:x -3 :y -2}) => 5
				)
			)
		(fact "for even_r coordinates"
			(fact "all neighbours are distance 1"
				(let [neighbours (n/neighbours_for_even_r offset_origin)]
					(distance/even_r_between offset_origin (nth neighbours 0)) => 1
					(distance/even_r_between offset_origin (nth neighbours 1)) => 1
					(distance/even_r_between offset_origin (nth neighbours 2)) => 1
					(distance/even_r_between offset_origin (nth neighbours 3)) => 1
					(distance/even_r_between offset_origin (nth neighbours 4)) => 1
					(distance/even_r_between offset_origin (nth neighbours 5)) => 1))
			(fact "no distance between a point and itself"
				(distance/even_r_between offset_origin offset_origin) => 0)
			(fact "other distances are also calculated correctly"
				(distance/even_r_between offset_origin {:x 2 :y 0}) => 2
				(distance/even_r_between offset_origin {:x 1 :y 2}) => 3
				(distance/even_r_between offset_origin {:x -4 :y 3}) => 4
				(distance/even_r_between offset_origin {:x -3 :y -2}) => 5
				)
			)
		(fact "for odd_r coordinates"
			(fact "all neighbours are distance 1"
				(let [neighbours (n/neighbours_for_odd_r offset_origin)]
					(distance/odd_r_between offset_origin (nth neighbours 0)) => 1
					(distance/odd_r_between offset_origin (nth neighbours 1)) => 1
					(distance/odd_r_between offset_origin (nth neighbours 2)) => 1
					(distance/odd_r_between offset_origin (nth neighbours 3)) => 1
					(distance/odd_r_between offset_origin (nth neighbours 4)) => 1
					(distance/odd_r_between offset_origin (nth neighbours 5)) => 1))
			(fact "no distance between a point and itself"
				(distance/odd_r_between offset_origin offset_origin) => 0)
			(fact "other distances are also calculated correctly"
				(distance/odd_r_between offset_origin {:x 2 :y 0}) => 2
				(distance/odd_r_between offset_origin {:x 1 :y 2}) => 3
				(distance/odd_r_between offset_origin {:x -4 :y 3}) => 4
				(distance/odd_r_between offset_origin {:x -3 :y -2}) => 5
				)
			)
		)
	)
