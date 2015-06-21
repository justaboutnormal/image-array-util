(ns image-array-util.core-test
  (:require [clojure.test :refer :all]
            [image-array-util.core :refer :all]))


;one triplet represents a RGB pixel e.g. [1 2 3]
;grayscale pixels can be represented by a single value 0-255
;outer most vector represents entire image
;inner vectors that do not represent pixels are rows e.g. [[row 1][row 2]]

(deftest rotate-image-test
  (testing "Rotate an image"
    (are [x y] (= x y)
      (rotate-image [[1]] :90) [[1]]
      (rotate-image [[[0 0 0]]] :90) [[[0 0 0]]]
      (rotate-image [[1 2]] :90) [[2] [1]]
      (rotate-image [[[1 1 1] [2 2 2]]] :90) [[[2 2 2]] [[1 1 1]]]
      (rotate-image [[1 2]] :180) [[2 1]]
      (rotate-image [[[1 1 1] [2 2 2]]] :180) [[[2 2 2] [1 1 1]]]
      (rotate-image [[[1 1 1] [2 2 2] [3 3 3]] [[4 4 4] [5 5 5] [6 6 6]]] :270) [[[4 4 4] [1 1 1]] [[5 5 5] [2 2 2]] [[6 6 6] [3 3 3]]])
    (is (thrown-with-msg? Exception #"Can't do that bucco. Argument 'deg' \(curently ':1'\) must be one of :90 :180 :270." (rotate-image [[1]] :1)))))

(deftest crop-image-test
  (testing "Cropping an image"
    (are [x y] (= x y)
      (crop-image [[1]] [0 1] [0 1]) [[1]]
      (crop-image [[1 2 3] [4 5 6] [7 8 9]] [0 1] [0 3]) [[1 2 3]]
      (crop-image [[1 2 3] [4 5 6] [7 8 9]] [0 3] [0 1]) [[1] [4] [7]]
      (crop-image [[1 2 3] [4 5 6] [7 8 9]] [1 2] [1 2]) [[5]]
      (crop-image [[[1 1 1] [2 2 2] [3 3 3]]
                   [[4 4 4] [5 5 5] [6 6 6]]
                   [[7 7 7] [8 8 8] [9 9 9]]] [1 2] [1 2]) [[[5 5 5]]])))