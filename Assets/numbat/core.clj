(ns numbat.core
  (:use arcadia.core
        arcadia.linear)
  (:import [UnityEngine
            Input Vector2 Transform]))

(def ball-initial
  {:position Vector2/zero})

(defn move [obj offset]
  (state+ obj :position (update (state obj :position) :position v2+ offset))
  obj)

(defn set-position [obj position]
  (with-cmpt obj [tr Transform]
    (set! (. tr position)
          (v3 (.x position)
              (.y position)
              0))))

(defn input-move [obj role-key]
  (-> obj
      (move (v2 (Input/GetAxis "Horizontal")
                (Input/GetAxis "Vertical")))
      (set-position (:position (state obj role-key)))))
