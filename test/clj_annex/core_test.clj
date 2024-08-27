(ns clj-annex.core-test
  (:require [clojure.test :refer :all]
            [clj-annex.core :refer :all]
            [clj-annex.test-lib-c :refer :all]))

(clj-annex.test-lib-c/hello-from-a)
(clj-annex.test-lib-c/hello-from-b)
(clj-annex.core/dump-ns 'clj-annex.test-lib-c)
