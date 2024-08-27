;; clj-annex -- https://www.github.com/takeiteasy/clj-annex
;;
;; The MIT License (MIT)
;;
;; Copyright (c) 2024 George Watson
;;
;; Permission is hereby granted, free of charge, to any person
;; obtaining a copy of this software and associated documentation
;; files (the "Software"), to deal in the Software without restriction,
;; including without limitation the rights to use, copy, modify, merge,
;; publish, distribute, sublicense, and/or sell copies of the Software,
;; and to permit persons to whom the Software is furnished to do so,
;; subject to the following conditions:
;;
;; The above copyright notice and this permission notice shall be
;; included in all copies or substantial portions of the Software.
;;
;; THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
;; EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
;; MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
;; IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
;; CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
;; TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
;; SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

(ns clj-annex.core)

(defmacro annex-ns
  [& namespaces]
  (let [sym (gensym 'x)
        val (gensym 'y)
        dst (gensym 'z)
        tmp (gensym 'w)]
    `(do
       (require ~@namespaces)
       (doseq [~tmp [~@namespaces]]
         (doseq [[~sym ~val] (ns-publics (find-ns ~tmp))]
           (let [~dst (if (bound? ~val)
                        (intern *ns* ~sym (var-get ~val))
                        (intern *ns* ~sym))]
             (->>
               (select-keys (meta ~dst) [:name :ns])
               (merge (meta ~val))
               (with-meta '~dst))))))))

(defmacro dump-ns
  [& namespaces]
  (let [sym (gensym 'x)
        tmp (gensym 'y)]
    `(doseq [~tmp [~@namespaces]]
       (do
         (require ~tmp)
         (doseq [~sym (ns-publics (find-ns ~tmp))]
           (println (first ~sym)))))))