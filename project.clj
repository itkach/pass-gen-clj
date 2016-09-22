(defproject pass-gen "0.1.0-SNAPSHOT"
  :plugins [[lein-ring "0.9.7"]]
  :jvm-opts ["-Duser.timezone=UTC"]
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [acyclic/squiggly-clojure "0.1.6"]
                 [ring/ring-core "1.5.0"]
                 [compojure "1.5.1"]
                 [ring/ring-defaults "0.2.1"]
                 [ring-middleware-format "0.7.0"]
                 [environ "1.1.0"]
                 [org.clojure/tools.cli "0.3.5"]]
  ;;:ring {:handler eightx.service.core/app}
  :main ^:skip-aot pass-gen.core)
