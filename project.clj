(defproject org.clojars.scknkkrer/fener "0.0.20"
  :description "Fetch API wrapper for clojurescript."
  :url "https://github.com/LeaveNhA/fener"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies
  [[org.clojure/clojure "1.8.0"]
   [org.clojure/clojurescript "1.10.520" :scope "provided"]
   [re-frame "0.10.6"]]

  :lein-release {:deploy-via :clojars}

  :pom-addition [:developers [:developer
                              [:name "Seçkin KÜKRER"]
                              [:url "leavenha.github.io"]]]

  :plugins [[lein-figwheel "0.5.18"]
            [lein-release "1.1.0"]
            [lein-cljsbuild "1.1.7" :exclusions [[org.clojure/clojure]]]]

  :profiles {:user {:signing {:gpg-key "AFEF1B9FFC0B1500"}}
             :prod {}
             :dev {:repl-options {:nrepl-middleware [cider.piggieback/wrap-cljs-repl]}
                   :clean-targets ^{:protect false} ["resources/public/js/compiled"
                                                      :target-path]
                   :dependencies [[binaryage/devtools "0.9.4"]
                                  [figwheel-sidecar "0.5.18"]
                                  [cider/piggieback "0.4.1"]]
                   :source-paths ["src" "dev"]}}

  :figwheel {:css-dirs ["resources/public/css"]}

  :cljsbuild {:builds [{:id "prod"
                        :source-paths ["src"]
                        :incremental true
                        :compiler {:output-to "resources/public/js/compiled/fener.js"
                                   :pretty-print false
                                   :main fener.core
                                   :closure-defines {goog.DEBUG false}
                                   :optimizations :advanced}}
                       {:id "dev"
                        :source-paths ["src"]
                        :incremental true
                        :figwheel {:open-urls ["http://localhost:3449/index.html"]}
                        :assert true
                        :compiler {:output-to "resources/public/js/compiled/fener.js"
                                   :output-dir "resources/public/js/compiled/out"
                                   :asset-path "js/compiled/out"
                                   :main fener.core
                                   :preloads [devtools.preload]
                                   ; :infer-externs        true
                                   ; :foreign-libs [{:file "resources/public/js/bundle.js"
                                   ;                 :provides ["react" "react-dom"
                                   ;                            "react-toastify"]
                                   ;                 :requires ["react" "react-dom"
                                   ;                            "react-toastify"]
                                   ;                 :global-exports {react React
                                   ;                                  react-dom ReactDOM
                                   ;                                  react-toastify ReactToastify}}]
                                   :npm-deps             false
                                   :source-map-timestamp true
                                   :warnings true
                                   :source-map true}}]})
