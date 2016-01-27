(defproject clj-hex-grid "0.1.1-SNAPSHOT"
  :description "A basic Clojure library for working with hexagonal grids"
  :url "https://github.com/andeemarks/clj-hex-grid/"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :profiles {:dev {:dependencies [[midje "1.7.0"]
                                  [compojure "1.4.0"]]
                   :plugins      [[lein-midje "3.1.3"]]}}
  :dependencies [[org.clojure/clojure "1.7.0"]])
