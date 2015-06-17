(ns image-array-util.core)

(defn rotate-image [image deg]
  (case deg
    :90 (let [new-height (count (nth image 0)) new-width (count image)]
          (->>
            (map reverse image)
            (apply interleave)
            (partition new-width)))
    :180 (reverse (map reverse image))
    :270 (rotate-image (rotate-image image :90) :180)
    (throw (IllegalArgumentException. (str "Can't do that bucco. Argument 'deg' (curently '" deg "') must be one of :90 :180 :270.")))))

(defn crop-image [image [vertical-s vertical-e] [horizontal-s horizontal-e]]
  (map #(subvec % horizontal-s horizontal-e) (subvec image vertical-s vertical-e)))
