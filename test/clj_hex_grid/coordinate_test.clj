(ns clj-hex-grid.coordinate-test
	(:require [clj-hex-grid.coordinate :as coord])
  (:use [midje.sweet]))

(facts "when converting from cube coordinates"
	(facts "to offset even-q"
		(fact "the offset row is assigned z + (x + (x&1)) / 2"
			(coord/cube_to_offset_even_q {:x 1 :y 2 :z 3}) => (contains {:row 4}))
		(fact "the offset column is assigned the cube x"
			(coord/cube_to_offset_even_q {:x 1 :y 2 :z 3}) => (contains {:col 1})))

	(facts "to offset odd-q"
		(fact "the offset row is assigned z + (x - (x&1)) / 2"
			(coord/cube_to_offset_odd_q {:x 1 :y 2 :z 3}) => (contains {:row 3}))
		(fact "the offset column is assigned the cube x"
			(coord/cube_to_offset_odd_q {:x 1 :y 2 :z 3}) => (contains {:col 1})))

	(facts "to offset even-r"
		(fact "the offset column is assigned x + (z + (z&1)) / 2"
			(coord/cube_to_offset_even_r {:x 1 :y 2 :z 3}) => (contains {:col 3}))
		(fact "the offset row is assigned the cube z"
			(coord/cube_to_offset_even_r {:x 1 :y 2 :z 3}) => (contains {:row 3})))

	(facts "to offset odd-r"
		(fact "the offset column is assigned x + (z - (z&1)) / 2"
			(coord/cube_to_offset_odd_r {:x 1 :y 2 :z 3}) => (contains {:col 2}))

		(fact "the offset row is assigned the cube z"
			(coord/cube_to_offset_odd_r {:x 1 :y 2 :z 3}) => (contains {:row 3})))


	)
