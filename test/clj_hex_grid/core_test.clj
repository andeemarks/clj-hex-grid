(ns clj-hex-grid.core-test
	(:require [clj-hex-grid.core :as hex])
  (:use [midje.sweet]))

(facts "hex sizes and spacing"
	(fact "default size is 1"
		(hex/width) => 2)
	(fact "width is twice the size"
		(hex/width {:size 1}) => 2
		(hex/width {:size 2}) => 4))
