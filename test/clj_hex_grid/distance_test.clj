(ns clj-hex-grid.distance-test
  (:require [clj-hex-grid.distance :as distance])
  (:require [clj-hex-grid.neighbours :as n])
  (:use [midje.sweet]))

(def cube_origin {:x 0 :y 0 :z 0})
(def offset_origin {:x 0 :y 0})

(facts "when determining distance"
       (facts "for cube coordinates"
              (fact "all neighbours are distance 1"
                    (let [neighbours (n/cube-neighbours cube_origin)]
                      (distance/cube-between cube_origin (nth neighbours 0)) => 1
                      (distance/cube-between cube_origin (nth neighbours 1)) => 1
                      (distance/cube-between cube_origin (nth neighbours 2)) => 1
                      (distance/cube-between cube_origin (nth neighbours 3)) => 1
                      (distance/cube-between cube_origin (nth neighbours 4)) => 1
                      (distance/cube-between cube_origin (nth neighbours 5)) => 1))
              (fact "no distance between a point and itself"
                    (distance/cube-between cube_origin cube_origin) => 0)
              (fact "other distances are also calculated correctly"
                    (distance/cube-between cube_origin {:x 2 :y 0 :z -2}) => 2
                    (distance/cube-between cube_origin {:x 1 :y 2 :z -3}) => 3
                    (distance/cube-between cube_origin {:x -4 :y 3 :z 1}) => 4
                    (distance/cube-between cube_origin {:x -3 :y -2 :z 5}) => 5))

       (facts "for offset coordinates"
              (fact "for even_q coordinates"
                    (fact "all neighbours are distance 1"
                          (let [neighbours (n/even-q-neighbours offset_origin)]
                            (distance/even-q-between offset_origin (nth neighbours 0)) => 1
                            (distance/even-q-between offset_origin (nth neighbours 1)) => 1
                            (distance/even-q-between offset_origin (nth neighbours 2)) => 1
                            (distance/even-q-between offset_origin (nth neighbours 3)) => 1
                            (distance/even-q-between offset_origin (nth neighbours 4)) => 1
                            (distance/even-q-between offset_origin (nth neighbours 5)) => 1))
                    (fact "no distance between a point and itself"
                          (distance/even-q-between offset_origin offset_origin) => 0)
                    (fact "other distances are also calculated correctly"
                          (distance/even-q-between offset_origin {:x 2 :y 0}) => 2
                          (distance/even-q-between offset_origin {:x 1 :y 2}) => 2
                          (distance/even-q-between offset_origin {:x -4 :y 3}) => 5
                          (distance/even-q-between offset_origin {:x -3 :y -2}) => 4))
              (fact "for odd_q coordinates"
                    (fact "all neighbours are distance 1"
                          (let [neighbours (n/odd-q-neighbours offset_origin)]
                            (distance/odd-q-between offset_origin (nth neighbours 0)) => 1
                            (distance/odd-q-between offset_origin (nth neighbours 1)) => 1
                            (distance/odd-q-between offset_origin (nth neighbours 2)) => 1
                            (distance/odd-q-between offset_origin (nth neighbours 3)) => 1
                            (distance/odd-q-between offset_origin (nth neighbours 4)) => 1
                            (distance/odd-q-between offset_origin (nth neighbours 5)) => 1))
                    (fact "no distance between a point and itself"
                          (distance/odd-q-between offset_origin offset_origin) => 0)
                    (fact "other distances are also calculated correctly"
                          (distance/odd-q-between offset_origin {:x 2 :y 0}) => 2
                          (distance/odd-q-between offset_origin {:x 1 :y 2}) => 3
                          (distance/odd-q-between offset_origin {:x -4 :y 3}) => 5
                          (distance/odd-q-between offset_origin {:x -3 :y -2}) => 3
                          )
                    )
              (fact "for even_r coordinates"
                    (fact "all neighbours are distance 1"
                          (let [neighbours (n/even-r-neighbours offset_origin)]
                            (distance/even-r-between offset_origin (nth neighbours 0)) => 1
                            (distance/even-r-between offset_origin (nth neighbours 1)) => 1
                            (distance/even-r-between offset_origin (nth neighbours 2)) => 1
                            (distance/even-r-between offset_origin (nth neighbours 3)) => 1
                            (distance/even-r-between offset_origin (nth neighbours 4)) => 1
                            (distance/even-r-between offset_origin (nth neighbours 5)) => 1))
                    (fact "no distance between a point and itself"
                          (distance/even-r-between offset_origin offset_origin) => 0)
                    (fact "other distances are also calculated correctly"
                          (distance/even-r-between offset_origin {:x 2 :y 0}) => 2
                          (distance/even-r-between offset_origin {:x 1 :y 2}) => 2
                          (distance/even-r-between offset_origin {:x -3 :y 1}) => 4
                          (distance/even-r-between offset_origin {:x -3 :y -2}) => 4
                          )
                    )
              (fact "for odd_r coordinates"
                    (fact "all neighbours are distance 1"
                          (let [neighbours (n/odd-r-neighbours offset_origin)]
                            (distance/odd-r-between offset_origin (nth neighbours 0)) => 1
                            (distance/odd-r-between offset_origin (nth neighbours 1)) => 1
                            (distance/odd-r-between offset_origin (nth neighbours 2)) => 1
                            (distance/odd-r-between offset_origin (nth neighbours 3)) => 1
                            (distance/odd-r-between offset_origin (nth neighbours 4)) => 1
                            (distance/odd-r-between offset_origin (nth neighbours 5)) => 1))
                    (fact "no distance between a point and itself"
                          (distance/odd-r-between offset_origin offset_origin) => 0)
                    (fact "other distances are also calculated correctly"
                          (distance/odd-r-between offset_origin {:x 2 :y 0}) => 2
                          (distance/odd-r-between offset_origin {:x 1 :y 2}) => 2
                          (distance/odd-r-between offset_origin {:x -4 :y 3}) => 5
                          (distance/odd-r-between offset_origin {:x -3 :y -2}) => 4
                          )
                    )
              )

       )
