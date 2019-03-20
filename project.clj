(defproject assignment "1.0.0"
  :description "assignment"
  :url "http://127.0.0.1"
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler assignment.routes/app}
  :main assignment.cli
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/tools.cli "0.4.1"]
                 [org.clojure/tools.logging "0.4.1"]
                 [compojure "1.6.1"]
                 [cheshire "5.8.1"]]
  :repl-options {:init-ns assignment.routes})
