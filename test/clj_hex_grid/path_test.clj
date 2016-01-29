(ns clj-hex-grid.path-test
  (:require [clj-hex-grid.path :as path]
            [clj-hex-grid.neighbours :as n])
  (:use [midje.sweet]))

(def cube-origin {:x 0 :y 0 :z 0})

(def empty-board (into {} (for [x (range 4) y (range 4)]
                            [{:x x :y y :z (- 0 x y)} 1])))

(def board-walls [{:x 1 :y 0 :z -1}
                  {:x 0 :y 3 :z -3}
                  {:x 1 :y 2 :z -3}
                  {:x 2 :y 1 :z -3}])

(def test-board (reduce #(assoc %1 %2 9)
                        empty-board
                        board-walls))

(facts "when calculating paths"
       (fact "two elements in path fom a point to a neighbour"
             (let [neighbours (n/cube-neighbours cube-origin)
                   destination (first neighbours)
                   path (path/between cube-origin destination)]
               path => [cube-origin destination]))
       (fact "only expected elements in longer horizontal path"
             (let [destination {:x -5 :y 5 :z 0}
                   path (path/between cube-origin destination)]
               path => [cube-origin
                        {:x -1 :y 1 :z 0}
                        {:x -2 :y 2 :z 0}
                        {:x -3 :y 3 :z 0}
                        {:x -4 :y 4 :z 0}
                        destination]))
       (fact "only expected elements in longer meandering path"
             (let [destination {:x 4 :y -2 :z -2}
                   path (path/between cube-origin destination)]
               path => [cube-origin
                        {:x 1 :y 0 :z -1}
                        {:x 2 :y -1 :z -1}
                        {:x 3 :y -1 :z -2}
                        destination]))
       (fact "only one element in path from a point to itself"
             (let [path (path/between cube-origin cube-origin)]
               path => [cube-origin])))

(facts "when finding paths"
       (let [destination {:x 2, :y 2, :z -4}
             path (path/cube-find-path
                    cube-origin
                    destination
                    test-board)
             nodes (:nodes path)
             steps (partition 2 nodes)]
         (fact "shortest path on test board"
               (:cost path) => 7)
         (fact "no 'walls' in path"
               (not-any? #(some #{%} board-walls) nodes) => true)
         (fact "path contains start"
               (boolean (some #{cube-origin} nodes)) => true)
         (fact "path contains end"
               (boolean (some #{destination} nodes)) => true)
         (fact "all nodes in path are adjecent (no jumps)"
               (every? (fn [[node nbr]] (n/cube-neighbour? node nbr)) steps) => true)))