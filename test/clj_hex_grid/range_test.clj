(ns clj-hex-grid.range-test
  (:require [clj-hex-grid.range :as range])
  (:require [clj-hex-grid.neighbours :as n])
  (:use [midje.sweet]))

(def cube_origin {:x 0 :y 0 :z 0})

(facts "when calculating ranges"
       (fact "only neighbours are within 1 range of itself"
             (let [neighbours (n/cube-neighbours cube_origin)
                   range (range/range-from cube_origin 1)]
               range => (contains neighbours :in-any-order :gaps-ok)
               range => (contains cube_origin)))
       (fact "correct number in range for distance of 2"
             (let [neighbours (n/cube-neighbours cube_origin)
                   range (range/range-from cube_origin 2)]
               range => (contains neighbours :in-any-order :gaps-ok)
               range => (contains cube_origin)
               (count range) => 19))
       (fact "only origin is within 1 range of itself"
             (let [range (range/range-from cube_origin 0)]
               range => (just cube_origin))))
