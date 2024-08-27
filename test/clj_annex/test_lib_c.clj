(ns clj-annex.test-lib-c
  (:require [clojure.test :refer :all]
            [clj-annex.core :refer :all]))

(annex-ns 'clj-annex.test-lib-a 'clj-annex.test-lib-b)
