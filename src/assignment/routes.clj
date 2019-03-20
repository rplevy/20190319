(ns assignment.routes
  "Routes for REST service"
  (:require [assignment.db :as db]
            [assignment.read :as read]
            [assignment.sort :as sort]
            [assignment.validate :as validate]
            [cheshire.core :as json]
            [compojure.core :refer :all]
            [compojure.route :as route]))

(defn json-response [rows]
  (json/generate-string {:results rows}))

(defroutes app
  (POST "/records" request
        (let [line (slurp (:body request))
              input-format (validate/detect-format line)]
          (if input-format
            (let [data (read/using-format input-format line)]
              (db/insert-record data)
              {:status 201
               :body (format "inserted record for %s\n" (:last-name data))})
            {:status 400
             :body "invalid input format"})))

  (GET "/records/gender" request
       {:status 200
        :headers {"Content-Type" "application/json"}
        :body (json-response
               (sort/by {:gender :asc} @db/db))})

  (GET "/records/birthdate" request
       {:status 200
        :headers {"Content-Type" "application/json"}
        :body (json-response
               (sort/by {:birthdate :asc} @db/db))})

  (GET "/records/name" request
       {:status 200
        :headers {"Content-Type" "application/json"}
        :body (json-response
               (sort/by {:name :asc} @db/db))})

  (route/not-found "Route not found"))
