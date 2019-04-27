(ns numbat.core
  (:use arcadia.core
        arcadia.linear)
  (:import [UnityEngine
            Input Vector2 Transform]))

(def ball-initial
  {:position Vector2/zero})

(def player-initial
  {:position (v2 -0.5 0) :has-ball false})

(def input-scale
  0.25)

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
      (move (v2 (* (Input/GetAxis "Horizontal") input-scale)
                (* (Input/GetAxis "Vertical") input-scale)))
      (set-position (:position (state obj role-key)))))

(defn trap-ball [obj role-key collision]
  (if (= (.. collision gameObject name) "Ball")
    (do
      (state+ obj :position (assoc (state obj :position) :has-ball true))
      (child+ obj (.. collision gameObject) true))
    obj))

