(ns clj-hex-grid.fov-test
  (:require [clj-hex-grid.fov :as fov]
            [clj-hex-grid.neighbours :as n])
  (:use [midje.sweet]))

(def cube-origin {:x 0 :y 0 :z 0})
(def test-walls #{{:x 1 :y 0 :z -1}
                  {:x 0 :y 3 :z -3}
                  {:x 1 :y 2 :z -3}
                  {:x 2 :y 1 :z -3}})

(def test-board (reduce #(assoc %1 %2 true) {} test-walls))

(facts "when calculating visible hexes"
       (let [visible (fov/cube-visible cube-origin 5 (partial get test-board))
             visible-walls (disj test-walls {:x 2 :y 1 :z -3})
             behind-pilar (rest (n/cube-n-neighbours {:x 1 :y 0 :z -1} 5 :northeast))]
         (fact "nearest walls should be visible"
               (every? #(some #{%} visible) visible-walls) => true)
         (fact "pillar should cover stuff behind it"
               (not-any? #(some #{%} visible) behind-pilar) => true)))