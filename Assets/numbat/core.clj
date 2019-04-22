(ns numbat.core
  (:use arcadia.core))

(defn rotate [obj role-key]
  (.. obj transform (Rotate 0 1 0)))
