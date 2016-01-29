(ns clj-hex-grid.coordinate-test
  (:require [clj-hex-grid.coordinate :as coord])
  (:use [midje.sweet]))

(facts "when converting from cube to offset coordinates"
       (facts "to offset even-q"
              (fact "the row is assigned z + (x + (x&1)) / 2"
                    (coord/cube->offset-even-q {:x 1 :y 2 :z 3}) => (contains {:row 4}))
              (fact "the column is assigned the cube x"
                    (coord/cube->offset-even-q {:x 1 :y 2 :z 3}) => (contains {:col 1})))

       (facts "to odd-q"
              (fact "the row is assigned z + (x - (x&1)) / 2"
                    (coord/cube->offset-odd-q {:x 1 :y 2 :z 3}) => (contains {:row 3}))
              (fact "the column is assigned the cube x"
                    (coord/cube->offset-odd-q {:x 1 :y 2 :z 3}) => (contains {:col 1})))

       (facts "to even-r"
              (fact "the column is assigned x + (z + (z&1)) / 2"
                    (coord/cube->offset-even-r {:x 1 :y 2 :z 3}) => (contains {:col 3}))
              (fact "the row is assigned the cube z"
                    (coord/cube->offset-even-r {:x 1 :y 2 :z 3}) => (contains {:row 3})))

       (facts "to odd-r"
              (fact "the column is assigned x + (z - (z&1)) / 2"
                    (coord/cube->offset-odd-r {:x 1 :y 2 :z 3}) => (contains {:col 2}))

              (fact "the row is assigned the cube z"
                    (coord/cube->offset-odd-r {:x 1 :y 2 :z 3}) => (contains {:row 3}))))

(facts "when converting from offset to cube coordinates"
       (facts "from even-q"
              (fact "the x is assigned the offset column"
                    (coord/offset-even-q->cube {:row 1 :col 2}) => (contains {:x 2}))
              (fact "the z is assigned row - (col + (col&1)) / 2"
                    (coord/offset-even-q->cube {:row 1 :col 2}) => (contains {:z 0}))
              (fact "the y is assigned -x-z"
                    (coord/offset-even-q->cube {:row 1 :col 2}) => (contains {:y -2})))

       (facts "from odd-q"
              (fact "the x is assigned the offset column"
                    (coord/offset-odd-q->cube {:row 2 :col 3}) => (contains {:x 3}))
              (fact "the z is assigned row - (col - (col&1)) / 2"
                    (coord/offset-odd-q->cube {:row 2 :col 3}) => (contains {:z 1}))
              (fact "the y is assigned -x-z"
                    (coord/offset-odd-q->cube {:row 2 :col 3}) => (contains {:y -4})))

       (facts "from even-r"
              (fact "the x is assigned col - (row + (row&1)) / 2"
                    (coord/offset-even-r->cube {:row 3 :col 2}) => (contains {:x 0}))
              (fact "the z is assigned row"
                    (coord/offset-even-r->cube {:row 3 :col 2}) => (contains {:z 3}))
              (fact "the y is assigned -x-z"
                    (coord/offset-even-r->cube {:row 3 :col 2}) => (contains {:y -3})))

       (facts "from odd-r"
              (fact "the x is assigned col - (row + (row&1)) / 2"
                    (coord/offset-odd-r->cube {:row 0 :col 2}) => (contains {:x 2}))
              (fact "the z is assigned row"
                    (coord/offset-odd-r->cube {:row 0 :col 2}) => (contains {:z 0}))
              (fact "the y is assigned -x-z"
                    (coord/offset-odd-r->cube {:row 0 :col 2}) => (contains {:y -2}))))
