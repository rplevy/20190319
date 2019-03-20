(ns assignment.routes-test
  (:require [clojure.test :refer :all]
            [assignment.fixtures :as fixtures]
            [assignment.routes :as base]
            [assignment.db :as db]
            [assignment.util :as util]
            [ring.mock.request :as mock]))

(use-fixtures :once fixtures/reset-db)

(deftest route-tests
  (is (= (base/app (mock/request :get "/records/gender"))
         {:status 200
          :headers {"Content-Type" "application/json"}
          :body "{\"results\":[]}"}))
  (is (= (base/app (mock/request :get "/records/birthdate"))
         {:status 200
          :headers {"Content-Type" "application/json"}
          :body "{\"results\":[]}"}))
  (is (= (base/app (mock/request :get "/records/name"))
         {:status 200
          :headers {"Content-Type" "application/json"}
          :body "{\"results\":[]}"}))
  (is (= (base/app (-> (mock/request :post "/records")
                       (mock/body "Barker | Bob | male | blue | 1/1/2001")))
         {:status 201
          :headers {}
          :body "inserted record for Barker\n"}))
  (is (= (base/app (-> (mock/request :post "/records")
                       (mock/body "Smith | Alice | female | green | 1/1/2002")))
         {:status 201
          :headers {}
          :body "inserted record for Smith\n"}))
  (is (= (base/app (mock/request :get "/records/gender"))
         {:status 200
          :headers {"Content-Type" "application/json"}
          :body "{\"results\":[{\"last-name\":\"Smith\",\"first-name\":\"Alice\",\"gender\":\"female\",\"favorite-color\":\"green\",\"birthdate\":\"01/01/2002\"},{\"last-name\":\"Barker\",\"first-name\":\"Bob\",\"gender\":\"male\",\"favorite-color\":\"blue\",\"birthdate\":\"01/01/2001\"}]}"}))
  (is (= (base/app (mock/request :get "/records/birthdate"))
         {:status 200
          :headers {"Content-Type" "application/json"}
          :body "{\"results\":[{\"last-name\":\"Barker\",\"first-name\":\"Bob\",\"gender\":\"male\",\"favorite-color\":\"blue\",\"birthdate\":\"01/01/2001\"},{\"last-name\":\"Smith\",\"first-name\":\"Alice\",\"gender\":\"female\",\"favorite-color\":\"green\",\"birthdate\":\"01/01/2002\"}]}"}))
  (is (= (base/app (mock/request :get "/records/name"))
         {:status 200
          :headers {"Content-Type" "application/json"}
          :body "{\"results\":[{\"last-name\":\"Barker\",\"first-name\":\"Bob\",\"gender\":\"male\",\"favorite-color\":\"blue\",\"birthdate\":\"01/01/2001\"},{\"last-name\":\"Smith\",\"first-name\":\"Alice\",\"gender\":\"female\",\"favorite-color\":\"green\",\"birthdate\":\"01/01/2002\"}]}"})))
