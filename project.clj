;; Copyright © 2015, JUXT LTD.

(defproject yada "0.4.1"
  :description "A library for Clojure web APIs"
  :url "http://github.com/juxt/yada"
  :license {:name "The MIT License"
            :url "http://opensource.org/licenses/MIT"}

  :exclusions [com.stuartsierra/component
               org.clojure/clojure]

  :dependencies
  [[prismatic/schema "0.3.5" :exclusions [potemkin]] ; TODO 0.4.2
   [manifold "0.1.0-beta12"]
   [bidi "1.19.0"]
   [potemkin "0.3.11"]
   [hiccup "1.0.5"]
   [cheshire "5.4.0"]
   [ring-basic-authentication "1.0.5"]
   [camel-snake-kebab "0.1.4"]
   [potemkin "0.3.11"]
   [org.clojure/core.async "0.1.346.0-17112a-alpha"]
   [clj-time "0.9.0"]
   [metosin/ring-swagger "0.20.2"]]

  :repl-options {:init-ns user
                 :welcome (println "Type (dev) to start")}

  :profiles
  {:dev {:main yada.dev.main ; needs dev profile: lein with-profile dev
                             ; trampoline run

         :dependencies
         [[org.clojure/clojure "1.7.0-beta3"]

          [org.clojure/tools.logging "0.2.6"]
          [ch.qos.logback/logback-classic "1.0.7"
           :exclusions [org.slf4j/slf4j-api]]
          [org.slf4j/jul-to-slf4j "1.7.2"]
          [org.slf4j/jcl-over-slf4j "1.7.2"]
          [org.slf4j/log4j-over-slf4j "1.7.2"]

          [com.stuartsierra/component "0.2.2"]
          [org.clojure/tools.namespace "0.2.5"]
          [org.clojure/data.zip "0.1.1"]

          [markdown-clj "0.9.62"]
          [ring-mock "0.1.5"]

          [juxt.modular/aleph "0.0.8" :exclusions [manifold]]
          [juxt.modular/bidi "0.9.2" :exclusions [bidi]]
          [juxt.modular/clostache "0.6.1"]
          [juxt.modular/co-dependency "0.2.0"]
          [juxt.modular/maker "0.5.0"]
          [juxt.modular/test "0.1.0"]
          [juxt.modular/template "0.6.2"]

          [org.webjars/swagger-ui "2.1.8-M1"]
          [org.webjars/jquery "2.1.3"]
          [org.webjars/bootstrap "3.3.2"]
          ]

         :source-paths ["dev/src"]
         :resource-paths ["dev/resources"]}})
