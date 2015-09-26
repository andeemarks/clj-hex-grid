(ns clj-hex-grid.coordinate-test
	(:require [clj-hex-grid.coordinate :as coord])
  (:use [midje.sweet]))

(facts "when converting from cube to offset coordinates"
	(facts "to offset even-q"
		(fact "the row is assigned z + (x + (x&1)) / 2"
			(coord/cube_to_offset_even_q {:x 1 :y 2 :z 3}) => (contains {:row 4}))
		(fact "the column is assigned the cube x"
			(coord/cube_to_offset_even_q {:x 1 :y 2 :z 3}) => (contains {:col 1})))

	(facts "to odd-q"
		(fact "the row is assigned z + (x - (x&1)) / 2"
			(coord/cube_to_offset_odd_q {:x 1 :y 2 :z 3}) => (contains {:row 3}))
		(fact "the column is assigned the cube x"
			(coord/cube_to_offset_odd_q {:x 1 :y 2 :z 3}) => (contains {:col 1})))

	(facts "to even-r"
		(fact "the column is assigned x + (z + (z&1)) / 2"
			(coord/cube_to_offset_even_r {:x 1 :y 2 :z 3}) => (contains {:col 3}))
		(fact "the row is assigned the cube z"
			(coord/cube_to_offset_even_r {:x 1 :y 2 :z 3}) => (contains {:row 3})))

	(facts "to odd-r"
		(fact "the column is assigned x + (z - (z&1)) / 2"
			(coord/cube_to_offset_odd_r {:x 1 :y 2 :z 3}) => (contains {:col 2}))

		(fact "the row is assigned the cube z"
			(coord/cube_to_offset_odd_r {:x 1 :y 2 :z 3}) => (contains {:row 3})))

	)

(facts "when converting from offset to cube coordinates"
	(facts "from even-q"
		(fact "the x is assigned the offset column"
			(coord/offset_even_q_to_cube {:row 1 :col 2}) => (contains {:x 2}))
		(fact "the z is assigned row - (col + (col&1)) / 2"
			(coord/offset_even_q_to_cube {:row 1 :col 2}) => (contains {:z 0}))
		(fact "the y is assigned -x-z"
			(coord/offset_even_q_to_cube {:row 1 :col 2}) => (contains {:y -2})))

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
