(defproject expectations "2.1.0-SNAPSHOT"
  :description "testing framework"
  :jar-name "expectations.jar"
  :jar-exclusions [#"\.cljx|\.swp|\.swo|\.DS_Store"]
  :java-source-paths ["src/java"]
  :source-paths ["src/cljx" "src/clojure" "src/cljs"]
  :test-paths ["target/test-classes"]
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2760"]]

  :plugins [[lein-expectations "0.0.8"]
            [lein-publishers "1.0.13"]]

  :deploy-repositories [["releases" :clojars]]

  :profiles {:dev {:dependencies      [[joda-time/joda-time "2.7"]
                                       [junit/junit "4.12"]]
                   :node-dependencies [[source-map-support "^0.2.9"]]
                   :plugins           [[com.keminglabs/cljx "0.5.1-SNAPSHOT"]
                                       [lein-cljsbuild "1.0.4"]
                                       [lein-npm "0.5.0"]]}}

  ;:prep-tasks ["clean" "cljx" "javac"]
  :auto-clean false

  :cljx {:builds [{:source-paths ["src/cljx"]
                   :output-path  "target/classes"
                   :rules        :clj}

                  {:source-paths ["src/cljx"]
                   :output-path  "target/classes"
                   :rules        :cljs}

                  {:source-paths ["test/cljx"]
                   :output-path  "target/test-classes"
                   :rules        :clj}

                  {:source-paths ["test/cljx"]
                   :output-path  "target/test-classes"
                   :rules        :cljs}]}

  :cljsbuild {:builds [{:source-paths   ["target/classes" "src/cljs" "target/test-classes"]
                        :notify-command ["node" "./scripts/run-tests.js"]
                        :compiler       {:target         :nodejs
                                         :output-to      "target/out/test.js"
                                         :output-dir     "target/out"
                                         :optimizations  :none
                                         :cache-analysis true
                                         :source-map     true
                                         :pretty-print   true}}]}

  :min-lein-version "2.5.0")
