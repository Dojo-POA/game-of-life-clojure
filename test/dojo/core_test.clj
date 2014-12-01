(ns dojo.core-test
  (:require [clojure.test :refer :all]
            #_[dojo.core :refer :all]))


(def celula-sem-vizinho
  '[[x x x]
    [x o x]
    [x x x]])

(def todos-mortos
  '[[x x x]
    [x x x]
    [x x x]])

(defn proximo [world]
  '[[x x x]
    [x x x]
    [x x x]])

(def todos-vivos
  '[[o o]
    [o o]])

(defn vivo? [celula]
  (= celula 'o))

(defn vizinhos [x y world]
  (filter
   identity
   [(get-in world [(- x 1) (- y 1)])(get-in world [x (- y 1)])(get-in world [(+ x 1) (- y 1)])
   (get-in world [(dec x) y])(get-in world [(inc x) y])
   (get-in world [(dec x) (inc y)])(get-in world [x (inc y)])(get-in world [(inc x) (inc y)])]
  ))
  ;'[o o o o o o o o])

(def so-x-no-meio '[[o o o]
                    [o x o]
                    [o o o]])


(get-in [[1 2 3] [4 5 6]] [1 4])

(identity nil)

(identity 1)

(filter identity [nil nil 1])

(deftest game-of-life

  #_(testing "Nasce um"
    (is (= todos-vivos
           (proximo '[[o o]
                      [x o]]))))

  (testing "#vizinhos"
    (is(= '[o o o o o o o o]
          (vizinhos 1 1 so-x-no-meio)))
    (is(= '[o o x]
          (vizinhos 0 0 so-x-no-meio))))

  (testing "Uma celula sem nenhum vizinho morre"
    (is (= todos-mortos
           (proximo celula-sem-vizinho)))

    (is (= todos-mortos
           (proximo
            '[[x o x]
              [x o x]
              [x x x]]))))

  (testing "Define vivo/morto"
    (is (vivo? 'o))
    (is (not (vivo? 'x)))))

(run-all-tests)
