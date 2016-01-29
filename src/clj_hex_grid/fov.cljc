(ns clj-hex-grid.fov
  (:require [clj-hex-grid.path :as path]
            [clj-hex-grid.neighbours :as n]))

(defn cube-visible
  "Returns set of hexes visible from origin, at max radius"
  [origin radius opaque-fn]
  (letfn [(visible-hexes [target]
            (let [[visible opaque] (split-with (comp not opaque-fn)
                                               (path/between origin target))
                  first-opaque (first opaque)]
              (if first-opaque (cons first-opaque visible)
                               visible)))]
    (->> (n/cube-ring origin radius)
         (pmap visible-hexes)
         flatten
         set)))

