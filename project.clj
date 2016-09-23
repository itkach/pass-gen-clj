(defproject pass-gen "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/tools.cli "0.3.5"]]
  :main pass-gen.core
  :aot [pass-gen.core])
