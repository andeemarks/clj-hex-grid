(ns clj-hex-grid.coordinate-test
	(:require [clj-hex-grid.coordinate :as coord])
  (:use [midje.sweet]))

(facts "when converting from cube coordinates"
	(facts "to offset even-q"
		(fact "the offset column is assigned the cube x"
			(coord/cube_to_offset_even_q {:x 1 :y 2 :z 3}) => (contains {:col 1}))))
